package com.movieApp.fragments;

import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieApp.R;
import com.movieApp.adapters.movieAdapter;
import com.movieApp.models.moviesModel;
import com.movieApp.viewModels.detailsViewModel;
import com.movieApp.viewModels.homeFragmentViewModel;

import java.util.List;


public class homeFragment extends Fragment {

    private View homeFragmentView;

    private homeFragmentViewModel mainViewModel;
    private detailsViewModel detailsViewModel;

    private RecyclerView topRatedMoviesRecyclerView;
    private movieAdapter topRatedAdapter;

    private RecyclerView popularMoviesRecyclerView;
    private movieAdapter popularMoviesAdapter;

    private RecyclerView nowPlayingMoviesRecyclerView;
    private movieAdapter nowPlayingMoviesAdapter;

    private movieDetailsFragment detailsFragment;

    private Bundle dataSender;

    private Handler mHandler = new Handler();

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = homeFragmentView.findViewById(R.id.progress_bar);

        topRatedMoviesRecyclerView = homeFragmentView.findViewById(R.id.top_rated_recyclerView);
        popularMoviesRecyclerView = homeFragmentView.findViewById(R.id.popular_movies_recyclerView);
        nowPlayingMoviesRecyclerView = homeFragmentView.findViewById(R.id.now_playing_recyclerView);


        mainViewModel = ViewModelProviders.of(getActivity()).get(homeFragmentViewModel.class);
        mainViewModel.init();

        getData();

        initTopRatedRecyclerView();
        initPopularRecyclerView();
        initNowPlayingRecyclerView();

        mHandler.postDelayed(this::controlProgressBar, 1500);

        return homeFragmentView;
    }


    private void initNowPlayingRecyclerView() {

        nowPlayingMoviesAdapter = new movieAdapter();
        nowPlayingMoviesRecyclerView.setAdapter(nowPlayingMoviesAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        nowPlayingMoviesRecyclerView.setLayoutManager(linearLayoutManager);
        nowPlayingMoviesAdapter.setOnClick(new movieAdapter.onCoverClick() {
            @Override
            public void onCoverClick(int position) {
                getDetails(nowPlayingMoviesAdapter.getMoviesList().get(position));
            }
        });

    }

    private void initTopRatedRecyclerView() {

        topRatedAdapter = new movieAdapter();
        topRatedMoviesRecyclerView.setAdapter(topRatedAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        topRatedMoviesRecyclerView.setLayoutManager(linearLayoutManager);
        topRatedAdapter.setOnClick(new movieAdapter.onCoverClick() {
            @Override
            public void onCoverClick(int position) {
                getDetails(topRatedAdapter.getMoviesList().get(position));
            }
        });
    }

    private void initPopularRecyclerView() {

        popularMoviesAdapter = new movieAdapter();
        popularMoviesRecyclerView.setAdapter(popularMoviesAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        popularMoviesRecyclerView.setLayoutManager(linearLayoutManager);
        popularMoviesAdapter.setOnClick(new movieAdapter.onCoverClick() {
            @Override
            public void onCoverClick(int position) {
                getDetails(popularMoviesAdapter.getMoviesList().get(position));
            }
        });

    }

    private void getData() {

        mainViewModel.getTopRatedMoviesData().observe(getViewLifecycleOwner(), new Observer<List<moviesModel>>() {
            @Override
            public void onChanged(List<moviesModel> movies) {
                topRatedAdapter.setMoviesList(movies);
            }
        });

        mainViewModel.getPopularMovies().observe(getViewLifecycleOwner(), new Observer<List<moviesModel>>() {
            @Override
            public void onChanged(List<moviesModel> movies) {
                popularMoviesAdapter.setMoviesList(movies);
            }
        });

        mainViewModel.getNowPlayingMovies().observe(getViewLifecycleOwner(), new Observer<List<moviesModel>>() {
            @Override
            public void onChanged(List<moviesModel> movies) {
                nowPlayingMoviesAdapter.setMoviesList(movies);
            }
        });

    }

    private void getDetails(moviesModel movie) {

        detailsFragment = new movieDetailsFragment();
        dataSender = new Bundle();

        detailsViewModel = ViewModelProviders.of(getActivity()).get(detailsViewModel.class);
        detailsViewModel.remove();

        dataSender.putString("title", movie.getTitle());
        dataSender.putString("cover", movie.getPoster_path());
        dataSender.putString("overview", movie.getOverview());
        dataSender.putString("id", movie.getId());

        detailsFragment.setArguments(dataSender);
        getFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragment_container, detailsFragment).commit();

    }

    private void controlProgressBar() {
        progressBar.setVisibility(View.GONE);
        topRatedAdapter.notifyDataSetChanged();
        popularMoviesAdapter.notifyDataSetChanged();
        nowPlayingMoviesAdapter.notifyDataSetChanged();
    }

}