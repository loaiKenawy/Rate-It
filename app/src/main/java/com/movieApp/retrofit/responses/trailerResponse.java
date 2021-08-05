package com.movieApp.retrofit.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.movieApp.models.trailerModel;

public class trailerResponse {

    @SerializedName("results")
    @Expose()
    private trailerModel[] trailers;

    public trailerModel[] getTrailers() {
        return trailers;
    }
}
