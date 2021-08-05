package com.movieApp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movieApp.R;
import com.movieApp.models.moviesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.movieViewHolder> {

    public interface onCoverClick {
        void onCoverClick(int position);
    }

    public interface onCoverLongClick {
        void onCoverLongClick(int position);
    }

    private onCoverLongClick coverLongClick;
    private onCoverClick onClick;
    private List<moviesModel> MoviesList;

    public void setCoverLongClick(onCoverLongClick coverLongClick) {
        this.coverLongClick = coverLongClick;
    }

    public void setOnClick(onCoverClick onClick) {
        this.onClick = onClick;
    }

    public void setMoviesList(List<moviesModel> moviesList) {
        MoviesList = moviesList;
    }

    public List<moviesModel> getMoviesList() {
        return MoviesList;
    }

    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view, parent, false);
        movieViewHolder viewFinder = new movieViewHolder(itemView, onClick, coverLongClick);
        return viewFinder;
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        moviesModel Movies = MoviesList.get(position);
        Picasso.get().load("https://image.tmdb.org/t/p/original" + Movies.getPoster_path()).centerInside().fit().into(holder.movieCover);
    }

    @Override
    public int getItemCount() {
        return MoviesList.size();
    }

    public class movieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieCover;

        public movieViewHolder(@NonNull View itemView, onCoverClick click, onCoverLongClick longClick) {
            super(itemView);
            movieCover = itemView.findViewById(R.id.movieCover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (click != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            click.onCoverClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClick != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longClick.onCoverLongClick(position);
                        }
                    }
                    return true;
                }
            });

        }


    }


}
