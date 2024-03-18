package com.smashtaps.assignment.controller;

import com.smashtaps.assignment.dto.ShopperDto;
import com.smashtaps.assignment.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal")
public class InternalController {
    @Autowired
    private InternalService internalService;

    @GetMapping
    public ResponseEntity<List<ShopperDto>> saveShopperDetails(){
        List<ShopperDto> shopperDto = internalService.saveShopperDetails();
        return new ResponseEntity<>(shopperDto, HttpStatus.CREATED);
    }
}
