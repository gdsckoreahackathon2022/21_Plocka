package com.jupging.jupgingServer.news.dto;


import com.jupging.jupgingServer.news.domain.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class GetNewsRes {

    private Long newsId;
    private String newsImage;
    private String title;
    private String contents;
    private String link;

    public GetNewsRes(News news){
        newsId = news.getId();
        newsImage = news.getImage();
        title = news.getTitle();
        contents = news.getContents();
        link = news.getUrl();
    }
}
