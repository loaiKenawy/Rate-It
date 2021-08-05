package com.movieApp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.PropertyKey;

@Entity(tableName = "movies")
public class moviesModel {

    @PrimaryKey(autoGenerate = true)
    private int dbID;

    private String id;

    private String poster_path;

    private String title;

    private String overview;

    public moviesModel(String id, String poster_path, String title, String overview) {

        this.id = id;
        this.poster_path = poster_path;
        this.title = title;
        this.overview = overview;
    }

    public String getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public int getDbID() {
        return dbID;
    }

    public void setDbID(int dbID) {
        this.dbID = dbID;
    }
}
