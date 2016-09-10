package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Introduction extends AppCompatActivity {

    Button signUp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        signUp = (Button )findViewById(R.id.singUp);

        FirebaseDatabase mFirebaseDatabaseReference = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = mFirebaseDatabaseReference.getReference();

        //myRef.child("test1").child("test2").setValue("Hello, World Bitch");

        //Toast.makeText(getApplicationContext(), "SIGN UP Button is clicked"+myRef.toString(), Toast.LENGTH_LONG).show();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Introduction.this, LoginActivity.class);
                startActivity(i);


            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
