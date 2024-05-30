package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.Order;

import java.util.List;

public interface OrderServices {
    Order createOrder(Long userId);
    Order getOrderById(Long orderId);

    Order saveOrderById(Long userid);
}
