package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView text ;
    private Runnable runnable = null , runnable1 = null ;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        text = (TextView) findViewById(R.id.splashText);
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                text.setVisibility(View.VISIBLE);
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.selector);

                text.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        handler.postDelayed(runnable1, 500);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        };

        runnable1 = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Introduction.class);
                startActivity(i);
            }
        };

        handler.postDelayed(runnable, 200);

    }
}
