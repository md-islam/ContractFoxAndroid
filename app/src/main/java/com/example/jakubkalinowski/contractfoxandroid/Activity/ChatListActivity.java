package com.example.jakubkalinowski.contractfoxandroid.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.jakubkalinowski.contractfoxandroid.Model.ChatMessage;
import com.example.jakubkalinowski.contractfoxandroid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import it.slyce.messaging.SlyceMessagingFragment;
import it.slyce.messaging.listeners.UserSendsMessageListener;
import it.slyce.messaging.message.Message;
import it.slyce.messaging.message.MessageSource;
import it.slyce.messaging.message.TextMessage;

/**
 * Created by MD on 11/26/2016.
 */

public class ChatListActivity extends AppCompatActivity {

    //--VARIABLES RELATING TO FIREBASE VARIABLES -- [START]
    private DatabaseReference mFirebaseDatabaseMessagesReference;
    private String currentUser_SenderID;
    private String currentUserImageUri;
    private String recipientUserImageUri;
    private String reciepientID;
    private String chatSessionID;
    private FirebaseStorage storage;
    private StorageReference mStorageReference;
    private StorageReference mProfilePictureFolderReference;
    //--VARIABLES RELATING TO FIREBASE VARIABLES -- [END}

    //--MISCELLANEOUS VARIABLES FOR LOCAL USE -- [START]
    Intent currentIntent;
    private boolean updateLocalMessageUI;


    //--MISCELLANEOUS VARIABLES FOR LOCAL USE -- [END]


    //--VARIABLES RELATING TO THE UI/FRAGMENT COMPONENTS--[START]
    SlyceMessagingFragment mSlyceMessagingFragment;

    //--VARIABLES RELATING TO THE UI/FRAGMENT COMPONENTS--[END]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setContentView(R.layout.conversations_chat_messages_list_layout_activity);
        updateLocalMessageUI = true;
        if (user != null) {
            // User is signed in
            currentUser_SenderID = user.getUid();

        } else {
            // No user is signed in
            System.out.println("user is null and current user not found");
        }


        currentIntent  = getIntent();
        chatSessionID = currentIntent.getStringExtra("chatSessionId");
        reciepientID = currentIntent.getStringExtra("recipientId");
        storage = FirebaseStorage.getInstance();
        mStorageReference = storage.getReferenceFromUrl("gs://contract-fox.appspot.com/");
        mProfilePictureFolderReference = mStorageReference.child("ProfilePictures");
        setImageUriForTwoUsers();
        mFirebaseDatabaseMessagesReference = FirebaseDatabase.getInstance().getReference().getRoot().
                child("chatMessages");
        mSlyceMessagingFragment = (SlyceMessagingFragment) getFragmentManager().
                findFragmentById(R.id.slyc_messagingUI_fragment);
        mSlyceMessagingFragment.setDefaultDisplayName("Matthew Page");
        mSlyceMessagingFragment.setDefaultUserId("uhtnaeohnuoenhaeuonthhntouaetnheuontheuo");

        //--ATTACHING LISTENERS AND SETUPS HERE[START]
        attachMessageSenderListener();
        attachChatMessageListFirebaseChildEventListener();
        //--ATTACHING LISTENERS AND SETUPS HERE[END]
    }

    public void attachMessageSenderListener(){
        mSlyceMessagingFragment.setOnSendMessageListener(new UserSendsMessageListener() {
            @Override
            public void onUserSendsTextMessage(String text) {
                //Step 1 --> construct message Object here
                String chatMessagePushKey = mFirebaseDatabaseMessagesReference.
                        child(chatSessionID).push().getKey();
                HashMap<String, Object> dateMap= new HashMap<String, Object>();
                dateMap.put("date", ServerValue.TIMESTAMP);
                ChatMessage chatMessage = new ChatMessage(currentUser_SenderID, reciepientID,
                        dateMap, null, chatMessagePushKey, chatSessionID, text);
                updateLocalMessageUI = false;
                mFirebaseDatabaseMessagesReference.child(chatSessionID).
                        child(chatMessagePushKey).setValue(chatMessage);

            }

            @Override
            public void onUserSendsMediaMessage(Uri imageUri) {

            }
        });
    }

    public void attachChatMessageListFirebaseChildEventListener(){
        mFirebaseDatabaseMessagesReference.child(chatSessionID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(mSlyceMessagingFragment == null){
                    mSlyceMessagingFragment = (SlyceMessagingFragment) getFragmentManager().
                        findFragmentById(R.id.slyc_messagingUI_fragment);
                }
                if((updateLocalMessageUI == true )){
                    mSlyceMessagingFragment.addNewMessage(getLocalConstructedTextMessage
                            (dataSnapshot.getValue(ChatMessage.class)));
                } else if (updateLocalMessageUI == false && dataSnapshot.getValue(ChatMessage.class).getSenderUserId().equals(currentUser_SenderID)){
                    //dont add it to fragment
                } else{
                    mSlyceMessagingFragment.addNewMessage(getLocalConstructedTextMessage
                            (dataSnapshot.getValue(ChatMessage.class)));
                }
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
    }

    public Message getLocalConstructedTextMessage(ChatMessage chatMessageFirebase){

        TextMessage slyceTextMessage = new TextMessage();
        slyceTextMessage.setText(chatMessageFirebase.getMessageText());
        slyceTextMessage.setDate(chatMessageFirebase.getChatMessageServerTimestampLong());
        if(chatMessageFirebase.getSenderUserId().equals(currentUser_SenderID)){
            slyceTextMessage.setUserId(currentUser_SenderID);
            slyceTextMessage.setSource(MessageSource.LOCAL_USER);
            slyceTextMessage.setAvatarUrl("https://lh3.googleusercontent.com/-Y86IN-vEObo/AAAAAAAAAAI/AAAAAAAKyAM/6bec6LqLXXA/s0-c-k-no-ns/photo.jpg");

            System.out.println(currentUserImageUri);
        }else {
            slyceTextMessage.setUserId(reciepientID);
            slyceTextMessage.setSource(MessageSource.EXTERNAL_USER);
            slyceTextMessage.setAvatarUrl("http://rpmmultisite.s3-us-west-2.amazonaws.com/wp-content/uploads/sites/152/2015/07/HomepageContractor1.jpg");
            System.out.println(recipientUserImageUri);
        }
        return slyceTextMessage;
    }

    public void setImageUriForTwoUsers(){
        if (reciepientID != null && currentUser_SenderID != null) {
            StorageReference mCurrentUserImageReference = mStorageReference.
                    child(currentUser_SenderID+"/profilepic.jpeg");
            StorageReference mRecipientUserImageReference = mStorageReference.child(reciepientID+
                    "/profilepic.jpeg");
            mCurrentUserImageReference.getDownloadUrl().
                    addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    currentUserImageUri = uri.toString();
                    System.out.println(currentUserImageUri);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //handle this nonsense after graduation
                    //after you maintain member of course
                }
            });

            mRecipientUserImageReference.getDownloadUrl().
                    addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    recipientUserImageUri = uri.toString();
                    System.out.println(recipientUserImageUri);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Latitude and Longitude co-ordinates here
                }
            });
        }
    }

}
