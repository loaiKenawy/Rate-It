package com.movieApp.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.movieApp.favouriteDB.DAO;
import com.movieApp.favouriteDB.favouriteDb;
import com.movieApp.models.moviesModel;

import java.util.List;

public class favoriteRepository {

    private DAO moviesDao;
    private LiveData<List<moviesModel>> allMovies;

    public favoriteRepository(Application application){

        favouriteDb dataBase = favouriteDb.getInstance(application);
        moviesDao = dataBase.favouriteDAO();
        allMovies = moviesDao.getAllMovies();
    }

    public void addToFavourite(moviesModel movie){
        new InsertAsyncTask(moviesDao).execute(movie);

    }
    public void removeFavourite(moviesModel movie){

        new removeAsyncTask(moviesDao).execute(movie);
    }

    public LiveData<List<moviesModel>> getAllMovies(){
        return allMovies;
    }

    private class InsertAsyncTask extends AsyncTask<moviesModel , Void , Void>{

       private DAO movieDao;

        public InsertAsyncTask(DAO Dao) {
            this.movieDao = Dao;
        }

        @Override
        protected Void doInBackground(moviesModel... movies) {
            movieDao.addToFavourite(movies[0]);
            return null;
        }
    }
    private class removeAsyncTask extends AsyncTask<moviesModel , Void , Void>{

        private DAO movieDao;

        public removeAsyncTask(DAO Dao) {
            this.movieDao = Dao;
        }

        @Override
        protected Void doInBackground(moviesModel... movies) {
            movieDao.remove(movies[0]);
            return null;
        }
    }
}
