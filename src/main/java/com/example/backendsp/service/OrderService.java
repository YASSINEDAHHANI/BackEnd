package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.Cart;
import com.example.backendsp.DAO.entities.Manga;
import com.example.backendsp.DAO.entities.Order;
import com.example.backendsp.DAO.entities.User;
import com.example.backendsp.DAO.repo.CartRepo;
import com.example.backendsp.DAO.repo.OrderRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServices {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserServices userServices;
    @Override
    public Order createOrder(Long userId) {
        User user = userServices.getUserById(userId);
        Cart cart = user.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart not found for user id: " + userId);
        }

        Order order = new Order();
        order.setUserId(userId);
        Hibernate.initialize(cart.getMangas());

        // Create a new list of Manga instances for the Order
        List<Manga> mangaList = cart.getMangas().stream().map(manga -> {
            Manga newManga = new Manga();
            newManga.setTitle(manga.getTitle());
            newManga.setImgUrl(manga.getImgUrl());
            newManga.setDescription(manga.getDescription());
            newManga.setPrice(manga.getPrice());
            // Do not set the order reference here to avoid circular reference issues
            return newManga;
        }).collect(Collectors.toList());

        order.setMangaList(mangaList);
        order.setTotalPrice(mangaList.stream().mapToDouble(Manga::getPrice).sum());
        order.setOrderNumber(generateOrderNumber());

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        cartRepo.delete(cart);

        return savedOrder;
    }
    private String generateOrderNumber() {
        return "ORDER-" + new Random().nextInt(999999);
    }
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public Order getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

}