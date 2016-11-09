package com.example.jakubkalinowski.contractfoxandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EstimateActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    //reference to realtime database
    private DatabaseReference mDatabaseEstimateReference;
    private DatabaseReference mDatabaseMessageReferance;

    //Firebase serverValueTimeStamp
    private Map<String, String> mFirebaseServerTimeStamp = null;

    // Camera items from imported library
    private CameraPhoto cameraPhoto;
    private GalleryPhoto galleryPhoto;

    final int CAMERA_REQUEST = 12345;
    final int GALLERY_REQUEST = 12345;

    // Camera images
    private ImageView ivCamera, ivGallery, ivUpload, ivImage;

    //Text Wrappers
    private TextInputLayout mProjectTitleWrapper, mItemAreaSpecsWrapper, mDetailDescriptionWrapper;

    // Input Texts
    private EditText mProjectTitleEditText, mItemAreaSpecsEditText, mDetailDescriptionEditText;

    //Strings from currentFragment
    private String mProjectTitle, mItemAreaSpecs, mDetailDescription;

    // Switches and Text fields with display
    private Switch switchButton1, switchButton2;
    private String switchOn = "YES";
    private String switchOff = "NO";
    private TextView ownMaterials, delivery;

    private String mOwnMaterials;
    private String mDelivery;

    // Submit button
    private Button mSubmitButton;

    Estimates mEstimates;

    MessageActivity mMessages;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> mEstimate_list = new ArrayList<>();
//    private ListView estimate_list;

    String contractorsid ;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressDialog = new ProgressDialog(this);

        //TODO: get contractorID from previous screen
        savedInstanceState = getIntent().getExtras();
//        contractorID = savedInstanceState.getString("id");
        contractorsid = "KHyneGAKKsOlDCSfDMJLzv8JSto2";

        mProjectTitleWrapper = (TextInputLayout)findViewById(R.id.project_title_text_wrapper);
        mItemAreaSpecsWrapper = (TextInputLayout)findViewById(R.id.item_area_specs_text_wrapper);
        mDetailDescriptionWrapper = (TextInputLayout)findViewById(R.id.detail_description_text_wrapper);

        mProjectTitleEditText = (EditText)findViewById(R.id.project_title_edit_text);
        mItemAreaSpecsEditText = (EditText)findViewById(R.id.item_area_specs_edit_text);
        mDetailDescriptionEditText = (EditText)findViewById(R.id.detail_description_edit_text);

        //This is for the images part
        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        ivImage = (ImageView)findViewById(R.id.ivImage);
        ivCamera = (ImageView)findViewById(R.id.ivCamera);
        ivGallery = (ImageView)findViewById(R.id.ivGallery);
//        ivUpload = (ImageView)findViewById(R.id.ivUpload);

        mSubmitButton = (Button)findViewById(R.id.submit_button);
//
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, estimate_list);
//
//        estimate_list = (ListView)findViewById(R.id.estimate_list_container);
//        estimate_list.setAdapter(arrayAdapter);

        mDatabaseEstimateReference = FirebaseDatabase.getInstance().getReference("estimates");
        mDatabaseMessageReferance = FirebaseDatabase.getInstance().getReference("messages");

        // --- STEP 1 ---

        // --- END OF STEP 1 ---


        //--- STEP 2 ---
        // Switch Button 1
        switchButton1 = (Switch)findViewById(R.id.switch_q1);
        ownMaterials = (TextView) findViewById(R.id.textView1);

        switchButton1.setChecked(false);
        switchButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ownMaterials.setText(switchOn);
                } else {
                    ownMaterials.setText(switchOff);
                }
            }
        });

        if (switchButton1.isChecked()) {
            ownMaterials.setText(switchOn);
        } else {
            ownMaterials.setText(switchOff);
        }
        // END Switch Button 1

        // Switch Button 2
        switchButton2 = (Switch)findViewById(R.id.switch_q2);
        delivery = (TextView) findViewById(R.id.textView2);

        switchButton2.setChecked(false);
        switchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    delivery.setText(switchOn);
                } else {
                    delivery.setText(switchOff);
                }
            }
        });

        if (switchButton2.isChecked()) {
            delivery.setText(switchOn);
        } else {
            delivery.setText(switchOff);
        }
        // --- END OF STEP 2 ---


        // --- STEP 3 ---

        // CAMERA OPERATIONS (PART 1)
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Something wrong while taking photos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
            }
        });
        // END CAMERA OPERATIONS (PART 1)
        // --- END OF STEP 3 ---


        // SUBMIT ESTIMATE(S)
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mProjectTitle = mProjectTitleEditText.getText().toString();
                mItemAreaSpecs = mItemAreaSpecsEditText.getText().toString();
                mDetailDescription = mDetailDescriptionEditText.getText().toString();
                mOwnMaterials = ownMaterials.getText().toString();
                mDelivery = delivery.getText().toString();

                mProgressDialog.setMessage("Sending ...");
                mProgressDialog.show();

                String estimateID = mDatabaseEstimateReference.push().getKey();
                if(estimateID != null) {
                    FirebaseUser sender_instance = FirebaseAuth.getInstance().getCurrentUser();
                    String sender_id = sender_instance.getUid();

                    String receiver_id = contractorsid.toString();

                    //Autogenerated pushID generated by Firebase
                    String estimateIDString = mDatabaseEstimateReference.child
                            (receiver_id).push().getKey();

                    //this is TimeStamp stored in Firebase as EPOCHz
                    mFirebaseServerTimeStamp = ServerValue.TIMESTAMP;

                    // Construct an Estimate object
                    //TODO: Add toString method into DB
                    mEstimates = new Estimates(sender_id, receiver_id,
                            mFirebaseServerTimeStamp, mProjectTitle, mItemAreaSpecs, mDetailDescription, mOwnMaterials, mDelivery);

                    mDatabaseEstimateReference.child(receiver_id)
                            .child(estimateIDString).setValue(mEstimates);

                }

                String messageID = mDatabaseMessageReferance.push().getKey();
                if (messageID != null) {

                    FirebaseUser sender_instance = FirebaseAuth.getInstance().getCurrentUser();
                    String sender_id = sender_instance.getUid();

                    String receiver_id = contractorsid.toString();

                    //Autogenerated pushID generated by Firebase
                    String estimateIDString = mDatabaseMessageReferance.child
                            (receiver_id).push().getKey();

                    //this is TimeStamp stored in Firebase as EPOCHz
                    mFirebaseServerTimeStamp = ServerValue.TIMESTAMP;


                    mEstimates = new Estimates(sender_id, receiver_id,
                            mFirebaseServerTimeStamp, mProjectTitle, mItemAreaSpecs, mDetailDescription, mOwnMaterials, mDelivery);

                    // Update the DB with new estimate chat room
                    Map<String, Object> map = new HashMap<String, Object>();
                    // Chat room name and estimate that goes with it
                    map.put(mProjectTitle,mEstimates.toString());
                    // append the messages reference with new chat room in the DB
                    mDatabaseMessageReferance.child("sender id: " + sender_id).child("receiver id: " +receiver_id).updateChildren(map);

                }



                // return to previous screen
                //TODO: kill the activity so you can't go back to it.
                Intent intent = new Intent(EstimateActivity.this, ContractorProfileActivity.class);
                startActivity(intent);
                mProgressDialog.dismiss();
                finish();
            }

        });

        mDatabaseMessageReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                mEstimate_list.clear();
                mEstimate_list.addAll(set);

//                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // END OF SUBMIT ESTIMATE(S)



    }

    // CAMERA OPERATIONS (PART 2)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == CAMERA_REQUEST) {
                String photoPath = cameraPhoto.getPhotoPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
                    ivImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Something wrong while loading photos", Toast.LENGTH_SHORT).show();
                }
            }
            else if(requestCode == GALLERY_REQUEST) {
                Uri uri = data.getData();
                galleryPhoto.setPhotoUri(uri);
                String photoPath = galleryPhoto.getPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
                    ivImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Something wrong while loading photos", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    // END CAMERA OPERATIONS (PART 2)



}
