package com.example.news_app_backend.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
    
    private String status;
    private int totalResults;
    private List<NewsArticle> articles;

}