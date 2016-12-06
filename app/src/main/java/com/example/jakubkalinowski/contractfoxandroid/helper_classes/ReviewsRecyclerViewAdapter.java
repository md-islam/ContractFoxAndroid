package com.example.jakubkalinowski.contractfoxandroid.helper_classes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jakubkalinowski.contractfoxandroid.Model.Review;
import com.example.jakubkalinowski.contractfoxandroid.R;

import java.util.List;


public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.SingleReviewViewHolder>{

    public List<Review> reviewList;


    public ReviewsRecyclerViewAdapter(List<Review> reviewList){
        this.reviewList = reviewList;
    }

    @Override
    public SingleReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SingleReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.reviewDescription.setText(review.getDescription());
        holder.reviewTimestamp.setText(review.
                getCreatedAtFirebaseTimeStampFormattedString());
        holder.reviewRatingBar.setRating(review.getStars().floatValue());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class SingleReviewViewHolder extends RecyclerView.ViewHolder {
        public RatingBar reviewRatingBar;
        public TextView reviewDescription;
        public TextView reviewTimestamp;
        public SingleReviewViewHolder(View view){
            super (view);
            reviewRatingBar = (RatingBar) view.findViewById(R.id.ratingBar_review_view);
            reviewDescription = (TextView) view.findViewById(R.id.review_description_view);
            reviewTimestamp = (TextView) view.findViewById(R.id.review_row_timeStamp);
        }
    }
}
