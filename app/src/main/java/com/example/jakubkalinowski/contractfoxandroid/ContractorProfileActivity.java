package com.example.jakubkalinowski.contractfoxandroid;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jakubkalinowski.contractfoxandroid.Model.Review;
import com.example.jakubkalinowski.contractfoxandroid.Navigation_Fragments.ContractorScheduleFragment;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContractorProfileActivity extends AppCompatActivity {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    float overAllrating;
    DrawerActivity drawerActivity = new DrawerActivity();
    public static final String ARG_ITEM_ID = "item_id";

    Fragment fragment = new ContractorScheduleFragment();
    String param = "kj";
    private static final String TAG = "Firebase_TAG!!";
    //[Firebase_variable]**
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener; //signed_in state listener object

    private DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance()
            .getReference();

    //private Estimate.OnFragmentInteractionListener mListener;
    private Member m;
    public Boolean option;
    String contractorID;
    RatingBar ratingForContractor;
    private String currentAuthenticatedUserID;

    //UI component variables
    private Button estimateButton, messageButton, availabilityButton, directionsButton;
    private TextView address, phoneNumber, companyName, website, emailAddress, fullName, miles;
    private LinearLayout callButton, websiteButton, skillsButton, reviewsButton, picGalleryButton;

    public String urlAddress;
    private double contractorUserRatingCount;
    private int count;
    final private LinearLayout.LayoutParams etm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
    private String addressInput, phoneInput, webInput, companyInput, briefDesc;
    private TextView briefDescription;

    //imageView
    private CircleImageView mCircleProfileImageView;
    private Bitmap mProfileImageBitmap;

    private String street, unitNo, city, state, zipcode;

    private FirebaseStorage storage;

    private StorageReference mProfilePicPath;
    private StorageReference mLogoImagesPath;

    private ImageView profilePicture, logoPicture;

    public ContractorProfileActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_profile);
        count = 0;
        contractorUserRatingCount = 0;
        //here you get the stuff passed to you from previous activity. which is the ID of the clicked contractor
        savedInstanceState = getIntent().getExtras();
        contractorID = savedInstanceState.getString("id");
        overAllrating = savedInstanceState.getFloat("overAllrating");

        Log.d("xyz-prof", contractorID);// yay it worked.
        //ok jakub here is how you would get anything you want on the clicked user.
        /*
       mFirebaseDatabaseReference.child("usersInChat").child(contractorID). anything you want here after dot ;

       here is a full example :
        mFirebaseDatabaseReference.child("usersInChat").child(contractorID).child("firstName") // will give you firstname
        mFirebaseDatabaseReference.child("usersInChat").child(contractorID).child("phonenNo") // will give you phone num
       so in conclusion, i am pasing you the id of the contraactor from the previous page and
       here you can make a quick call to db to get what you want. no going over a list. you have the id.
         */

        storage = FirebaseStorage.getInstance();

        mFirebaseDatabaseReference.child("contractor_reviews").child(contractorID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Review child_review = dataSnapshot.getValue(Review.class);
                count++;
                contractorUserRatingCount += child_review.getStars();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        address = (TextView) findViewById(R.id.address_string);
        phoneNumber = (TextView) findViewById(R.id.call_text);
        companyName = (TextView) findViewById(R.id.company_name);
        website = (TextView) findViewById(R.id.website_url);
        briefDescription = (TextView) findViewById(R.id.brief_description_layout);
        profilePicture = (ImageView) findViewById(R.id.profile_fragment_picture);
        logoPicture = (ImageView) findViewById(R.id.logo_fragment_picture);

        // Download profile picture
        mProfilePicPath = FirebaseStorage.getInstance().getReference("ProfilePictures/" + contractorID + "/profilepic.jpeg");
        mLogoImagesPath = FirebaseStorage.getInstance().getReference("LogoImages/" + contractorID + "/logoimg.jpeg");

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(mProfilePicPath)
                .into(profilePicture);

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(mLogoImagesPath)
                .into(logoPicture);

        mFirebaseDatabaseReference
                .child("users").child(contractorID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("contractorOption").getValue().equals(true)) {

                            briefDesc = dataSnapshot.child("briefDescription").getValue().toString();
                            briefDescription.setText(briefDesc);

                            street = dataSnapshot.child("address").child("streetAddress").getValue().toString();
                            unitNo = dataSnapshot.child("address").child("unit_Apt_no").getValue().toString();
                            city = dataSnapshot.child("address").child("city").getValue().toString();
                            state = dataSnapshot.child("address").child("state").getValue().toString();
                            zipcode = dataSnapshot.child("address").child("zipCode").getValue().toString();

                            if (unitNo.equals(null)) {
                                addressInput = street + ", " + city + ", " + state + zipcode;
                            } else {
                                addressInput = street + ", " + unitNo + ", " + city + ", " + state + ", " + zipcode;
                            }

                            address.setText(addressInput);

                            phoneInput = dataSnapshot.child("phoneNo").getValue().toString();
                            phoneNumber.setText(phoneInput);

                            companyInput = dataSnapshot.child("firstName").getValue().toString();
                            companyName.setText(companyInput);

                            webInput = dataSnapshot.child("businessWebsiteURL").getValue().toString();
                            website.setText(webInput);

                            //miles.setText();

                            urlAddress = webInput;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Estimate.ARG_ITEM_ID,
//                    getIntent().getStringExtra(com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Estimate.ARG_ITEM_ID));
//            com.example.jakubkalinowski.contractfoxandroid.Contractor_Fragments.Estimate fragment = new Estimate();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.activity_contractor_profile, fragment)
//                    .commit();
//        }

        availabilityButton = (Button) findViewById(R.id.availability);
        estimateButton = (Button) findViewById(R.id.aprofile_estimate_button);
        messageButton = (Button) findViewById(R.id.aprofile_message_button);
        callButton = (LinearLayout) findViewById(R.id.acall_button);
        directionsButton = (Button)findViewById(R.id.adirections_button);
        websiteButton = (LinearLayout)findViewById(R.id.awebsite_button);
        skillsButton = (LinearLayout)findViewById(R.id.askills_button);
        reviewsButton = (LinearLayout)findViewById(R.id.areviews_button);

        picGalleryButton = (LinearLayout) findViewById(R.id.pic_gallery_button);

        availabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FrameLayout fl = (FrameLayout) findViewById(R.id.displayArea_ID);
                Fragment fragment = new ContractorScheduleFragment();
                //  FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.displayArea_ID, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        estimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContractorProfileActivity.this, EstimateActivity.class);
                String[] id = {contractorID};
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneInput));
                startActivity(intent);
            }
        });

//    directionsButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent i = new Intent(ContractorProfileActivity.this, MapsActivity.class);
//            startActivity(i);
//        }
//    });

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!webInput.startsWith("http://") && !webInput.startsWith("https://")) {
                    webInput = "http://" + webInput;
                }

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(webInput));
                startActivity(i);
            }
        });

        skillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SkillSetActivity.class);
                //TODO: debug here!!!
                i.putExtra("id", contractorID);
                startActivity(i);
            }
        });

        reviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateReviewDialog();
            }
        });

        picGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContractorProfileActivity.this, PicGalleryActivity.class);
                i.putExtra("id", contractorID);
                startActivity(i);
            }
        });
    }

    public Dialog onCreateReviewDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContractorProfileActivity.this);
        alertDialog.setTitle("Write a Review");
        alertDialog.setMessage("");
        LinearLayout linear = new LinearLayout(getApplicationContext());
//        TextView textEmail = new TextView(getApplicationContext());
//        textEmail.setText("Email: ");
        final RatingBar rb = new RatingBar(getApplicationContext());
        rb.setRating(0);


        final EditText description = new EditText(getApplicationContext());
        description.setHint("Description");
        description.setMinHeight(150);
        // description.setBackgroundResource(R.drawable.border);
        description.setHintTextColor(0xFFBCBCBC);
        description.setTextColor(0xFFBCBCBC);

        rb.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

        // description.setLayoutParams(etm);


//        Button send = new Button(getApplicationContext());
//        send.setText("Submit");
        alertDialog.setNegativeButton("Submit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String desc = description.getText().toString(); //descritption
                        int numOfStars = (int) rb.getRating(); //nummber of stars
                        //database work goes here.
                        double starsDouble = (double) numOfStars;

                        saveReviewInDB(desc, starsDouble);

                    }
                });
        //send.setLayoutParams(etm);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.addView(description);
        linear.addView(rb);
        // linear.addView(send);
        alertDialog.setView(linear);
        return alertDialog.show();
    }

    // You can put the DB code here.
    private void saveReviewInDB(String description, double numOfStars) {

        String currentReviewerUserId = DrawerActivity.currentUserId; //this is the current user id.
        // contractorID is a string variable available in this activity. it is being passed from previous activity.
        //
        String firebasePushKey = mFirebaseDatabaseReference.child("contractor_reviews").push().
                getKey();
        HashMap<String, Object> dateMap = new HashMap<String, Object>();
        dateMap.put("date", ServerValue.TIMESTAMP);
        Review review = new Review(currentReviewerUserId, contractorID, dateMap, null,
                description, numOfStars, firebasePushKey);

        mFirebaseDatabaseReference.child("contractor_reviews").child(contractorID).child(firebasePushKey)
                .setValue(review);

        //ok so every contractor needs to haave an overall rating attribute in db. Just one number.
        mFirebaseDatabaseReference.child("users").child(contractorID).child("overAllrating").
                setValue(contractorUserRatingCount / count);
        //each contractor id is the parent key and the childs are firebase push key with containing child object

    }

}
