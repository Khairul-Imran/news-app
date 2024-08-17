package com.example.news_app_backend.Exceptions;

public class NewsServiceException extends RuntimeException {
    public NewsServiceException(String message) {
        super(message);
    }

    public NewsServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}