package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

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
//    private CameraPhoto cameraPhoto;
//    private GalleryPhoto galleryPhoto;

//    final int CAMERA_REQUEST = 12345;
//    final int GALLERY_REQUEST = 12345;

    // Camera images
//    private ImageView ivCamera, ivGallery, ivUpload, ivImage;

    //Text Wrappers
    private TextInputLayout mProjectTitleWrapper, mItemAreaSpecsWrapper, mDetailDescriptionWrapper;

    // Input Texts
    private EditText mProjectTitleEditText, mItemAreaSpecsEditText, mDetailDescriptionEditText;

    //Strings from currentFragment
    private String mProjectTitle, mItemAreaSpecs, mDetailDescription;

    // Layouts with checkboxes
    private LinearLayout layout_interior, layout_exterior, layout_backyard, layout_description;

    // Radio buttons
    private RadioButton radio_interior, radio_exterior, radio_backyard;

    // ArrayList for storing checked items
    private ArrayList<String> checkedItems;

    // Checkboxes
    private CompoundButton.OnCheckedChangeListener mCheckListener;
    // Interior
    private CheckBox kitchen, living_room, bedroom, closet, bathroom, attic, basement,
            garage, dinning_room, utility_room, entrance, other_interior;
    // Exterior
    private CheckBox first_floor, second_floor, garage_unit, roof, foundation, addition, porch, balcony, other_exterior;
    // Backyard
    private  CheckBox pool_jacuzzi, septic, driveway, fence, landscape, trees, shed, well, other_backyard;

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
    private ListView estimate_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProjectTitleWrapper = (TextInputLayout)findViewById(R.id.project_title_text_wrapper);
        mItemAreaSpecsWrapper = (TextInputLayout)findViewById(R.id.item_area_specs_text_wrapper);
        mDetailDescriptionWrapper = (TextInputLayout)findViewById(R.id.detail_description_text_wrapper);

        mProjectTitleEditText = (EditText)findViewById(R.id.project_title_edit_text);
        mItemAreaSpecsEditText = (EditText)findViewById(R.id.item_area_specs_edit_text);
        mDetailDescriptionEditText = (EditText)findViewById(R.id.detail_description_edit_text);

        layout_interior = (LinearLayout)findViewById(R.id.interior_fragment_content_layout);
        layout_exterior = (LinearLayout)findViewById(R.id.exterior_fragment_content_layout);
        layout_backyard = (LinearLayout)findViewById(R.id.backyard_fragment_content_layout);

        radio_interior = (RadioButton)findViewById(R.id.radio_interior);
        radio_exterior = (RadioButton)findViewById(R.id.radio_exterior);
        radio_backyard = (RadioButton) findViewById(R.id.radio_backyard);

        checkedItems = new ArrayList<String>();

//        cameraPhoto = new CameraPhoto(getApplicationContext());
//        galleryPhoto = new GalleryPhoto(getApplicationContext());

//        ivImage = (ImageView)findViewById(ivImage);
//        ivCamera = (ImageView)findViewById(ivCamera);
//        ivGallery = (ImageView)findViewById(ivGallery);
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
        // Check Boxes
        // Interior
        kitchen = (CheckBox)findViewById(R.id.checkbox_kitchen);
        living_room = (CheckBox)findViewById(R.id.checkbox_livingroom);
        bedroom = (CheckBox)findViewById(R.id.checkbox_bedroom);
        closet = (CheckBox)findViewById(R.id.checkbox_closet);
        bathroom = (CheckBox)findViewById(R.id.checkbox_bathroom);
        attic = (CheckBox)findViewById(R.id.checkbox_attic);
        basement = (CheckBox)findViewById(R.id.checkbox_basement);
        garage = (CheckBox)findViewById(R.id.checkbox_garage);
        dinning_room = (CheckBox)findViewById(R.id.checkbox_dinningroom);
        utility_room = (CheckBox)findViewById(R.id.checkbox_utility_room);
        entrance = (CheckBox)findViewById(R.id.checkbox_entrance);
        other_interior = (CheckBox)findViewById(R.id.checkbox_other_interior);
        // Exterior
        first_floor = (CheckBox)findViewById(R.id.checkbox_first_floor);
        second_floor = (CheckBox)findViewById(R.id.checkbox_second_floor);
        garage_unit = (CheckBox)findViewById(R.id.checkbox_garage_unit);
        roof = (CheckBox)findViewById(R.id.checkbox_roof);
        foundation = (CheckBox)findViewById(R.id.checkbox_foundation);
        addition = (CheckBox)findViewById(R.id.checkbox_addition);
        porch = (CheckBox)findViewById(R.id.checkbox_porch);
        balcony = (CheckBox)findViewById(R.id.checkbox_balcony);
        other_exterior = (CheckBox)findViewById(R.id.checkbox_other_exterior);
        // Backyard
        pool_jacuzzi = (CheckBox)findViewById(R.id.checkbox_pool_jacuzzi);
        septic = (CheckBox)findViewById(R.id.checkbox_septic_system);
        driveway = (CheckBox)findViewById(R.id.checkbox_driveway);
        fence = (CheckBox)findViewById(R.id.checkbox_fence);
        landscape = (CheckBox)findViewById(R.id.checkbox_landscape);
        trees = (CheckBox)findViewById(R.id.checkbox_trees);
        shed = (CheckBox)findViewById(R.id.checkbox_shed);
        well = (CheckBox)findViewById(R.id.checkbox_well);
        other_backyard = (CheckBox)findViewById(R.id.checkbox_other_backyard);


        //This Checklistener fires everytime a check button is clicked.
        mCheckListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                // compoundButton is the checkbox
                // boolean is whether or not checkbox is checked
                // Check which checkbox was clicked
                switch (compoundButton.getId()) {
                    case R.id.checkbox_kitchen:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("kitchen");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("kitchen");
                        }
                        break;
                    case R.id.checkbox_bedroom:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("bedroom");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("bedroom");
                        }
                        break;
                    case R.id.checkbox_livingroom:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("living room");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("living room");
                        }
                        break;
                    case R.id.checkbox_attic:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("attic");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("attic");
                        }
                        break;
                    case R.id.checkbox_basement:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("basement");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("basement");
                        }
                        break;
                    case R.id.checkbox_bathroom:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("bathroom");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("bathroom");
                        }
                        break;
                    case R.id.checkbox_closet:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("closet");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("closet");
                        }
                        break;
                    case R.id.checkbox_dinningroom:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("dinning room");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("dinning room");
                        }
                        break;
                    case R.id.checkbox_entrance:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("entrance");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("entrance");
                        }
                        break;
                    case R.id.checkbox_garage:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("garage");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("garage");
                        }
                        break;
                    case R.id.checkbox_other_interior:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("interior - other");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("other");
                        }
                        break;
                    case R.id.checkbox_utility_room:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("utility room");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("utility room");
                        }
                        break;
                    case R.id.checkbox_addition:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("addition");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("addition");
                        }
                        break;
                    case R.id.checkbox_balcony:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("balcony");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("balcony");
                        }
                        break;
                    case R.id.checkbox_driveway:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("driveway");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("driveway");
                        }
                        break;
                    case R.id.checkbox_fence:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("fence");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("fence");
                        }
                        break;
                    case R.id.checkbox_first_floor:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("exterior - first floor");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("exterior - first floor");
                        }
                        break;
                    case R.id.checkbox_foundation:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("foundation");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("foundation");
                        }
                        break;
                    case R.id.checkbox_garage_unit:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("exterior - garage");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("exterior - garage");
                        }
                        break;
                    case R.id.checkbox_landscape:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("landscape");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("landscape");
                        }
                        break;
                    case R.id.checkbox_other_backyard:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("backyard - other");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("backyard - other");
                        }
                        break;
                    case R.id.checkbox_other_exterior:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("exterior - other");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("exterior - other");
                        }
                        break;
                    case R.id.checkbox_pool_jacuzzi:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("pool/jacuzzi");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("pool/jacuzzi");
                        }
                        break;
                    case R.id.checkbox_porch:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("porch");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("porch");
                        }
                        break;
                    case R.id.checkbox_roof:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("roof");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("roof");
                        }
                        break;
                    case R.id.checkbox_second_floor:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("exterior - second floor");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("exterior - second floor");
                        }
                        break;
                    case R.id.checkbox_septic_system:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("septic system");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("septic system");
                        }
                        break;
                    case R.id.checkbox_shed:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("shed");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("shed");
                        }
                        break;
                    case R.id.checkbox_trees:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("trees");
                        } else {
                            //Remove From ArrayList
                            checkedItems.add("trees");
                        }
                        break;
                    case R.id.checkbox_well:
                        if (checked) {
                            //Add to arrayList
                            checkedItems.add("water well");
                        } else {
                            //Remove From ArrayList
                            checkedItems.remove("water well");
                        }
                        break;
                }
            }
        };

        // Setting up the checked item arraylist and it's listeners
        CheckedItemCheckbox();

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

        // --- END OF STEP 3 ---


        // --- STEP 4 ---
        // CAMERA OPERATIONS (PART 1)
//        ivCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
//                    cameraPhoto.addToGallery();
//                } catch (IOException e) {
//                    Toast.makeText(getApplicationContext(), "Something wrong while taking photos", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        ivGallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
//            }
//        });
        // END CAMERA OPERATIONS (PART 1)
        // --- END OF STEP 4 ---

        // SUBMIT ESTIMATE(S)

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mProjectTitle = mProjectTitleEditText.getText().toString();
                mItemAreaSpecs = mItemAreaSpecsEditText.getText().toString();
                mDetailDescription = mDetailDescriptionEditText.getText().toString();
                mOwnMaterials = ownMaterials.getText().toString();
                mDelivery = delivery.getText().toString();
                // TODO: Add an ArrayList of checked items
                // checkedItems

                String estimateID = mDatabaseEstimateReference.push().getKey();
                if(estimateID != null) {
                    FirebaseUser sender_instance = FirebaseAuth.getInstance().getCurrentUser();
                    String sender_id = sender_instance.getUid();

                    String receiver_id = "pnuccWQv9wgyPpkaWKuWo1FVsPd2";

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

                    String receiver_id = "pnuccWQv9wgyPpkaWKuWo1FVsPd2";

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
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(resultCode == RESULT_OK) {
//            if(requestCode == CAMERA_REQUEST) {
//                String photoPath = cameraPhoto.getPhotoPath();
//                try {
//                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
//                    ivImage.setImageBitmap(bitmap);
//                } catch (FileNotFoundException e) {
//                    Toast.makeText(getApplicationContext(), "Something wrong while loading photos", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else if(requestCode == GALLERY_REQUEST) {
//                Uri uri = data.getData();
//                galleryPhoto.setPhotoUri(uri);
//                String photoPath = galleryPhoto.getPath();
//                try {
//                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
//                    ivImage.setImageBitmap(bitmap);
//                } catch (FileNotFoundException e) {
//                    Toast.makeText(getApplicationContext(), "Something wrong while loading photos", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
    // END CAMERA OPERATIONS (PART 2)

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_interior:
                if (checked) {
                    radio_exterior.setChecked(false);
                    radio_backyard.setChecked(false);
                    layout_interior.setVisibility(View.VISIBLE);
                    layout_exterior.setVisibility(View.GONE);
                    layout_backyard.setVisibility(View.GONE);
                }
                break;
            case R.id.radio_exterior:
                if (checked) {
                    radio_interior.setChecked(false);
                    radio_backyard.setChecked(false);
                    layout_interior.setVisibility(View.GONE);
                    layout_exterior.setVisibility(View.VISIBLE);
                    layout_backyard.setVisibility(View.GONE);
                }
                break;
            case R.id.radio_backyard:
                if (checked) {
                    radio_interior.setChecked(false);
                    radio_exterior.setChecked(false);
                    layout_interior.setVisibility(View.GONE);
                    layout_exterior.setVisibility(View.GONE);
                    layout_backyard.setVisibility(View.VISIBLE);
                }
                break;
        }

    }


    //[THIS PIECE OF CODE BELOW ONLY ATTACHES THE LISTERNER TO EACH INDIVIDUAL CHECKBOX]
    public void CheckedItemCheckbox() {
        kitchen.setOnCheckedChangeListener(mCheckListener);
        living_room.setOnCheckedChangeListener(mCheckListener);
        bathroom.setOnCheckedChangeListener(mCheckListener);
        dinning_room.setOnCheckedChangeListener(mCheckListener);
        attic.setOnCheckedChangeListener(mCheckListener);
        entrance.setOnCheckedChangeListener(mCheckListener);
        bedroom.setOnCheckedChangeListener(mCheckListener);
        basement.setOnCheckedChangeListener(mCheckListener);
        other_backyard.setOnCheckedChangeListener(mCheckListener);
        other_exterior.setOnCheckedChangeListener(mCheckListener);
        other_interior.setOnCheckedChangeListener(mCheckListener);
        balcony.setOnCheckedChangeListener(mCheckListener);
        addition.setOnCheckedChangeListener(mCheckListener);
        closet.setOnCheckedChangeListener(mCheckListener);
        driveway.setOnCheckedChangeListener(mCheckListener);
        first_floor.setOnCheckedChangeListener(mCheckListener);
        fence.setOnCheckedChangeListener(mCheckListener);
        foundation.setOnCheckedChangeListener(mCheckListener);
        garage.setOnCheckedChangeListener(mCheckListener);
        garage_unit.setOnCheckedChangeListener(mCheckListener);
        porch.setOnCheckedChangeListener(mCheckListener);
        pool_jacuzzi.setOnCheckedChangeListener(mCheckListener);
        landscape.setOnCheckedChangeListener(mCheckListener);
        roof.setOnCheckedChangeListener(mCheckListener);
        septic.setOnCheckedChangeListener(mCheckListener);
        second_floor.setOnCheckedChangeListener(mCheckListener);
        trees.setOnCheckedChangeListener(mCheckListener);
        utility_room.setOnCheckedChangeListener(mCheckListener);
        well.setOnCheckedChangeListener(mCheckListener);

    }
}
