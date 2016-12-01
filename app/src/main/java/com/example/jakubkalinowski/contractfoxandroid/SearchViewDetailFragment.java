package com.example.jakubkalinowski.contractfoxandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jakubkalinowski.contractfoxandroid.Model.Review;
import com.example.jakubkalinowski.contractfoxandroid.dummy.DummyContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Map;

/**
 * A fragment representing a single SearchView detail screen.
 * This fragment is either contained in a {@link SearchViewListActivity}
 * in two-pane mode (on tablets) or a {@link SearchViewDetailActivity}
 * on handsets.
 */
public class SearchViewDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    private Button phone, message, website, availability, directions, estimate, mSubmitReview;


    private DatabaseReference mDatabaseReviewReference;

    private EditText mReviewTitleEditText;
    private EditText mDescriptionEditText;
    private RatingBar mRatingBar;



    //initializing these to null, so that Firebase or JavaObject finds something to point to.
    private String mReviewTitle = null;
    private String mDescriptionTitle = null;
    private Double  mRatingBarValue = null;
    //
    private String homeOwnerUserId = null;
    private String contractorUserId = null;
    //this is for Firebase serverValueTimeStamp
    private Map<String, String> mFirebaseServerTimeStamp = null;

    Member member; //? <--WHat is this //
    Review mReview;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchViewDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
          //  mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));



            String companyName = getArguments().getString("name");
      //  Log.d("name", companyName);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(companyName); //?????????
            }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.searchview_detail, container, false);
        return rootView;
    }

    View.OnClickListener messageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // message action here
        }
    };

    View.OnClickListener websiteListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // website action here
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.google.com"));
            startActivity(intent);

        }
    };

    View.OnClickListener phoneListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "8453327029"));
            startActivity(intent);
        }
    };

    View.OnClickListener availabilityListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

//    View.OnClickListener directionsListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(getActivity().getApplicationContext(), GoogleDirectionsActivity.class);
//           // intent.putExtra(lat, long);
//            startActivity(intent);
//        }
//    };

    View.OnClickListener estimateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initializing view components to be manipulated.
        phone = (Button) view.findViewById(R.id.phone_button);
        message = (Button) view.findViewById(R.id.message_button);
        website = (Button) view.findViewById(R.id.website_button);
        availability = (Button) view.findViewById(R.id.view_availability_button);
        directions = (Button) view.findViewById(R.id.directions_button);
        estimate = (Button) view.findViewById(R.id.estimate_button);
        mSubmitReview = (Button) view.findViewById(R.id.submit_review_Contractor_profile_fragment);

        //initializing RatingView components
        mDescriptionEditText = (EditText) view.findViewById(R.id.
                description_editText_contractorProfileFragment);
        mReviewTitleEditText = (EditText) view.findViewById(R.id.
                title_edit_text_contractor_profile_fragment);
        mRatingBar = (RatingBar) view.findViewById(R.id.ratingStars_ID_contractorProfileFragment);

        mDatabaseReviewReference = FirebaseDatabase.getInstance().
                getReference("contractor_reviews");


//        message.setOnClickListener(messageListener);
//        website.setOnClickListener(websiteListener);
//        phone.setOnClickListener(phoneListener);
//        availability.setOnClickListener(availabilityListener);
//        directions.setOnClickListener(directionsListener);
//        estimate.setOnClickListener(estimateListener);



        //test-submitting_review to contractor profile--[Start]
        mSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //sending title, desciption, and Rating from view
                mRatingBarValue = Double.valueOf(mRatingBar.getRating());
                mReviewTitle = mReviewTitleEditText.getText().toString();
                mDescriptionTitle = mDescriptionEditText.getText().toString();

                //tricky part
                // Step 1 -> create an id for the review, dont worry, it's just a hashID
                        //generate user ID and contractor ID
                // Step 2 -> .child(*contractorUSERID*)
                // Step 3 -> .child(*pushreview*)

                String reviewID = mDatabaseReviewReference.push().getKey();
                if(reviewID != null){
                    FirebaseUser current_user_instance = FirebaseAuth.getInstance().getCurrentUser();
                    String current_user_id = current_user_instance.getUid();

                    Toast.makeText(getActivity(), current_user_id,
                            Toast.LENGTH_SHORT).show();





                    //this piecce of ID will be grabbed when a card is hit from the previous page,
                    // and that card (contractor user id) is passed to this activity.
                    String temporary_contractor_user_id = "pnuccWQv9wgyPpkaWKuWo1FVsPd2";

                    //autogenerated pushID generated by Firebase
                    String reviewIDString = mDatabaseReviewReference.child
                            (temporary_contractor_user_id).push().getKey();


                    //this is TimeStamp stored in Firebase as EPOCH
                    mFirebaseServerTimeStamp = ServerValue.TIMESTAMP;

                    //construct the review object
//                    mReview = new Review(current_user_id, temporary_contractor_user_id,
//                            mFirebaseServerTimeStamp, mReviewTitle, mDescriptionTitle,
//                            mRatingBarValue);
//
                    mDatabaseReviewReference.child(temporary_contractor_user_id).
                            child(reviewIDString).setValue(mReview);
                }






            }
        });

        //test-submitting_review to contractor profile--[END]




    }


}
