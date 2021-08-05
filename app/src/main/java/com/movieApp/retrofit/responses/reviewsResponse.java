package com.movieApp.retrofit.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.movieApp.models.reviewsModel;

public class reviewsResponse {

    @SerializedName("results")
    @Expose()
    private reviewsModel[] reviewsArray;

    public reviewsModel[] getReviewsArray() {
        return reviewsArray;
    }
}
