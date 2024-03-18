package com.smashtaps.assignment.dto;

import com.smashtaps.assignment.entity.Shelf;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 7415597639673767179L;

    private int id;
    private String productId;
    private String category;
    private String brand;
    private Set<Shelf> shelves;
}
