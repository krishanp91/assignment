package com.smashtaps.assignment.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shopper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String shopperId;

    @JsonManagedReference
    @OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Shelf> shelves;

    public Shopper(String shopperId, Set<Shelf> shelves){
        this.shopperId = shopperId;
        this.shelves = shelves;
    }
}
