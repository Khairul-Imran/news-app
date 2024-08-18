package com.example.news_app_backend.Models;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EverythingRequest {

    // The fields are limited for simplicity.
    private String q;
    private String sources;
    private String from; // Date for oldest article allowed
    private String to; // Date for newest article allowed
    private String language;
    private String sortBy;
    // For now not using page

    public EverythingRequest setQ(String q) {
        this.q = q;
        return this;
    }

    public EverythingRequest setSources (String sources) {
        this.sources = sources;
        return this;
    }

    public EverythingRequest setFrom (String from) {
        this.from = from;
        return this;
    }

    public EverythingRequest setTo (String to) {
        this.to = to;
        return this;
    }

    public EverythingRequest setLanguage (String language) {
        this.language = language;
        return this;
    }

    public EverythingRequest setSortBy (String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public Map<String, String> toQueryParams() {
        Map<String, String> params = new HashMap<>();
        if (q != null) params.put("q", q);
        if (sources != null) params.put("sources", sources);
        if (from != null) params.put("from", from);
        if (to != null) params.put("to", to);
        if (language != null) params.put("language", language);
        if (sortBy != null) params.put("sortBy", sortBy);

        return params;
    }
}