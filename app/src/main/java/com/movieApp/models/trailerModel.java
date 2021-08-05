package com.movieApp.models;

public class trailerModel {

    private String key;
    private String name;

    public trailerModel(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
