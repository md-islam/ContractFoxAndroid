package com.example.jakubkalinowski.contractfoxandroid;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContractorProfileActivity extends AppCompatActivity {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    String param = "kj";
    private static final String TAG = "Firebase_TAG!!" ;



    private DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance()
            .getReference();

    //private Estimate.OnFragmentInteractionListener mListener;

    private Member m;
    public Boolean option;

    String contractorID ;

    //UI component variables
    private Button estimateButton, messageButton;
    private TextView address, phoneNumber, companyName, website, emailAddress, fullName, miles;
    private LinearLayout callButton, directionsButton, websiteButton, skillsButton, reviewsButton, picGalleryButton;
    public String urlAddress;

    private String addressInput, phoneInput, webInput, companyInput, briefDesc;
    private TextView briefDescription;

    final private LinearLayout.LayoutParams etm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);

    //imageView
    private CircleImageView mCircleProfileImageView;
    private Bitmap mProfileImageBitmap;

    public ContractorProfileActivity(){}

    public String idpass;

    private String street, unitNo, city, state, zipcode;


    private FirebaseStorage storage;
    
    private StorageReference mProfilePicPath;
    private StorageReference mLogoImagesPath;

    private ImageView profilePicture, logoPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_profile);

        //here you get the stuff passed to you from previous activity. which is the ID of the clicked contractor
        savedInstanceState = getIntent().getExtras();
        contractorID = savedInstanceState.getString("id");

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





        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    mFirebaseDatabaseReference
                            .child("usersInChat").child(user.getUid().toString())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.child("contractorOption").getValue().equals(true)) {

                                        //need null handlers here
                                        Contractor m = dataSnapshot.getValue(Contractor.class);

//                                        option = true;
                                        setOption(true);

                                        address.setText(m.getAddress().toString());
                                        phoneNumber.setText(m.getPhoneNo());
                                        companyName.setText(m.getBusinessWebsiteURL());
                                        website.setText(m.getBusinessWebsiteURL());
                                        //miles.setText();
                                        urlAddress = m.getBusinessWebsiteURL();

                                    } else {
                                        Homeowner m = (Homeowner) dataSnapshot.getValue(Homeowner.class);

//                                        option = false;
                                        setOption(false);
                                        address.setText(m.getAddress().toString());
                                        phoneNumber.setText(m.getPhoneNo());
                                        //fullName.setText(m.getFullName().toString());
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

        storage = FirebaseStorage.getInstance();

        address = (TextView) findViewById(R.id.address_string);
        phoneNumber = (TextView) findViewById(R.id.call_text);
        companyName = (TextView) findViewById(R.id.company_name);
        website = (TextView) findViewById(R.id.website_url);

        briefDescription = (TextView) findViewById(R.id.brief_description_layout);

        profilePicture = (ImageView)findViewById(R.id.profile_fragment_picture);
        logoPicture = (ImageView)findViewById(R.id.logo_fragment_picture);

        // Download profile picture
        mProfilePicPath = FirebaseStorage.getInstance().getReference("ProfilePictures/"+contractorID+"/profilepic.jpeg");
        mLogoImagesPath = FirebaseStorage.getInstance().getReference("LogoImages/"+contractorID+"/logoimg.jpeg");

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

                        briefDesc = dataSnapshot.child("briefDescription").getValue().toString();
                        briefDescription.setText(briefDesc);

                        street = dataSnapshot.child("address").child("streetAddress").getValue().toString();
                        unitNo = dataSnapshot.child("address").child("unit_Apt_no").getValue().toString();
                        city = dataSnapshot.child("address").child("city").getValue().toString();
                        state = dataSnapshot.child("address").child("state").getValue().toString();
                        zipcode = dataSnapshot.child("address").child("zipCode").getValue().toString();

                        if (unitNo.equals(null)){
                            addressInput = street+", "+city+", "+state+zipcode;
                        } else {
                            addressInput = street+", "+unitNo+", "+city+", "+state+", "+zipcode;
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
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        estimateButton = (Button) findViewById(R.id.aprofile_estimate_button);
//        messageButton = (Button) findViewById(R.id.aprofile_message_button);
        callButton = (LinearLayout) findViewById(R.id.acall_button);
        directionsButton = (LinearLayout)findViewById(R.id.adirections_button);
        websiteButton = (LinearLayout)findViewById(R.id.awebsite_button);
        skillsButton = (LinearLayout)findViewById(R.id.askills_button);
        reviewsButton = (LinearLayout)findViewById(R.id.areviews_button);
        picGalleryButton = (LinearLayout) findViewById(R.id.pic_gallery_button);

        estimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ContractorProfileActivity.this, EstimateActivity.class);
                String [] id = {contractorID };
                i.putExtra("id", id) ;
                startActivity(i);
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(ContractorProfileActivity.this, MessageActivity.class);
//                startActivity(i);
            }
        });

//        messageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(ContractorProfileActivity.this, MessageActivity.class);
//                startActivity(i);
//            }
//        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phoneInput));
                startActivity(intent);            }
        });

        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContractorProfileActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

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
                i.putExtra("id",contractorID);
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
        rb.setRating(5);


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
                        int numOfStars = rb.getNumStars(); //nummber of stars
                        //database work goes here.
                        saveReviewInDB(desc , numOfStars);

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
    private void saveReviewInDB(String description , int numOfStars) {

        String currentUserId = DrawerActivity.currentUserId ; //this is the current user id.
       // contractorID is a string variable available in this activity. it is being passed from previous activity.

    }


}
