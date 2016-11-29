package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MessageActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> estimate_list = new ArrayList<>();
    private DatabaseReference mDatabaseMessageReferance;

    Member member;
    private String mFirstName = "bob";

    private String contractorID2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        contractorID2 = getIntent().getExtras().getString("contID");

        listView = (ListView) findViewById(R.id.estimate_list_container);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, estimate_list);

        listView.setAdapter(arrayAdapter);

        mDatabaseMessageReferance = FirebaseDatabase.getInstance().getReference("messages");

//        mFirstName = member.getFirstName();

        // Show available estimate chats in the listView
        mDatabaseMessageReferance.addValueEventListener(new ValueEventListener() {
            // Called when new estimate is posted or app is accessed
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //This will avoid duplicates in the listView
                //it contains all the chat room names
                Set<String> set = new HashSet<String>();

                // Go through the DB and read it
                // this reads only the first level of children
                Iterator i = dataSnapshot.getChildren().iterator();

                // read DB line by line
                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                //clear the current list
                estimate_list.clear();

                // appent the current list with temporary HashSet
                estimate_list.addAll(set);

                // see the updated changes on the screen
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Go into the chat room and carry a dialog
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Intent to lead to Message Room
                Intent intent = new Intent(getApplicationContext(), Message_Room.class);

                // extract room name from selected list item and pass the name of the active message room chosen
                intent.putExtra("estimate_title", ((TextView)view).getText().toString());

                // pass the name of the current user
                intent.putExtra("user_name", mFirstName);

                startActivity(intent);
            }
        });

    }
}
