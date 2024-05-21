package com.example.backendsp.DAO.repo;

import com.example.backendsp.DAO.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
