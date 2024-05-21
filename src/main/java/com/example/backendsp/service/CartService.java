package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.Cart;
import com.example.backendsp.DAO.entities.Manga;
import com.example.backendsp.DAO.entities.Order;
import com.example.backendsp.DAO.repo.CartRepo;
import com.example.backendsp.DAO.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService implements CartServices {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private MangaServices mgser;

    @Autowired
    private OrderService orderService;

    @Override
    public List<Cart> getAllCarts() {
        return null;
    }

    @Override
    public Optional<Cart> getCartById(Long Id) {

        return Optional.empty();
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartRepo.findByUserId(userId);
    }


    //    public Cart createCart(Cart cart) {
//        return null;
//    }
    public  Cart addToCart(Cart cart){
        return cartRepo.save(cart);
    }
    @Override
    public Cart updateCart(Long Id, Cart updatedCart) {
        return null;
    }

    @Override
    public void deleteCart(Long Id) {

    }

    @Override
    public void deleteMangaFromCart(long userId, long bookId) {
           Cart cart =  cartRepo.findByUserId(userId).get();
           System.out.println(cart);
           cart.getMangas().remove(mgser.getMangaById(bookId));
           cartRepo.save(cart);
    }
    public Order checkout(Long userId) {
        return orderService.createOrder(userId);
    }
}
