package com.example.news_app_backend.Services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.news_app_backend.Config.NewsApiConfig;
import com.example.news_app_backend.Exceptions.NewsApiException;
import com.example.news_app_backend.Exceptions.NewsServiceException;
import com.example.news_app_backend.Models.EverythingRequest;
import com.example.news_app_backend.Models.NewsResponse;
import com.example.news_app_backend.Models.TopHeadlinesRequest;
import com.example.news_app_backend.Utils.NewsResponseParser;

@Service
public class NewsService {
    
    private final RestTemplate restTemplate;
    private final NewsApiConfig config;
    private final NewsResponseParser newsResponseParser;
    private final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    public NewsService(RestTemplate restTemplate, NewsApiConfig config, NewsResponseParser newsResponseParser) {
        this.restTemplate = restTemplate;
        this.config = config;
        this.newsResponseParser = newsResponseParser;
    }

    public NewsResponse getTopHeadlines(TopHeadlinesRequest request) {
        String url = buildUrl("/top-headlines", request.toQueryParams());
        return fetchNews(url);
    }

    public NewsResponse getEverything(EverythingRequest request) {
        String url = buildUrl("/everything", request.toQueryParams());
        return fetchNews(url);
    }


    // Helper methods.
    private String buildUrl(String endpoint, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder
                                        .fromHttpUrl(config.getApiUrl() + endpoint)
                                        .queryParam("apiKey", config.getApiKey());
        queryParams.forEach(builder::queryParam);
        return builder.toUriString();
    }

    private NewsResponse fetchNews(String url) {
        try {
            RequestEntity<Void> request = RequestEntity
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();

            ResponseEntity<String> response = restTemplate.exchange(request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return newsResponseParser.parseNewsResponseData(response.getBody());
            } else {
                logger.error("Error response from NewsAPI. Status: {}", response.getStatusCode());
                throw new NewsServiceException("Failed to fetch news data. Status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error fetching news from API", e);
            throw new NewsApiException("Failed to fetch news", e);
        }
    }
}