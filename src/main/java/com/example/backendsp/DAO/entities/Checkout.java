package com.example.backendsp.DAO.entities;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Checkout {
    private Long Order;
    private String Adress;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order")
    private List<Manga> Items;
}
