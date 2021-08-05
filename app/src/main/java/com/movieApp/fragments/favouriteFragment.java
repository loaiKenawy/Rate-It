package com.movieApp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieApp.R;
import com.movieApp.adapters.movieAdapter;
import com.movieApp.models.moviesModel;
import com.movieApp.viewModels.detailsViewModel;
import com.movieApp.viewModels.favoriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class favouriteFragment extends Fragment {

    private View fav_view;

    private favoriteViewModel favViewModel;
    private detailsViewModel detailsViewModel;

    private RecyclerView favRecyclerView;
    private movieAdapter adapter;

    private movieDetailsFragment detailsFragment;

    private Bundle dataSender;

    private ArrayList<moviesModel> modelList = new ArrayList<>();

    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fav_view = inflater.inflate(R.layout.fragment_favourite, container, false);
        favRecyclerView = fav_view.findViewById(R.id.fav_recycler_view);

        getFavourite();

        initRecyclerView();

        handler.postDelayed(this::notifyData, 1000);

        return fav_view;

    }

    private void initRecyclerView() {

        adapter = new movieAdapter();
        favRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));

        favRecyclerView.setAdapter(adapter);
        adapter.setMoviesList(modelList);

        adapter.setOnClick(new movieAdapter.onCoverClick() {
            @Override
            public void onCoverClick(int position) {
                getDetails(adapter.getMoviesList().get(position));
            }
        });

        adapter.setCoverLongClick(new movieAdapter.onCoverLongClick() {

            @Override
            public void onCoverLongClick(int position) {

                favViewModel.removeMovie(adapter.getMoviesList().get(position));
                adapter.notifyItemRemoved(position);

            }
        });
    }

    private void getFavourite() {
        favViewModel = ViewModelProviders.of(getActivity()).get(favoriteViewModel.class);
        favViewModel.getAllMovies().observe(getViewLifecycleOwner(), new Observer<List<moviesModel>>() {
            @Override
            public void onChanged(List<moviesModel> movies) {
                adapter.setMoviesList(movies);
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

    private void notifyData() {
        adapter.notifyDataSetChanged();

    }



}
