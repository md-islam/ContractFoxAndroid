package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Message_Room extends AppCompatActivity{

    private TextView message_conversation;
    private Button send_button;
    private EditText message_input;

    private String user_name, message_room_name;

    // message room reference
    private DatabaseReference messageReference;
    private String temp_key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_room);

        send_button = (Button)findViewById(R.id.send_btn);
        message_input = (EditText)findViewById(R.id.msg_input);
        message_conversation = (TextView)findViewById(R.id.msg_conversation);

        user_name = getIntent().getExtras().get("user_name").toString();
        message_room_name = getIntent().getExtras().get("estimate_title").toString();

        // over rite the title in the action bar with room name
        setTitle(" My Estimate - "+ message_room_name);

        messageReference = FirebaseDatabase.getInstance().getReference().child("estimate_title");

        // append the chat room with user message
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: get reference to the message receiver_id
                // Holds unique random keys
                Map<String,Object> map = new HashMap<String, Object>();

                // create and return random unique keys
                temp_key = messageReference.push().getKey();

                // append chat room with this unique key
                messageReference.updateChildren(map);

                // position inside of the random key object
                //TODO: position inside receiver_id instead
                DatabaseReference message_root = messageReference.child(temp_key);

                // Generate 2 more children (name and message)
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", user_name);
                map2.put("msg", message_input.getText().toString());

                // confirm changes and update screen
                message_root.updateChildren(map2);

            }
        });

        // listen for data changes and display them on the screen
        messageReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_message_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_message_conversation(dataSnapshot);
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

    private String chat_msg, chat_user_name;

    private void append_message_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {

            // read first child
            chat_msg = (String) ((DataSnapshot)i.next()).getValue();

            // read second child
            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();

            message_conversation.append(chat_user_name+" : "+chat_msg+ " \n");
        }
    }
}
