package com.smashtaps.assignment.controller;

import com.smashtaps.assignment.dto.ShopperDto;
import com.smashtaps.assignment.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
public class ExternalController {
    @Autowired
    private ExternalService externalService;

    @GetMapping
    public ResponseEntity<Page<ShopperDto>> filterShopperDetails(@RequestParam String shopperId, @RequestParam String category, @RequestParam String brand, @RequestParam int page, @RequestParam int size){
        Page<ShopperDto> shoppers = externalService.filterShopperDetails(shopperId, category, brand, page, size);
        return new ResponseEntity<>(shoppers, HttpStatus.OK);
    }
}
