package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.Order;

public interface OrderServices {
    Order createOrder(Long userId);
    Order getOrderById(Long orderId);
}
