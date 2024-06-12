package com.literlaura.alura_challenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksAPI {
    @JsonProperty("title")
    private String title;
    @JsonProperty("authors")
    private List<AuthorsAPI> author;
    @JsonProperty("languages")
    private List<String> language;
    @JsonProperty("download_count")
    private Long downloads;

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorsAPI> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorsAPI> author) {
        this.author = author;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "DataBooks{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", lang=" + language +
                ", downloads=" + downloads +
                '}';
    }
}
