package com.example.news_app_backend.Exceptions;

public class NewsDataParsingException extends Exception {
    public NewsDataParsingException(String message) {
        super(message);
    }

    public NewsDataParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsDataParsingException(Throwable cause) {
        super(cause);
    }

    public NewsDataParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}