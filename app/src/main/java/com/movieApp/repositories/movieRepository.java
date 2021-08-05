package com.movieApp.repositories;


import androidx.lifecycle.MutableLiveData;

import com.movieApp.models.moviesModel;
import com.movieApp.models.reviewsModel;
import com.movieApp.models.trailerModel;
import com.movieApp.retrofit.API_requestHandler;

import java.util.List;

public class movieRepository {

    private static final String TAG = "MOVIE REPOSITORY";
    private static movieRepository instance;

    private API_requestHandler dataHandler = new API_requestHandler();

    private List<moviesModel> topRatedList = dataHandler.retrieveTopRatedData();
    private List<moviesModel> popularList = dataHandler.retrievePopular();
    private List<moviesModel> nowPlayingList = dataHandler.retrieveNowPlaying();


    public static movieRepository getInstance() {
        if (instance == null) {
            instance = new movieRepository();
        }
        instance = new movieRepository();
        return instance;
    }

    public MutableLiveData<List<moviesModel>> requestTopRatedMoviesData() {
        MutableLiveData<List<moviesModel>> data = new MutableLiveData<>();
        data.setValue(topRatedList);
        return data;
    }

    public MutableLiveData<List<moviesModel>> requestPopularMoviesData() {
        MutableLiveData<List<moviesModel>> data = new MutableLiveData<>();
        data.setValue(popularList);
        return data;
    }

    public MutableLiveData<List<moviesModel>> requestNowPlayingMoviesData() {
        MutableLiveData<List<moviesModel>> data = new MutableLiveData<>();
        data.setValue(nowPlayingList);
        return data;
    }

    public MutableLiveData<List<moviesModel>> requestSimilarMoviesData(String id) {
        List<moviesModel> similarList = dataHandler.retrieveSimilarMovies(id);
        MutableLiveData<List<moviesModel>> data = new MutableLiveData<>();
        data.setValue(similarList);
        return data;
    }
    public MutableLiveData<List<reviewsModel>> requestReviews(String id ){
        List<reviewsModel> reviewsModels = dataHandler.retrieveReviews(id);
        MutableLiveData<List<reviewsModel>> data = new MutableLiveData<>();
        data.setValue(reviewsModels);
        return data;
    }
    public MutableLiveData<List<trailerModel>> requestTrailers(String id){
        List<trailerModel> trailerModels = dataHandler.retrieveTrailers(id);
        MutableLiveData<List<trailerModel>> data = new MutableLiveData<>();
        data.setValue(trailerModels);
        return data;
    }


}
