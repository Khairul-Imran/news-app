package com.example.news_app_backend.Models;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopHeadlinesRequest {

    private String country; // Note: This can't be used with sources.
    private String category; // Note: This can't be used with sources.
    private String sources; 
    private String q;
    // For now not using page

    public TopHeadlinesRequest setCountry(String country) {
        this.country = country;
        return this;
    }

    public TopHeadlinesRequest setCategory(String category) {
        this.category = category;
        return this;
    }

    public TopHeadlinesRequest setSources(String sources) {
        this.sources = sources;
        return this;
    }

    public TopHeadlinesRequest setQ(String q) {
        this.q = q;
        return this;
    }

    public Map<String, String> toQueryParams() {
        Map<String, String> params = new HashMap<>();
        if (country != null) params.put("country", country);
        if (category != null) params.put("category", category);
        if (sources != null) params.put("sources", sources);
        if (q != null) params.put("q", q);

        return params;
    }
}
