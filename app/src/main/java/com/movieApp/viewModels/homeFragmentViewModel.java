package com.movieApp.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movieApp.models.moviesModel;
import com.movieApp.repositories.movieRepository;

import java.util.List;
import java.util.logging.Handler;

public class homeFragmentViewModel extends ViewModel {

    private movieRepository mRepo;

    private MutableLiveData<List<moviesModel>> topRatedMovies;
    private MutableLiveData<List<moviesModel>> popularMovies;
    private MutableLiveData<List<moviesModel>> nowPlayingMovies;


    public void init() {

        if (topRatedMovies != null && popularMovies != null) {
            return;
        }
        mRepo = movieRepository.getInstance();
        topRatedMovies = mRepo.requestTopRatedMoviesData();
        popularMovies = mRepo.requestPopularMoviesData();
        nowPlayingMovies = mRepo.requestNowPlayingMoviesData();

    }

    public MutableLiveData<List<moviesModel>> getTopRatedMoviesData() {
        return topRatedMovies;
    }

    public MutableLiveData<List<moviesModel>> getPopularMovies() {
        return popularMovies;
    }

    public MutableLiveData<List<moviesModel>> getNowPlayingMovies() {
        return nowPlayingMovies;
    }
}
