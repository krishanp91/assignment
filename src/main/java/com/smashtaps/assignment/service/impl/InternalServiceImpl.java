package com.smashtaps.assignment.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smashtaps.assignment.dto.ShopperDto;
import com.smashtaps.assignment.entity.Product;
import com.smashtaps.assignment.entity.Shelf;
import com.smashtaps.assignment.entity.Shopper;
import com.smashtaps.assignment.exception.InvalidJsonException;
import com.smashtaps.assignment.mapper.ShopperMapper;
import com.smashtaps.assignment.model.ProductModel;
import com.smashtaps.assignment.model.ShelfModel;
import com.smashtaps.assignment.model.ShopperModel;
import com.smashtaps.assignment.repository.ProductRepository;
import com.smashtaps.assignment.repository.ShopperRepository;
import com.smashtaps.assignment.service.InternalService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InternalServiceImpl implements InternalService {
    @Value("classpath:data/shopper_data.json")
    Resource shopperData;
    @Value("classpath:data/product_data.json")
    Resource productData;

    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private ProductRepository productRepository;

    private final ShopperMapper shopperMapper = Mappers.getMapper(ShopperMapper.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ShopperDto> saveShopperDetails() {
        List<Shopper> shoppers = createShoppersList();
        shoppers = shopperRepository.saveAll(shoppers);
        return shoppers.stream().map(shopper -> shopperMapper.shopperToShopperDto(shopper)).collect(Collectors.toList());
    }

    private List<Shopper> createShoppersList() {
        try {
            List<ShopperModel> shopperModels = objectMapper.readValue(shopperData.getFile(),
                    new TypeReference<List<ShopperModel>>() {
                    });
            List<ProductModel> productModels = objectMapper.readValue(productData.getFile(),
                    new TypeReference<List<ProductModel>>() {
                    });
            List<Shopper> shoppers = shopperModels.stream().map(shopperModel -> {
                Shopper shopper = getShopper(shopperModel.getShopperId());
                Set<Shelf> existingShelves = shopper.getShelves();
                Set<Shelf> shelves = createShelfSet(shopperModel, shopper, productModels);
                existingShelves.addAll(shelves);
                shopper.setShelves(existingShelves);
                return shopper;
            }).collect(Collectors.toList());

            return shoppers;
        } catch (IOException e) {
            throw new InvalidJsonException(e.getMessage());
        }
    }

    private Shopper getShopper(String shopperId){
        Optional<Shopper> existingShopper = shopperRepository.findByShopperId(shopperId);
        if(existingShopper.isPresent()){
            return existingShopper.get();
        }else{
            Shopper shopper = new Shopper();
            shopper.setShopperId(shopperId);
            shopper.setShelves(new HashSet<>());
            return shopper;
        }
    }

    private Set<Shelf> createShelfSet(ShopperModel shopperModel, Shopper shopper, List<ProductModel> productModels){
        return shopperModel.getShelf()
                .stream()
                .map(createShelfEntity(shopper, productModels))
                .collect(Collectors.toSet());
    }

    private Function<ShelfModel, Shelf> createShelfEntity(Shopper shopper, List<ProductModel> productModels) {
        return shelfModel -> {
            Product product =
                    productModels.stream().filter(model ->
                                    model.getProductId().equals(shelfModel.getProductId()))
                            .map(getProduct())
                            .findFirst()
                            .orElse(null);
            return new Shelf(shelfModel.getRelevancyScore(), product, shopper);
        };
    }

    private Function<ProductModel, Product> getProduct(){
        return model -> {
            Optional<Product> existProduct = productRepository.findByProductId(model.getProductId());
            if(existProduct.isPresent()) {
                return existProduct.get();
            }else {
                return new Product(model.getProductId(), model.getCategory(), model.getBrand());
            }
        };
    }
}
