package com.example.news_app_backend.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.news_app_backend.Exceptions.NewsServiceException;
import com.example.news_app_backend.Models.EverythingRequest;
import com.example.news_app_backend.Models.NewsResponse;
import com.example.news_app_backend.Models.TopHeadlinesRequest;
import com.example.news_app_backend.Services.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    
    private final NewsService newsService;
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/top-headlines")
    public ResponseEntity<?> getTopHeadlines(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sources,
            @RequestParam(required = false) String q) {
                
                TopHeadlinesRequest request = new TopHeadlinesRequest()
                    .setCountry(country)
                    .setCategory(category)
                    .setSources(sources)
                    .setQ(q);
                
                logger.info("Received request for news: {}", request);

                try {
                    NewsResponse response = newsService.getTopHeadlines(request);
                    logger.info("Successfully retrieved news data for top headlines: {}", request);
                    return ResponseEntity.ok(response);
                } catch (NewsServiceException e) {
                    logger.error("Error fetching news data for everything: {}", request);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching news data: " + e.getMessage());
                }

    }

    @GetMapping("/everything")
    public ResponseEntity<?> getEverything(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String sources,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String sortBy) {

                EverythingRequest request = new EverythingRequest()
                    .setQ(q)
                    .setSources(sources)
                    .setFrom(from)
                    .setTo(to)
                    .setLanguage(language)
                    .setSortBy(sortBy);
                
                logger.info("Received request for news: {}", request);

                try {
                    NewsResponse response = newsService.getEverything(request);
                    logger.info("Successfully retrieved news data for top headlines: {}", request);
                    return ResponseEntity.ok(response);
                } catch (NewsServiceException e) {
                    logger.error("Error fetching news data for everything: {}", request);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching news data: " + e.getMessage());
                }

    }
}
