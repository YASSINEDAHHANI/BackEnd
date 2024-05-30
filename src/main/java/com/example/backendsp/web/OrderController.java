package com.example.backendsp.web;

import com.example.backendsp.DAO.entities.Order;
import com.example.backendsp.DAO.repo.CartRepo;
import com.example.backendsp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<Order> createOrder(@RequestParam("userId") Long userId) {
            Order order = orderService.createOrder(userId);

            return ResponseEntity.ok(new Order());
    }
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }


    @GetMapping("/details/{orderId}")
    public Order getOrderDetails(@PathVariable Long orderId) {
        return orderService.getOrderDetails(orderId);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

}
