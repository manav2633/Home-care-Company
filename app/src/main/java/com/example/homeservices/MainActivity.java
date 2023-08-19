package com.example.homeservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private int selectedTab = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout calenderLayout = findViewById(R.id.calenderLayout);
        final LinearLayout aboutLayout = findViewById(R.id.aboutLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);


        final ImageView home = findViewById(R.id.home1);
        final ImageView calender = findViewById(R.id.calender);
        final ImageView aboutUs = findViewById(R.id.aboutUs);
        final ImageView profile = findViewById(R.id.profile);
//        final ImageButton profileButton = findViewById(R.id.profileButton);

        // set home fragment by default
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer,HomeFragment.class,null)
                .commit();






        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



             // check if home is already selected or not
             if(selectedTab != 1){



                 // set home fragment by default
                 getSupportFragmentManager().beginTransaction()
                         .setReorderingAllowed(true)
                         .replace(R.id.fragmentContainer,HomeFragment.class,null)
                         .commit();

                 calenderLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                 aboutLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                 profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

             // homeLayout background

             homeLayout.setBackgroundResource(R.drawable.round_back);

             // create Animation

                 ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                 scaleAnimation.setDuration(200);
                 scaleAnimation.setFillAfter(true);
                 homeLayout.startAnimation(scaleAnimation);

             // set first tab has selected

             selectedTab = 1;
             }
            }
        });

        calenderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if calender is already selected or not
                if(selectedTab != 2){

                    // set calender fragment

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,CalenderFragment.class,null)
                            .commit();

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    aboutLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // homeLayout background

                   calenderLayout.setBackgroundResource(R.drawable.round_back);

                    // create Animation

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    calenderLayout.startAnimation(scaleAnimation);

                    // set first tab has selected

                    selectedTab = 2;
                }

            }
        });



        aboutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if about is already selected or not
                if(selectedTab != 3){

                    //set about fragment

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,AboutFragment.class,null)
                            .commit();

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    calenderLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // homeLayout background

                    aboutLayout.setBackgroundResource(R.drawable.round_back);

                    // create Animation

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    aboutLayout.startAnimation(scaleAnimation);

                    // set first tab has selected

                    selectedTab = 3;
                }
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedTab != 4){

                    // set profile fragment

//                    getSupportFragmentManager().beginTransaction()
//                            .setReorderingAllowed(true)
//                            .replace(R.id.fragmentContainer,ProfileFragment.class,null)
//                            .commit();

                    Intent profileIntent = new Intent(MainActivity.this,ProfileActivity.class);
                    startActivity(profileIntent);

                homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                calenderLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                aboutLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                // homeLayout background

                profileLayout.setBackgroundResource(R.drawable.round_back);

                // create Animation

                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                profileLayout.startAnimation(scaleAnimation);

                // set first tab has selected

                selectedTab = 4;
            }
            }
        });




    }
}