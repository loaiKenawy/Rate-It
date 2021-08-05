package com.movieApp.retrofit;

import com.movieApp.retrofit.responses.movieResponse;
import com.movieApp.retrofit.responses.reviewsResponse;
import com.movieApp.retrofit.responses.trailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSON_Holder {


    @GET("{movie_id}?api_key=272e194ff73e87f61a183dfd8570004e&language=en-US")
    Call<movieResponse> movies(
            @Path("movie_id") String id
    );

    @GET("{movie_id}/similar?api_key=272e194ff73e87f61a183dfd8570004e&language=en-Us")
    Call<movieResponse> similarMovies(
            @Path("movie_id") String id
    );

    @GET("{movie_id}/reviews?api_key=272e194ff73e87f61a183dfd8570004e&language=en-US")
    Call<reviewsResponse> reviews(
            @Path("movie_id") String id
    );

    @GET("{movie_id}/videos?api_key=272e194ff73e87f61a183dfd8570004e&language=en-US")
    Call<trailerResponse> videos(
            @Path("movie_id") String id
    );


}
