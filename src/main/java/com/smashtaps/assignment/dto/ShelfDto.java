package com.smashtaps.assignment.dto;

import com.smashtaps.assignment.entity.Shopper;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ShelfDto implements Serializable {
    private static final long serialVersionUID = 2949461343999822368L;

    private int id;
    private double relevancyScore;
    private ProductDto product;
    private ShopperDto shopper;
}
