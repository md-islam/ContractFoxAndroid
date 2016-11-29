package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SkillSetActivity extends AppCompatActivity {

    String contractorID;
    ListView listView;
    List<String > skills = new ArrayList<>();

    private DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance()
            .getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        savedInstanceState = getIntent().getExtras();
        contractorID = savedInstanceState.getString("id");
        Log.i("id-:", contractorID);

//        listView = (ListView)findViewById(R.id.skillset_list_view);

        mFirebaseDatabaseReference
                .child("users").child(contractorID).child("skillSet")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

//                        Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
//
//                        List<Object> values = (List<Object>) td.values();
////
//                        listView.setAdapter((ListAdapter) values);


                        Iterable<DataSnapshot> skillList = dataSnapshot.getChildren();

                        for( DataSnapshot skill :  skillList){


//                                companyNames.add(snapshot.child("firstName").getValue().toString());
//                                map.put(count , snapshot.getKey().toString());
//                                count++;
                            skills.add(skill.toString());

                        }




//                        briefDesc = dataSnapshot.child("briefDescription").getValue().toString();
//                        briefDescription.setText(briefDesc);
//
//                        street = dataSnapshot.child("address").child("streetAddress").getValue().toString();
//                        unitNo = dataSnapshot.child("address").child("unit_Apt_no").getValue().toString();
//                        city = dataSnapshot.child("address").child("city").getValue().toString();
//                        state = dataSnapshot.child("address").child("state").getValue().toString();
//                        zipcode = dataSnapshot.child("address").child("zipCode").getValue().toString();
//
//                        if (unitNo.equals(null)){
//                            addressInput = street+", "+city+", "+state+zipcode;
//                        } else {
//                            addressInput = street+", "+unitNo+", "+city+", "+state+", "+zipcode;
//                        }
//
//                        address.setText(addressInput);
//
//                        phoneInput = dataSnapshot.child("phoneNo").getValue().toString();
//                        phoneNumber.setText(phoneInput);
//
//                        companyInput = dataSnapshot.child("firstName").getValue().toString();
//                        companyName.setText(companyInput);
//
//                        webInput = dataSnapshot.child("businessWebsiteURL").getValue().toString();
//                        website.setText(webInput);
//
//                        //miles.setText();
//
//                        urlAddress = webInput;

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, skills);

        listView = (ListView) findViewById(R.id.skillset_list_view);
        listView.setAdapter(arrayAdapter);

    }
}
