package com.example.homeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    // firebase
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    private int selectedTab = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout calenderLayout = findViewById(R.id.calenderLayout);
        final LinearLayout aboutLayout = findViewById(R.id.aboutLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);



        final ImageView home = findViewById(R.id.home1);
        final ImageView calender = findViewById(R.id.calender);
        final ImageView aboutUs = findViewById(R.id.aboutUs);
        final ImageView profile = findViewById(R.id.profile);

        final LinearLayout Layout4 = findViewById(R.id.Linear4);
        final LinearLayout Layout5 = findViewById(R.id.Linear5);



        calenderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if calender is already selected or not
                if(selectedTab != 2){


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


                    // hide

                    Layout4.setVisibility(View.GONE);
                    Layout5.setVisibility(View.GONE);

                    // set first tab has selected

                    selectedTab = 2;
                }

            }
        });


        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // check if home is already selected or not
                if(selectedTab != 1){





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


                    // hide

                    Layout4.setVisibility(View.GONE);
                    Layout5.setVisibility(View.GONE);

                    // set first tab has selected

                    selectedTab = 1;
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


                    // hide

                    Layout4.setVisibility(View.GONE);
                    Layout5.setVisibility(View.GONE);

                    // set first tab has selected

                    selectedTab = 3;
                }
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(selectedTab != 4){

                    Intent profileIntent = new Intent(ProfileActivity.this,ProfileActivity.class);
                    startActivity(profileIntent);

//                    // set profile fragment
//
//                    getSupportFragmentManager().beginTransaction()
//                            .setReorderingAllowed(true)
//                            .replace(R.id.fragmentContainer,ProfileFragment.class,null)
//                            .commit();

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

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


      // firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        final TextView nameView = (TextView) findViewById(R.id.nameView);
        final TextView emailView = (TextView) findViewById(R.id.emailView);
        final TextView addressView = (TextView) findViewById(R.id.addressView);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String Email = userProfile.Email;
                    String userName = userProfile.userName;
                    String Address = userProfile.Address;


                    nameView.setText(userName);
                    emailView.setText(Email);
                    addressView.setText(Address);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProfileActivity.this,"Something wrong happend!",Toast.LENGTH_LONG).show();
            }
        });


    }
}