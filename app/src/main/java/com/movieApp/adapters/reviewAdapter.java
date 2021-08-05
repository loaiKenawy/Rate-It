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
import com.movieApp.models.reviewsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class reviewAdapter extends RecyclerView.Adapter<reviewAdapter.movieViewHolder> {


    private List<reviewsModel> reviewsModelList;


    public void setReviewsModelList(List<reviewsModel> reviewsModelList) {
        this.reviewsModelList = reviewsModelList;
    }

    public List<reviewsModel> getReviewsModelList() {
        return reviewsModelList;
    }

    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_card_view, parent, false);
        movieViewHolder viewFinder = new movieViewHolder(itemView);
        return viewFinder;
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        reviewsModel review = reviewsModelList.get(position);
        holder.author.setText(review.getAuthor());
        holder.review.setText((review.getContent()));

    }

    @Override
    public int getItemCount() {
        return reviewsModelList.size();
    }

    public class movieViewHolder extends RecyclerView.ViewHolder {

        TextView author , review;

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author_name);
            review = itemView.findViewById(R.id.review);

        }



    }


}
