package com.smashtaps.assignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double relevancyScore;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopper_id", nullable = false, updatable = false)
    private Shopper shopper;

    public Shelf(double relevancyScore, Product product, Shopper shopper){
        this.relevancyScore = relevancyScore;
        this.product = product;
        this.shopper = shopper;
    }
}
