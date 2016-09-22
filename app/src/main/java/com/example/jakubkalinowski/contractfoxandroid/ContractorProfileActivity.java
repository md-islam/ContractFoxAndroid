package com.example.jakubkalinowski.contractfoxandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Directions;
import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Reviews;
import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Skills;
import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Website;

public class ContractorProfileActivity extends AppCompatActivity implements Directions.OnFragmentInteractionListener,
        Reviews.OnFragmentInteractionListener, Skills.OnFragmentInteractionListener, Website.OnFragmentInteractionListener {

    private Button estimateButton, messageButton, callButton, directionsButton, websiteButton, skillsButton, reviewsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.contractor_profile_main);
        setContentView(R.layout.contractor_profile_activity);

        // GET THE TITLE PASSED TO THE TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // INITIALIZING ACTIVITY COMPONENTS
        estimateButton = (Button) findViewById(R.id.estimate_button);
        messageButton = (Button) findViewById(R.id.message_button);
        callButton = (Button) findViewById(R.id.call_button);
        directionsButton = (Button) findViewById(R.id.directions_button);
        websiteButton = (Button) findViewById(R.id.website_button);
        //skillsButton = (Button) findViewById(R.id.skills_button);
        //reviewsButton = (Button) findViewById(R.id.reviews_button);


        // BUTTON ACTIONS
        estimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fragmentManager = getFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragment = new Fragment();
//
//                if (fragment != null) {
//                    ft = getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.displayArea_ID, fragment, title);
//                    ft.commit();
//                }
                Fragment estimate_fragment = new Fragment();

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                // Replace the contents of the container with the new fragment
                ft.addToBackStack("Estimate");
                ft.replace(R.id.estimate_fragment_layout, estimate_fragment,
                        "Estimate");
                // or ft.replace(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above

                ft.commit();

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
