package com.jupging.jupgingServer.shop.service;

import com.jupging.jupgingServer.shop.domain.Shop;
import com.jupging.jupgingServer.shop.dto.GetShopRes;
import com.jupging.jupgingServer.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GetShopRes> findShops(){
        List<Shop> shopList = shopRepository.findAll();
        List<GetShopRes> shopDtoList = new ArrayList<>();
        for (Shop shop : shopList)
            shopDtoList.add(new GetShopRes(shop));
        return shopDtoList;
    }
}
