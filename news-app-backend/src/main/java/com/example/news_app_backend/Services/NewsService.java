package com.example.news_app_backend.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.news_app_backend.Config.NewsApiConfig;
import com.example.news_app_backend.Models.EverythingRequest;
import com.example.news_app_backend.Models.NewsResponse;
import com.example.news_app_backend.Models.TopHeadlinesRequest;
import com.example.news_app_backend.Utils.NewsResponseParser;

@Service
public class NewsService {
    
    private final RestTemplate restTemplate;
    private final NewsApiConfig config;
    private final NewsResponseParser newsDataParser;
    private final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    public NewsService(RestTemplate restTemplate, NewsApiConfig config, NewsResponseParser newsDataParser) {
        this.restTemplate = restTemplate;
        this.config = config;
        this.newsDataParser = newsDataParser;
    }

    public NewsResponse getTopHeadlines(TopHeadlinesRequest request) {
        
    }

    public NewsResponse getEverything(EverythingRequest request) {
        
    }


    // Helper methods.
    private NewsResponse fetchNews(String url) {

    }

    private String buildUrl() {
        
    }


}
