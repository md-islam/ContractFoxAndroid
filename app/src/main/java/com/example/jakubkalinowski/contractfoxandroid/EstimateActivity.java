package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EstimateActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;

    final int CAMERA_REQUEST = 12345;
    final int GALLERY_REQUEST = 12345;

    LinearLayout layout_interior, layout_exterior, layout_backyard, layout_description;
    RadioButton radio_interior, radio_exterior, radio_backyard;
    EditText project_description;
    ImageView ivCamera, ivGallery, ivUpload, ivImage;

    Switch switchButton1, switchButton2;
    String switchOn = "YES";
    String switchOff = "NO";
    TextView textView1, textView2;

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
}
