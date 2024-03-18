package com.smashtaps.assignment.service;

import com.smashtaps.assignment.dto.ShopperDto;
import org.springframework.data.domain.Page;

public interface ExternalService {
    Page<ShopperDto> filterShopperDetails(String shopperId, String category, String brand, int page, int size);
}
