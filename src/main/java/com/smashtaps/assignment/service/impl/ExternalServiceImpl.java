package com.smashtaps.assignment.service.impl;

import com.smashtaps.assignment.dto.ShopperDto;
import com.smashtaps.assignment.entity.Shopper;
import com.smashtaps.assignment.exception.BadRequestException;
import com.smashtaps.assignment.mapper.ShopperMapper;
import com.smashtaps.assignment.repository.ShopperRepository;
import com.smashtaps.assignment.service.ExternalService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ExternalServiceImpl implements ExternalService {
    @Autowired
    private ShopperRepository shopperRepository;

    private final ShopperMapper shopperMapper = Mappers.getMapper(ShopperMapper.class);

    @Override
    public Page<ShopperDto> filterShopperDetails(String shopperId, String category, String brand, int page, int size) {
        if(StringUtils.isEmpty(shopperId)){
            throw new BadRequestException("Shopper id is mandatory");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Shopper> shoppers = shopperRepository.filterShopperDetails(shopperId, category, brand, pageable);
        return shoppers.map(shopper -> shopperMapper.shopperToShopperDto(shopper));
    }
}
