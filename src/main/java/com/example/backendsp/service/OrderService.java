package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.Cart;
import com.example.backendsp.DAO.entities.Manga;
import com.example.backendsp.DAO.entities.Order;
import com.example.backendsp.DAO.entities.User;
import com.example.backendsp.DAO.repo.CartRepo;
import com.example.backendsp.DAO.repo.MangaRepo;
import com.example.backendsp.DAO.repo.OrderRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServices {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MangaRepo mangaRepo;

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
        order.setTotalPrice(cart.getMangas().stream().mapToDouble(Manga::getPrice).sum());
        order.setOrderNumber(generateOrderNumber());


        System.out.println("------------------------------------------------------------- ORDER CHECK ");
        order.setMangaList(cart.getMangas().stream().toList());
        System.out.println("------------------------------------------------------------- ORDER CHECK ");
        System.out.println(order);

        System.out.println("------------------------------------------------------------- SAVED ORDER ----------------------------------     CHECK ");

        System.out.println(orderRepository.save(order));

        cart.setMangas(null);
        cartRepo.save(cart);

        return order;
    }


    private String generateOrderNumber() {
        return "ORDER-" + new Random().nextInt(999999);
    }
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order saveOrderById(Long userid) {
        Cart cart = cartRepo.findByUserId(userid).orElseThrow(()->new RuntimeException("cart not found"));
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setUserId(userid);

        order.setMangaList(cart.getMangas());
        orderRepository.save(order);
        return null;
    }

    public Order getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}