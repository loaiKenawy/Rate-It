package com.movieApp.viewModels;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movieApp.models.moviesModel;
import com.movieApp.models.reviewsModel;
import com.movieApp.models.trailerModel;
import com.movieApp.repositories.movieRepository;

import java.util.List;

public class detailsViewModel extends ViewModel {

    private movieRepository mRepo;
    private MutableLiveData<List<moviesModel>> similarMovies;
    private MutableLiveData<List<reviewsModel>> reviews;
    private MutableLiveData<List<trailerModel>> trailers;

    public void init(String id) {
        if (similarMovies != null) {
            return;
        }
        mRepo = movieRepository.getInstance();
        similarMovies = mRepo.requestSimilarMoviesData(id);
        reviews = mRepo.requestReviews(id);
        trailers = mRepo.requestTrailers(id);
    }
    public void remove(){
        similarMovies = null;
        reviews = null;
        trailers = null;
    }
    public MutableLiveData<List<moviesModel>> getSimilarMovies() {
        return similarMovies;
    }

    public MutableLiveData<List<reviewsModel>> getReviews() {
        return reviews;
    }

    public MutableLiveData<List<trailerModel>> getTrailers() {
        return trailers;
    }
}
