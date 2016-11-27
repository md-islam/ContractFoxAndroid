package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jakubkalinowski.contractfoxandroid.Model.ChatMessage;
import com.example.jakubkalinowski.contractfoxandroid.Model.ChatSession;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EstimateActivity extends AppCompatActivity {

    private final String TAG = "ladimmm";
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    String firstName = "";

    private DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance()
            .getReference().getRoot();


    CameraPhoto cameraPhoto;
    static String currentUserId ;
    GalleryPhoto galleryPhoto;

    final int CAMERA_REQUEST = 12345;
    final int GALLERY_REQUEST = 12345;

    LinearLayout layout_interior, layout_exterior, layout_backyard, layout_description;
    RadioButton radio_interior, radio_exterior, radio_backyard;
    EditText project_description;
    ImageView ivCamera, ivGallery, ivUpload, ivImage;
    Button send ;

    Switch switchButton1, switchButton2;
    String switchOn = "YES";
    String switchOff = "NO";
    TextView textView1, textView2;
    static String [] ContracoorIds = new String[5]; // list of ids for contractors from previous view that has their
    // check box clicked.

    DatabaseReference messageReferencesDatabaseReference;
    DatabaseReference allMessagesDatabaseReference;

    static String receiverName,CurrentUsername, sendersName ;

    @Override
    protected void onStart() {
        super.onStart();

        mFirebaseDatabaseReference
                .child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sendersName = dataSnapshot.child(currentUserId).child("firstName").getValue(String.class);
                Log.i("gibsonSnap", firstName);

                receiverName = dataSnapshot.child(ContracoorIds[0]).child("firstName").getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
//            Log.i("ladimmm" ,user.getDisplayName());
//            Log.i("ladimmm" ,currentUserId);
        } else {
            // No user is signed in
            Log.i("ladimmm" ,"not signed in !!");
        }



        savedInstanceState = getIntent().getExtras();
        ContracoorIds = savedInstanceState.getStringArray("id");
        Log.d("i-d-est", ContracoorIds[0]);

        layout_interior = (LinearLayout)findViewById(R.id.interior_fragment_content_layout);
        layout_exterior = (LinearLayout)findViewById(R.id.exterior_fragment_content_layout);
        layout_backyard = (LinearLayout)findViewById(R.id.backyard_fragment_content_layout);
        send = ( Button)findViewById(R.id.sendButton);

        send.setOnClickListener( sendListener );


        radio_interior = (RadioButton)findViewById(R.id.radio_interior);
        radio_exterior = (RadioButton)findViewById(R.id.radio_exterior);
        radio_backyard = (RadioButton) findViewById(R.id.radio_backyard);

        project_description = (EditText)findViewById(R.id.description_paragraph_step3);

        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        ivImage = (ImageView)findViewById(R.id.ivImage);
        ivCamera = (ImageView)findViewById(R.id.ivCamera);
        ivGallery = (ImageView)findViewById(R.id.ivGallery);
//        ivUpload = (ImageView)findViewById(R.id.ivUpload);

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

        //----- STEP 2 -----

        // Switch Button 1
        switchButton1 = (Switch)findViewById(R.id.switch_q1);
        textView1 = (TextView) findViewById(R.id.textView1);

        switchButton1.setChecked(false);
        switchButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    textView1.setText(switchOn);
                } else {
                    textView1.setText(switchOff);
                }
            }
        });

        if (switchButton1.isChecked()) {
            textView1.setText(switchOn);
        } else {
            textView1.setText(switchOff);
        }
        // END Switch Button 1

        // Switch Button 2
        switchButton2 = (Switch)findViewById(R.id.switch_q2);
        textView2 = (TextView) findViewById(R.id.textView2);

        switchButton2.setChecked(false);
        switchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    textView2.setText(switchOn);
                } else {
                    textView2.setText(switchOff);
                }
            }
        });

        if (switchButton2.isChecked()) {
            textView2.setText(switchOn);
        } else {
            textView2.setText(switchOff);
        }

        //lets get the names here.




    }


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
//            case R.id.project_description_edit_text:
//                if (checked) {
//                    layout_description.setVisibility(View.VISIBLE);
//                }
        }


    }
    /*
    above here was jakub's work.
    below is the firebase part.
    type something is the part three of the estimate and click send.
    the method below is nvoked after clicking the send button in estimate page.
     */

    View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //get thenames first


            String description = project_description.getText().toString(); // the text that you typed.

            //New stuff from MD
            //Chat session
            //--------------------STEP 1-------CREATE A CHAT SESSION UNDER FOR USERS---------------//
            Map<String,Boolean> membersMap = new HashMap<String, Boolean>();
            membersMap.put(currentUserId, true);
            membersMap.put(ContracoorIds[0], true);
            String chatSessionKey = mFirebaseDatabaseReference.child("chatSessions").push().getKey();
            HashMap<String, Object> dateMap= new HashMap<String, Object>();
            dateMap.put("date", ServerValue.TIMESTAMP);
            ChatSession chatSession = new ChatSession(dateMap,chatSessionKey,
                    membersMap, description, description, dateMap);
//            chatSession.setUsersInChat(membersMap);
            mFirebaseDatabaseReference.child("chatSessions").child(chatSessionKey).
                    setValue(chatSession);
            //------------------STEP 1 ----------------------------------//

            //------------------------STEP 2 -----------APPEND UNDER CHAT SESSION ID-------------//

            String chatMessageKey = mFirebaseDatabaseReference.child("chatMessages").
                    child(chatSessionKey).push().getKey();
            ChatMessage chatMessage = new ChatMessage(currentUserId, ContracoorIds[0],
                    dateMap, null,chatMessageKey, chatSessionKey, description);
            mFirebaseDatabaseReference.child("chatMessages").child(chatSessionKey).
                    child(chatMessageKey).setValue(chatMessage);
            //------------------------STEP 2 ----------------------------------------------------//

            //------------------------Step 3 ---------LINK REFERENCES----------------------------//
            //---this step is meant to update children--//
            Map<String, Boolean> chatSessionIdMap = new HashMap<String, Boolean>();
            chatSessionIdMap.put(chatSessionKey, true);
            Map<String,Object> chatSessionIds = new HashMap<String, Object>();
            chatSessionIds.put( currentUserId+"/chatSessions/"+chatSessionKey,true);
            chatSessionIds.put( ContracoorIds[0]+"/chatSessions/"+chatSessionKey,true);
            mFirebaseDatabaseReference.child("users").updateChildren(chatSessionIds);
            //-------------------------Step 3-----------------------------------------------------//




//////////////////////this part is done. Each user is apporpiraitely updated ////////////////////////////////
            //messageReference update below:
//            Map<String, Object> messageReference = new HashMap<>();
//            messageReference.put(currentUserId, "");
//            messageReference.put(ContracoorIds[0], "");
//            mFirebaseDatabaseReference.child("messageReferences").updateChildren(messageReference);
//////////////////////this part is done. IDs are added as immidiate children of message references.  ////////////////////////////////


//            Map<String , Object> reciverMesList = new HashMap<>();
//            Map<String , Object> senderMesList = new HashMap<>();

            /*
            the value in the map is being put with a slash to avoid dta to be replaced when i use update children.
            but id didnt work. it needs some tweaking.
            IMPORTANT: check out the link below to understand why i use the slash and fix it possibly.
            https://firebase.google.com/docs/database/admin/save-data
            remember ContracoorIds[0] will be the first and only one in the list if you only checked on e contractor from the list of contractors.
            you could use a loop to go through all of them to implement messaging multiple contractors.
            for now focus on one to one. it is scalable.
             */
//            senderMesList.put(ContracoorIds[0]+"/"+currentUserId , sendersName); //this value ---
//
//            senderMesList.put(currentUserId+"/"+ContracoorIds[0] ,receiverName);//this value ---


//            mFirebaseDatabaseReference.child("messageReferences").updateChildren(senderMesList);
//            mFirebaseDatabaseReference.child("messageReferences").updateChildren(senderMesList);
            //  mFirebaseDatabaseReference.child("messageReferences").child(currentUserId).updateChildren(reciverMesList);

///////////////////////this part is done ////////////////////////////////////////////////////////////////


//            Map<String, Object > allMessageMap = new HashMap<>();
//            allMessageMap.put(currentUserId+ContracoorIds[0], "");//this value ---
//            mFirebaseDatabaseReference.child("allMessages").updateChildren(allMessageMap);

            //these two line gets the current date in the format that i wanted. note:
            // slashes are not allowed to be passed as keys. ('/'). that is why i used '-'. but
            //anyways you will use long or ts or String since it is required.
            // here we update the allMessages reference.

//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String currentDateandTime = sdf.format(new Date());

//            Map<String, Object > initialMessageMap = new HashMap<>();
//            initialMessageMap.put(currentDateandTime , description);
//            mFirebaseDatabaseReference.child("allMessages").child(currentUserId+ContracoorIds[0]).updateChildren(initialMessageMap);
        }
    };

//---------------------------------------------------------------------

}