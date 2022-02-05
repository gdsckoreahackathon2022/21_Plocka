package com.jupging.jupgingServer.news.service;

import com.jupging.jupgingServer.news.dto.GetNewsRes;

import java.util.List;

public interface NewsService {
    public List<GetNewsRes> findNews();
}
