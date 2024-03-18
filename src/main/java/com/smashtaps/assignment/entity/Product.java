package com.smashtaps.assignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productId;
    private String category;
    private String brand;

    @OneToMany
    private Set<Shelf> productShelfList;

    public Product(String productId, String category, String brand){
        this.productId = productId;
        this.category = category;
        this.brand = brand;
    }
}
