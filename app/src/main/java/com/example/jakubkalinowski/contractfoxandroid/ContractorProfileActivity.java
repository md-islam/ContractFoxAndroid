package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    String contractorID ;

    //UI component variables
    private Button estimateButton, messageButton;
    private TextView address, phoneNumber, companyName, website, emailAddress, fullName, miles;
    private LinearLayout callButton, directionsButton, websiteButton, skillsButton, reviewsButton, picGalleryButton;
    public String urlAddress;

    private String addressInput, phoneInput, webInput, companyInput, briefDesc;
    private TextView briefDescription;

    //imageView
    private CircleImageView mCircleProfileImageView;
    private Bitmap mProfileImageBitmap;

    public ContractorProfileActivity(){}

    public String idpass;

    private String street, unitNo, city, state, zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_profile);

        savedInstanceState = getIntent().getExtras();
        contractorID = savedInstanceState.getString("id");

        Log.i("id-:", contractorID);


        address = (TextView) findViewById(R.id.address_string);
        phoneNumber = (TextView) findViewById(R.id.call_text);
        companyName = (TextView) findViewById(R.id.company_name);
        website = (TextView) findViewById(R.id.website_url);

        briefDescription = (TextView) findViewById(R.id.brief_description_layout);

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
//                Intent i = new Intent(ContractorProfileActivity.this, EstimateActivity.class);
//                startActivity(i);
//            }

                idpass=contractorID.toString();

                // Context context = v.getContext();
                Intent intent = new Intent(getApplicationContext(), EstimateActivity.class);
                intent.putExtra("contID", idpass);

                startActivity(intent);


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
                Intent i = new Intent(ContractorProfileActivity.this, ReviewsActivity.class);
                startActivity(i);
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

}
