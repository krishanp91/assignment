package com.smashtaps.assignment.dto;

import com.smashtaps.assignment.entity.Shelf;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class ShopperDto implements Serializable {
    private static final long serialVersionUID = -6561032036304756621L;

    private int id;
    private String shopperId;
    private Set<ShelfDto> shelves;
}
