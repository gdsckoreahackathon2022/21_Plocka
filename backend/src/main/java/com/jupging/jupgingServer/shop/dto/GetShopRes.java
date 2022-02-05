package com.jupging.jupgingServer.shop.dto;

import com.jupging.jupgingServer.shop.domain.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetShopRes {

    private Long productId;
    private String Image;
    private String title;
    private  int price;
    private String link;

    public GetShopRes(Shop shop){
        productId = shop.getId();
        Image = shop.getImage();
        title = shop.getName();
        price = shop.getPrice();
        link = shop.getUrl();
    }
}
