package com.movieApp.models;

public class reviewsModel {
    private String author ;
    private String content;

    public reviewsModel(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
