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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    private List<Manga> mangas;
    private int quantity;
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : "null") + // Assuming User has getId() method
                ", mangaList=" + mangas +
                ", quantity=" + quantity +
                '}';
    }
}
