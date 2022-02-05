package com.jupging.jupgingServer.news.service;

import com.jupging.jupgingServer.news.domain.News;
import com.jupging.jupgingServer.news.dto.GetNewsRes;
import com.jupging.jupgingServer.news.repository.NewsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GetNewsRes> findNews(){
        List<News> newsList = newsRepository.findAll();
        List<GetNewsRes> newsDtoList = new ArrayList<>();
        for (News news : newsList)
            newsDtoList.add(new GetNewsRes(news));
        return newsDtoList;
    }
}
