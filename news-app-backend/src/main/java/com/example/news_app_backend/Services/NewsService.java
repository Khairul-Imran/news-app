package com.example.news_app_backend.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.news_app_backend.Config.NewsApiConfig;
import com.example.news_app_backend.Utils.NewsDataParser;

@Service
public class NewsService {
    
    private final RestTemplate restTemplate;
    private final NewsApiConfig config;
    private final NewsDataParser newsDataParser;
    private final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    public NewsService(RestTemplate restTemplate, NewsApiConfig config, NewsDataParser weatherDataParser) {
        this.restTemplate = restTemplate;
        this.config = config;
        this.newsDataParser = weatherDataParser;
    }

}
