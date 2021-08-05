package com.movieApp.favouriteDB;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.movieApp.models.moviesModel;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void addToFavourite(moviesModel movie);

    @Query("SELECT * FROM movies")
    LiveData<List<moviesModel>> getAllMovies();

    @Delete
    void remove(moviesModel movie);


}

