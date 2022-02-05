package com.jupging.jupgingServer.shop.service;

import com.jupging.jupgingServer.shop.dto.GetShopRes;
import java.util.List;

public interface ShopService {

    public List<GetShopRes> findShops();
}
