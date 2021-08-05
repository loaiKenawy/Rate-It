package com.movieApp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movieApp.R;
import com.movieApp.models.moviesModel;
import com.movieApp.models.trailerModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class trailerAdapter extends RecyclerView.Adapter<trailerAdapter.movieViewHolder> {

    public interface onCoverClick {
        void onCoverClick(int position);
    }

    private onCoverClick onClick;
    private List<trailerModel> trailersList;
    private String cover;


    public void setOnClick(onCoverClick onClick) {
        this.onClick = onClick;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setTrailersList(List<trailerModel> trailersList) {
        this.trailersList = trailersList;
    }

    public List<trailerModel> getTrailersList() {
        return trailersList;
    }

    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers_card_view, parent, false);
        movieViewHolder viewFinder = new movieViewHolder(itemView, onClick);
        return viewFinder;
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        holder.trailerName.setText(trailersList.get(position).getName());
        Picasso.get().load("https://image.tmdb.org/t/p/original" + cover).fit().into(holder.movieCover);
    }

    @Override
    public int getItemCount() {
        return trailersList.size();
    }

    public class movieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieCover;
        TextView trailerName;

        public movieViewHolder(@NonNull View itemView, onCoverClick click) {
            super(itemView);
            movieCover = itemView.findViewById(R.id.trailerCover);
            trailerName = itemView.findViewById(R.id.trailerName);

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

        }



    }


}
