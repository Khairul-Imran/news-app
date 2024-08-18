package com.example.news_app_backend.Exceptions;

public class NewsApiException extends RuntimeException {
    public NewsApiException(String message, Throwable cause) {
        super(message, cause);
    }
}