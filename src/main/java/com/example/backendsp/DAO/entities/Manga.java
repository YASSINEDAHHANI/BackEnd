package com.example.backendsp.DAO.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Number;
    private String Title;
    private String imgUrl;
    private String description;
    private double price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "mangas")
    private List<Cart> cart;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Order> order;

    @Override
    public String toString() {
        return "Manga{" +
                "id=" + Number +
                ", title='" + Title + '\'' +
                ", email='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
