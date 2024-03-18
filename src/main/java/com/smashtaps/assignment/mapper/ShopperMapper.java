package com.smashtaps.assignment.mapper;

import com.smashtaps.assignment.dto.ProductDto;
import com.smashtaps.assignment.dto.ShelfDto;
import com.smashtaps.assignment.dto.ShopperDto;
import com.smashtaps.assignment.entity.Product;
import com.smashtaps.assignment.entity.Shelf;
import com.smashtaps.assignment.entity.Shopper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShopperMapper {
    ShopperDto shopperToShopperDto(Shopper shopper);
    Shopper shopperDtoToShopper(ShopperDto shopperDto);

    @Mapping(target = "shopper", ignore = true)
    ShelfDto shelfToShelfDto(Shelf shelf);
    Shelf shelfDtoToShelf(ShelfDto shelfDto);

    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
}
