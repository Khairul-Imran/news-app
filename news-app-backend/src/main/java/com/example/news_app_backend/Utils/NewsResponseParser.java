package com.example.news_app_backend.Utils;

import java.io.StringReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.news_app_backend.Exceptions.NewsDataParsingException;
import com.example.news_app_backend.Models.NewsArticle;
import com.example.news_app_backend.Models.NewsResponse;
import com.example.news_app_backend.Models.Source;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Component
public class NewsResponseParser {
    private static final Logger logger = LoggerFactory.getLogger(NewsResponseParser.class);

    public NewsResponse parseNewsResponseData(String jsonPayload) throws NewsDataParsingException {
        try(JsonReader jsonReader = Json.createReader(new StringReader(jsonPayload))) {
            JsonObject jsonObject = jsonReader.readObject();

            String status = parseStatus(jsonObject);
            int totalResults = parseTotalResults(jsonObject);
            List<NewsArticle> articles = parseNewsArticles(jsonObject);

            return new NewsResponse(status, totalResults, articles);
        } catch (JsonException e) {
            logger.error("Error parsing JSON data", e);
            throw new NewsDataParsingException("Error parsing JSON data", e);

        } catch (Exception e) {
            logger.error("Unexpected error while parsing news data", e);
            throw new NewsDataParsingException("Unexpected error while parsing news data", e);
        }
    }

    private List<NewsArticle> parseNewsArticles(JsonObject jsonObject) {
        List<NewsArticle> articles = new ArrayList<>();
        
        JsonArray articlesArray = jsonObject.getJsonArray("articles");
        for (JsonValue articleValue : articlesArray) {
            JsonObject articleJson = articleValue.asJsonObject();
            
            Source source = parseSource(articleJson);
            String author = parseAuthor(articleJson);
            String title = parseTitle(articleJson);
            String description = parseDescription(articleJson);
            String url = parseUrl(articleJson);
            String urlToImage = parseUrlToImage(articleJson);
            Instant publishedAt = parsePublishedAt(articleJson);
            String content = parseContent(articleJson);

            articles.add(new NewsArticle(source, author, title, description, url, urlToImage, publishedAt, content));
        }
        
        return articles;
    }
    
    private String parseStatus(JsonObject jsonObject) {
        return jsonObject.getString("status", "");
    }

    private int parseTotalResults(JsonObject jsonObject) {
        return jsonObject.getInt("totalResults");
    }

    // From "articles" JsonArray
    private Source parseSource(JsonObject articleJson) {
        String id = articleJson.getJsonObject("source").getString("id", "");
        String name = articleJson.getJsonObject("source").getString("name", "");

        return new Source(id, name);
    }

    private String parseAuthor(JsonObject articleJson) {
        return articleJson.getString("author", "");
    }

    private String parseTitle(JsonObject articleJson) {
        return articleJson.getString("title", "");
    }

    private String parseDescription(JsonObject articleJson) {
        return articleJson.getString("description", "");
    }

    private String parseUrl(JsonObject articleJson) {
        return articleJson.getString("url", "");
    }

    private String parseUrlToImage(JsonObject articleJson) {
        return articleJson.getString("urlToImage", "");
    }

    private Instant parsePublishedAt(JsonObject articleJson) {
        return Instant.parse(articleJson.getString("publishedAt", ""));
    }

    private String parseContent(JsonObject articleJson) {
        return articleJson.getString("content", "");
    }
}