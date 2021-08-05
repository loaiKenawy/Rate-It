package com.movieApp.viewModels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.movieApp.models.moviesModel;
import com.movieApp.repositories.favoriteRepository;

import java.util.List;

public class favoriteViewModel extends AndroidViewModel {

    private favoriteRepository mRepo;
    private LiveData<List<moviesModel>> allMovies;


    public favoriteViewModel(@NonNull Application application) {
        super(application);

        mRepo = new favoriteRepository(application);
        allMovies = mRepo.getAllMovies();
    }

    public void remove(){
        allMovies = null;
    }

    public void add(moviesModel movie){
        mRepo.addToFavourite(movie);

    }

    public void removeMovie(moviesModel movie){
        mRepo.removeFavourite(movie);
    }

    public LiveData<List<moviesModel>> getAllMovies() {
        return allMovies;
    }

}
