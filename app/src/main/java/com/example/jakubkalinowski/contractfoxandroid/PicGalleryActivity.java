package com.example.jakubkalinowski.contractfoxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class PicGalleryActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_gallery);

        mImageView = (ImageView)findViewById(R.id.gallery_image1);
    }
}
