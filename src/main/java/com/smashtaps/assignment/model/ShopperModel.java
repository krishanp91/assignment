package com.smashtaps.assignment.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ShopperModel {
    private String shopperId;
    private List<ShelfModel> shelf;
}
