package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class EstimateActivity extends AppCompatActivity {

    LinearLayout layout_interior, layout_exterior, layout_backyard, layout_description;
    RadioButton radio_interior, radio_exterior, radio_backyard;
    EditText project_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout_interior = (LinearLayout)findViewById(R.id.interior_fragment_content_layout);
        layout_exterior = (LinearLayout)findViewById(R.id.exterior_fragment_content_layout);
        layout_backyard = (LinearLayout)findViewById(R.id.backyard_fragment_content_layout);

        radio_interior = (RadioButton)findViewById(R.id.radio_interior);
        radio_exterior = (RadioButton)findViewById(R.id.radio_exterior);
        radio_backyard = (RadioButton) findViewById(R.id.radio_backyard);

        project_description = (EditText)findViewById(R.id.description_paragraph);
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
}
