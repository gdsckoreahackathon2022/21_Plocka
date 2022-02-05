package com.jupging.jupgingServer.shop.controller;


import com.jupging.jupgingServer.common.BaseResponse;
import com.jupging.jupgingServer.shop.dto.GetShopRes;
import com.jupging.jupgingServer.shop.service.ShopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopServiceImpl shopService;

    /**
     * 물건 조회 API
     * [GET] /shop
     * 개발자 : 홍민주
     */
    @GetMapping("")
    public BaseResponse<List<GetShopRes>> getShops(){
        List<GetShopRes> shopList = shopService.findShops();
        return new BaseResponse<>(shopList);
    }
}
