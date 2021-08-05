package com.movieApp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.movieApp.R;
import com.movieApp.activities.trailerActivity;
import com.movieApp.adapters.movieAdapter;
import com.movieApp.adapters.reviewAdapter;
import com.movieApp.adapters.trailerAdapter;
import com.movieApp.models.moviesModel;
import com.movieApp.models.reviewsModel;
import com.movieApp.models.trailerModel;
import com.movieApp.viewModels.detailsViewModel;
import com.movieApp.viewModels.favoriteViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class movieDetailsFragment extends Fragment {

    private static final String TAG = "Details Fragment ";

    private View movieDetailsView;

    private Bundle dataSender;

    private favoriteViewModel favoriteViewModel;
    private detailsViewModel detailsViewModel;

    private TextView title, overView;

    private ImageView cover;

    private RecyclerView trailersRecyclerView;
    private trailerAdapter trailersAdapter;

    private RecyclerView similarMoviesRecyclerView;
    private movieAdapter similarMoviesAdapter;

    private RecyclerView reviewsRecyclerView;
    private reviewAdapter reviewAdapter;

    private BottomNavigationView bottomNavigationView;

    private Handler handler = new Handler();

    private boolean isExists = false;

    private String mTitle, mCover, mOverview, mId;

    private ArrayList<reviewsModel> testing = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        movieDetailsView = inflater.inflate(R.layout.fragment_movie_details, container, false);


        mTitle = getArguments().getString("title");
        mCover = getArguments().getString("cover");
        mOverview = getArguments().getString("overview");
        mId = getArguments().getString("id");

        favoriteViewModel = ViewModelProviders.of(getActivity()).get(favoriteViewModel.class);

        bottomNavigationView = movieDetailsView.findViewById(R.id.details_menu);

        similarMoviesRecyclerView = movieDetailsView.findViewById(R.id.similar_recycler_view);
        reviewsRecyclerView = movieDetailsView.findViewById(R.id.reviews_recycler_view);
        trailersRecyclerView = movieDetailsView.findViewById(R.id.trailers_recycler_view);

        getData();

        initTrailersRecyclerView();
        initSimilarRecyclerView();
        initReviewsRecyclerView();

        setDataToView();

        handler.postDelayed(this::NOTIFY, 2000);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        return movieDetailsView;
    }

    private void setDataToView() {

        title = movieDetailsView.findViewById(R.id.movie_title);
        overView = movieDetailsView.findViewById(R.id.overView);
        cover = movieDetailsView.findViewById(R.id.movie_cover);

        title.setText(mTitle);
        overView.setText(mOverview);
        Picasso.get().load("https://image.tmdb.org/t/p/original" + mCover).fit().into(cover);
    }

    private void getFavourite() {

        favoriteViewModel.getAllMovies().observe(getViewLifecycleOwner(), new Observer<List<moviesModel>>() {
            @Override
            public void onChanged(List<moviesModel> moviesModels) {
                for (moviesModel models : moviesModels) {
                    if (models.getId().equals(mId)) {
                        isExists = true;
                        break;
                    }
                }
            }
        });

    }

    private void addToFavourite() {

        getFavourite();

        if (isExists == true) {
            Toast.makeText(getContext(), "Already exist", Toast.LENGTH_SHORT).show();
        } else {
            favoriteViewModel.add(new
                    moviesModel(
                    mId,
                    mCover,
                    mTitle,
                    mOverview
            ));
            Toast.makeText(getContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
        }
    }

    private void initSimilarRecyclerView() {

        similarMoviesAdapter = new movieAdapter();
        similarMoviesRecyclerView.setAdapter(similarMoviesAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        similarMoviesRecyclerView.setLayoutManager(linearLayoutManager);
        similarMoviesAdapter.setOnClick(new movieAdapter.onCoverClick() {
            @Override
            public void onCoverClick(int position) {
                getDetails(similarMoviesAdapter.getMoviesList().get(position));
            }
        });

    }

    private void initReviewsRecyclerView() {

        reviewAdapter = new reviewAdapter();
        reviewsRecyclerView.setAdapter(reviewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        reviewsRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initTrailersRecyclerView() {

        trailersAdapter = new trailerAdapter();
        trailersRecyclerView.setAdapter(trailersAdapter);
        trailersAdapter.setCover("https://image.tmdb.org/t/p/original" + mCover);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        trailersRecyclerView.setLayoutManager(linearLayoutManager);
        trailersAdapter.setOnClick(new trailerAdapter.onCoverClick() {
            @Override
            public void onCoverClick(int position) {
                getTrailer(trailersAdapter.getTrailersList().get(position).getKey());
            }
        });

    }

    private void getData() {

        detailsViewModel = ViewModelProviders.of(getActivity()).get(detailsViewModel.class);
        detailsViewModel.init(mId);

        detailsViewModel.getSimilarMovies().observe(getViewLifecycleOwner(), new Observer<List<moviesModel>>() {
            @Override
            public void onChanged(List<moviesModel> moviesModels) {
                similarMoviesAdapter.setMoviesList(moviesModels);

            }
        });

        detailsViewModel.getReviews().observe(getViewLifecycleOwner(), new Observer<List<reviewsModel>>() {
            @Override
            public void onChanged(List<reviewsModel> reviewsModels) {
                reviewAdapter.setReviewsModelList(reviewsModels);
            }
        });

        detailsViewModel.getTrailers().observe(getViewLifecycleOwner(), new Observer<List<trailerModel>>() {
            @Override
            public void onChanged(List<trailerModel> trailerModels) {
                trailersAdapter.setTrailersList(trailerModels);
            }
        });
    }

    private void getDetails(moviesModel movie) {

        movieDetailsFragment detailsFragment;
        detailsFragment = new movieDetailsFragment();
        dataSender = new Bundle();

        detailsViewModel = ViewModelProviders.of(getActivity()).get(detailsViewModel.class);
        detailsViewModel.remove();
        favoriteViewModel.remove();

        dataSender.putString("title", movie.getTitle());
        dataSender.putString("cover", movie.getPoster_path());
        dataSender.putString("overview", movie.getOverview());
        dataSender.putString("id", movie.getId());

        detailsFragment.setArguments(dataSender);

        getFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragment_container, detailsFragment).commit();

    }

    private void getTrailer(String key) {

        Intent trailerIntent = new Intent(getActivity(), trailerActivity.class);
        trailerIntent.putExtra("id", key);
        startActivity(trailerIntent);

    }

    private void NOTIFY() {
        similarMoviesAdapter.notifyDataSetChanged();
        reviewAdapter.notifyDataSetChanged();
        trailersAdapter.notifyDataSetChanged();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.video:

                            break;

                        case R.id.addToFavorite:
                            addToFavourite();
                            break;
                    }
                    return true;
                }
            };

}

