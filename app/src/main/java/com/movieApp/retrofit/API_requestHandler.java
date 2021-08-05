package com.movieApp.retrofit;

import com.movieApp.models.moviesModel;
import com.movieApp.models.reviewsModel;
import com.movieApp.models.trailerModel;
import com.movieApp.retrofit.responses.movieResponse;
import com.movieApp.retrofit.responses.reviewsResponse;
import com.movieApp.retrofit.responses.trailerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_requestHandler {

    private static final String TAG = "dataHandler!!!";
    private static final String baseURL = "https://api.themoviedb.org/3/movie/";

    private ArrayList<moviesModel> popularList = new ArrayList<>();
    private ArrayList<moviesModel> topRatedList = new ArrayList<>();
    private ArrayList<moviesModel> nowPlayingList = new ArrayList<>();
    private ArrayList<moviesModel> similarList = new ArrayList<>();
    private ArrayList<reviewsModel> reviewsList = new ArrayList<>();
    private ArrayList<trailerModel> trailersList = new ArrayList<>();


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private JSON_Holder holder = retrofit.create(JSON_Holder.class);


    public ArrayList<moviesModel> retrieveTopRatedData() {

        final Call<movieResponse> topRatedCall = holder.movies("top_rated");

        topRatedCall.enqueue(new Callback<movieResponse>() {
            @Override
            public void onResponse(Call<movieResponse> call, Response<movieResponse> response) {
                response.body().getMoviesArray();
                for (moviesModel movie : response.body().getMoviesArray()) {
                    topRatedList.add(movie);
                }
            }

            @Override
            public void onFailure(Call<movieResponse> call, Throwable t) {

            }
        });
        return topRatedList;
    }

    public ArrayList<moviesModel> retrievePopular() {

        final Call<movieResponse> popularCall = holder.movies("popular");

        popularCall.enqueue(new Callback<movieResponse>() {
            @Override
            public void onResponse(Call<movieResponse> call, Response<movieResponse> response) {
                response.body().getMoviesArray();
                for (moviesModel movie : response.body().getMoviesArray()) {
                    popularList.add(movie);
                }
            }

            @Override
            public void onFailure(Call<movieResponse> call, Throwable t) {

            }
        });
        return popularList;
    }

    public ArrayList<moviesModel> retrieveNowPlaying() {


        final Call<movieResponse> popularCall = holder.movies("now_playing");

        popularCall.enqueue(new Callback<movieResponse>() {
            @Override
            public void onResponse(Call<movieResponse> call, Response<movieResponse> response) {
                response.body().getMoviesArray();
                for (moviesModel movie : response.body().getMoviesArray()) {
                    nowPlayingList.add(movie);
                }
            }

            @Override
            public void onFailure(Call<movieResponse> call, Throwable t) {

            }
        });
        return nowPlayingList;
    }

    public ArrayList<moviesModel> retrieveSimilarMovies(String id) {

        final Call<movieResponse> similarCall = holder.similarMovies(id);
        similarCall.enqueue(new Callback<movieResponse>() {
            @Override
            public void onResponse(Call<movieResponse> call, Response<movieResponse> response) {
                response.body().getMoviesArray();
                for (moviesModel movie : response.body().getMoviesArray()) {
                    similarList.add(movie);
                }
            }

            @Override
            public void onFailure(Call<movieResponse> call, Throwable t) {

            }
        });
        return similarList;
    }

    public ArrayList<reviewsModel> retrieveReviews(String id) {

        final Call<reviewsResponse> reviewsCall = holder.reviews(id);
        reviewsCall.enqueue(new Callback<reviewsResponse>() {
            @Override
            public void onResponse(Call<reviewsResponse> call, Response<reviewsResponse> response) {
                response.body().getReviewsArray();
                for (reviewsModel reviews : response.body().getReviewsArray()) {
                    reviewsList.add(reviews);
                }
            }

            @Override
            public void onFailure(Call<reviewsResponse> call, Throwable t) {

            }
        });
        return reviewsList;
    }

    public ArrayList<trailerModel> retrieveTrailers(String id) {
        final Call<trailerResponse> trailersCall = holder.videos(id);
        trailersCall.enqueue(new Callback<trailerResponse>() {
            @Override
            public void onResponse(Call<trailerResponse> call, Response<trailerResponse> response) {
                response.body().getTrailers();
                if (response.body().getTrailers() != null) {
                    for (trailerModel video : response.body().getTrailers()) {
                        trailersList.add(video);
                    }
                } else {
                    trailersList = null;
                }
            }

            @Override
            public void onFailure(Call<trailerResponse> call, Throwable t) {

            }
        });
        return trailersList;
    }

}
