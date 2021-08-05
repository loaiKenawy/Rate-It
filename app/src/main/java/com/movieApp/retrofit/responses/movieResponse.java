package com.movieApp.retrofit.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.movieApp.models.moviesModel;

public class movieResponse {

    @SerializedName("results")
    @Expose()
    private moviesModel[] moviesArray;

    public moviesModel[] getMoviesArray() {
        return moviesArray;
    }
}
