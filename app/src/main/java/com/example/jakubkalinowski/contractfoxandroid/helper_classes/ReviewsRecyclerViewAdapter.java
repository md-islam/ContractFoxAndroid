package com.example.jakubkalinowski.contractfoxandroid.helper_classes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jakubkalinowski.contractfoxandroid.Model.Review;
import com.example.jakubkalinowski.contractfoxandroid.R;

import java.util.List;

<<<<<<< HEAD
=======

>>>>>>> b940201bb12dd346f9fafb272a32031f03046331

public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.SingleReviewViewHolder>{

    private List<Review> reviewList;


    public ReviewsRecyclerViewAdapter(List<Review> reviewList){
        this.reviewList = reviewList;
    }

    public class SingleReviewViewHolder extends RecyclerView.ViewHolder {
        private RatingBar reviewRatingBar;
        private TextView reviewDescription;
        private TextView reviewTimestamp;
        public SingleReviewViewHolder(View itemView){
            super (itemView);
            reviewRatingBar = (RatingBar) itemView.findViewById(R.id.ratingBar_review_view);
            reviewDescription = (TextView) itemView.findViewById(R.id.review_description_view);
            reviewTimestamp = (TextView) itemView.findViewById(R.id.review_row_timeStamp);
        }
    }

    @Override
    public SingleReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.review_list_row, parent, false);
        return new SingleReviewViewHolder(itemView);

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


}
