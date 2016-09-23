package com.example.jakubkalinowski.contractfoxandroid;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Directions;
import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Reviews;
import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Skills;
import com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Website;

public class ContractorProfileActivity extends AppCompatActivity implements Directions.OnFragmentInteractionListener,
        Reviews.OnFragmentInteractionListener, Skills.OnFragmentInteractionListener, Website.OnFragmentInteractionListener {

    public Button estimateButton, callButton, directionsButton, websiteButton, skillsButton, reviewsButton;

    public static FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.contractor_profile_main);

        //changed temporarily
        setContentView(R.layout.activity_introduction);

        // GET THE TITLE PASSED TO THE TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        // END OF GET THE TITLE PASSED TO THE TOOLBAR

//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(pictureFragment , "pictureTag");
//        fragmentTransaction.commit();
    }

    public void buttonPressed(Button button) {

        int id = button.getId();

    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
