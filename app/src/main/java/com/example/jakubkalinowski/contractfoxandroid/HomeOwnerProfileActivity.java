package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class HomeOwnerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_owner_profile);


        // Get a reference to HomeOwner's Profile
        Firebase ref = new Firebase("https://contract-fox.firebaseio.com/users");
        // Attach an listener to read the data
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                    Member firstName = userSnapshot.getValue(Member.class);
                    System.out.println(Member.getFirstName());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });




    }
}
