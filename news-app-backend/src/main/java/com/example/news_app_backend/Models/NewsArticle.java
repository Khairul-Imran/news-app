package com.example.news_app_backend.Models;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticle {
    
    private Source source;

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private Instant publishedAt;
    private String content;

}