package com.example.news_app_backend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsData {
    
    private NewsArticle newsArticle;
    private Source source;

}
