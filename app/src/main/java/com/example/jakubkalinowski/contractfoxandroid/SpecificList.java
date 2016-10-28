
package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SpecificList extends AppCompatActivity {

    Button garage;
    TextView titleText ;
    String titleString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_lsit);
        savedInstanceState = getIntent().getExtras();
        titleString = savedInstanceState.getString("name");

        titleText = (TextView) findViewById(R.id.title_ID);
        titleText.setText(titleString);
        garage = (Button) findViewById(R.id.item);
        garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change here

                Intent i = new Intent(SpecificList.this, SearchViewListActivity.class);
                i.putExtra("serachedItem", titleString);
                startActivity(i);
                Log.d("garage", "button pushed");
            }
        });
    }
}
