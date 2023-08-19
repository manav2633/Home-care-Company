package com.example.homeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LinearLayout homeCareLayout = (LinearLayout) findViewById(R.id.homeCareLayout);
        TextView homeCareView = (TextView) findViewById(R.id.homeCareView);
        TextView companyView = (TextView) findViewById(R.id.companyView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splash_animation);
                homeCareView.startAnimation(animation);
//                companyView.startAnimation(animation);


                homeCareView.setVisibility(View.VISIBLE);
//                companyView.setVisibility(View.VISIBLE);

            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splash_animation);
//                homeCareLayout.startAnimation(animation);
                companyView.startAnimation(animation);


                companyView.setVisibility(View.VISIBLE);

            }
        },2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeCare = new Intent(SplashActivity.this,DragActivity.class);
                startActivity(homeCare);


            }
        },5000);

    }
}