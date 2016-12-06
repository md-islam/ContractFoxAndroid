package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jakubkalinowski.contractfoxandroid.Model.ChatMessage;
import com.example.jakubkalinowski.contractfoxandroid.Model.ChatSession;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;

import java.util.HashMap;
import java.util.Map;

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

    private EditText project_title, project_description, mitemAreaDimensionsEditText;

    Button send;
    private CompoundButton.OnCheckedChangeListener mInteriorCheckListener;
    //-----UI COMPONENT VARIABLES DECLARATION-----[END]

    Switch switchButton1, switchButton2;
    String switchOn = "YES";
    String switchOff = "NO";
    String materialProvideSwitchYes = "Customer will provide materials";
    String materialProvideSwitchNo = "Customer wont provide materials";
    String materialDeliverySwitchYes = "Customer will need material delivery";
    String materialDeliverySwitchNo = "Customer will not need material delivery";
    TextView textView1, textView2;
    static String [] ContracoorIds = new String[5]; // list of ids for contractors from previous view that has their
    // check box clicked.

    DatabaseReference messageReferencesDatabaseReference;
    DatabaseReference allMessagesDatabaseReference;

    private FirebaseStorage storage;
    private StorageReference uploadRef;
    private ImageView mAddImageToGallery, mImageView;
    // integer for request code
    private static final int GALLERY_INTENT = 2;
    static String receiverName,CurrentUsername, sendersName ;

    @Override
    protected void onStart() {
        super.onStart();

        storage = FirebaseStorage.getInstance();
        uploadRef = storage.getReference("EstimateFiles/"+receiverName+"file.jpeg");


        mFirebaseDatabaseReference
                .child("usersInChat").addListenerForSingleValueEvent(new ValueEventListener() {
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

        mAddImageToGallery = (ImageView)findViewById(R.id.ivGallery);
        mImageView = (ImageView)findViewById(R.id.ivImage);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        } else {
            // No user is signed in
            Log.i("ladimmm" ,"not signed in !!");
        }

        savedInstanceState = getIntent().getExtras();
        ContracoorIds = savedInstanceState.getStringArray("id");
        Log.d("i-d-est", ContracoorIds[0]);

        mAddImageToGallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(Intent.ACTION_PICK);

                i.setType("image/*");

                startActivityForResult(i, GALLERY_INTENT);
            }
        });

        send = ( Button)findViewById(R.id.sendButton);

        send.setOnClickListener( sendListener );

        project_title = (EditText) findViewById(R.id.project_title_edit_text);
        mitemAreaDimensionsEditText = (EditText) findViewById(R.id.item_area_specs_edit_text);
        project_description = (EditText)findViewById(R.id.detail_description_edit_text);

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
    }

    View.OnClickListener sendListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String description = "";

            description = "<---" + project_title.getText().toString() + "-->";
            description = description.concat("Contract description: \n");
            description = description.concat("\n" + project_description.getText().toString()); // the text that you typed.
            description = description.concat("\n" + "<-Item/Area Dimensions and Specs-> \n" +
                    mitemAreaDimensionsEditText.getText().toString());
            String meta_description_data = "";
            if(switchButton1.isChecked()){
                meta_description_data.concat("\n"+materialProvideSwitchYes);
            }else {
                meta_description_data.concat("\n"+materialProvideSwitchNo);
            }

            if(switchButton2.isChecked()){
                meta_description_data.concat("\n"+materialDeliverySwitchYes);
            }else{
                meta_description_data.concat("\n"+materialDeliverySwitchNo);
            }

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
                    membersMap, project_title.getText().toString(), description.concat(meta_description_data), dateMap);

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

            Toast.makeText(getApplicationContext() , " Your message was sent!" , Toast.LENGTH_LONG).show();
            onBackPressed();

        }
    };

//---------------------------------------------------------------------

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            Uri uri = data.getData();

            uploadRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Glide.with(EstimateActivity.this).using(new FirebaseImageLoader()).load(uploadRef).into(mImageView);
                }
            });
        }
    }
}