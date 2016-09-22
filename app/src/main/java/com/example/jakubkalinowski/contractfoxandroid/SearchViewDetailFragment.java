package com.example.jakubkalinowski.contractfoxandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jakubkalinowski.contractfoxandroid.dummy.DummyContent;

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

    Button phone, message, website, availability, directions, estimate;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchViewDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("Company name"); //?????????
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.searchview_detail, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.searchview_detail)).setText("More details " +
//                    "about the company, -- Ladi Dadi --");
//        }

        phone = (Button) rootView.findViewById(R.id.phone_button);
        message = (Button) rootView.findViewById(R.id.message_button);
        website = (Button) rootView.findViewById(R.id.website_button);
        availability = (Button) rootView.findViewById(R.id.view_availability_button);
        directions = (Button) rootView.findViewById(R.id.directions_button);
        estimate = (Button) rootView.findViewById(R.id.estimate_button);

        message.setOnClickListener(messageListener);
        website.setOnClickListener(websiteListener);
        phone.setOnClickListener(phoneListener);
        availability.setOnClickListener(availabilityListener);
//        directions.setOnClickListener(directionsListener);
//        estimate.setOnClickListener(estimateListener);

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
//            startActivity(intent);
//        }
//    };
//
//    View.OnClickListener estimateListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//        }
//    };

}
