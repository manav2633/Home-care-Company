package com.example.homeservices;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppointmentActivity extends AppCompatActivity {

    private int selectedTab = 2;

    EditText editText;
    DatePickerDialog datePicker;
    ImageButton imageButton;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseAuth mAuth;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);



       /* below code for calender*/


        // initialising the calendar
        final Calendar calendar = Calendar.getInstance();

        // initialising the layout
        editText = findViewById(R.id.editDate);
        imageButton = findViewById(R.id.calenderButton);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        // initialising the datepickerdialog
        datePicker = new DatePickerDialog(AppointmentActivity.this);

        // click on edittext to set the value
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        editText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);

                // set maximum date to be selected as today
                datePicker.getDatePicker().setMinDate(calendar.getTimeInMillis());

                // show the dialog
                datePicker.show();
            }
        });





        /* below code for parsing json file layout */

        String name = getIntent().getExtras().getString("name");
        String field = getIntent().getExtras().getString("field");
        String rating = getIntent().getExtras().getString("rating");
        String image = getIntent().getExtras().getString("image");


        TextView name1 = findViewById(R.id.name);
        TextView field1 = findViewById(R.id.field);
        TextView rating1 = findViewById(R.id.rating);
        ImageView image1 = findViewById(R.id.worker_image);


        name1.setText(name);
        field1.setText(field);
        rating1.setText(rating);

        // set image

        Glide.with(this).load(image).into(image1);



        /* below code for bottom navigation bar */

        final LinearLayout Layout1 = findViewById(R.id.Linear1);
        final LinearLayout Layout2 = findViewById(R.id.Linear2);
        final LinearLayout Layout3 = findViewById(R.id.Linear3);
        final Button create = findViewById(R.id.createButton);


        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout calenderLayout = findViewById(R.id.calenderLayout);
        final LinearLayout aboutLayout = findViewById(R.id.aboutLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);


        final ImageView home = findViewById(R.id.home1);
        final ImageView calender = findViewById(R.id.calender);
        final ImageView aboutUs = findViewById(R.id.aboutUs);
        final ImageView profile = findViewById(R.id.profile);

        // set home fragment by default




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


                    //hide

                    Layout1.setVisibility(View.GONE);
                    Layout2.setVisibility(View.GONE);
                    Layout3.setVisibility(View.GONE);
                    create.setVisibility(View.GONE);

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

                    //hide

                    Layout1.setVisibility(View.GONE);
                    Layout2.setVisibility(View.GONE);
                    Layout3.setVisibility(View.GONE);
                    create.setVisibility(View.GONE);

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

                    //hide

                    Layout1.setVisibility(View.GONE);
                    Layout2.setVisibility(View.GONE);
                    Layout3.setVisibility(View.GONE);
                    create.setVisibility(View.GONE);


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

                    Intent profileIntent = new Intent(AppointmentActivity.this,ProfileActivity.class);
                    startActivity(profileIntent);
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

                    //hide

                    Layout1.setVisibility(View.GONE);
                    Layout2.setVisibility(View.GONE);
                    Layout3.setVisibility(View.GONE);
                    create.setVisibility(View.GONE);


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


        ImageButton profileButton = (ImageButton) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction()
//                        .setReorderingAllowed(true)
//                        .replace(R.id.fragmentContainer,ProfileFragment.class,null)
//                        .commit();

                Intent profileButton = new Intent(AppointmentActivity.this,ProfileActivity.class);
                startActivity(profileButton);
//                homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                calenderLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//                aboutLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//
//                // homeLayout background
//
//                profileLayout.setBackgroundResource(R.drawable.round_back);
//
//                // create Animation
//
//                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
//                scaleAnimation.setDuration(200);
//                scaleAnimation.setFillAfter(true);
//                profileLayout.startAnimation(scaleAnimation);
//
//                //hide
//
//                Layout1.setVisibility(View.GONE);
//                Layout2.setVisibility(View.GONE);
//                Layout3.setVisibility(View.GONE);
//                create.setVisibility(View.GONE);
            }
        });

        EditText Location = (EditText) findViewById(R.id.editLocation);
        EditText Date = (EditText) findViewById(R.id.editDate);
        Button Book = (Button) findViewById(R.id.createButton);
        TextView EmailText = (TextView) findViewById(R.id.EmailText);
        TextView PhoneText = (TextView) findViewById(R.id.PhoneText);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();
        mAuth = FirebaseAuth.getInstance();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String Email = userProfile.Email;
                    String Phone = userProfile.Phone;

                    EmailText.setText(Email);
                    PhoneText.setText(Phone);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AppointmentActivity.this,"Something wrong happend!",Toast.LENGTH_LONG).show();
            }
        });

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bookNow();

                try {

                    String Email = EmailText.getText().toString().trim();
                    String Phone = PhoneText.getText().toString().trim();
                    String location = Location.getText().toString().trim();
                    String date = Date.getText().toString().trim();

                    if (location.isEmpty()) {
                        Location.setError("Location is required!");
                        Location.requestFocus();
                        return;
                    }
                    if (date.isEmpty()) {
                        Date.setError("Date is required!");
                        Date.requestFocus();
                        return;
                    } else {

                        String stringSenderEmail = "homeservicesm12@gmail.com";
                        String stringReceiverEmail = "manavchaniyara29@gmail.com";
                        String stringPasswordSenderEmail = "gvqcsgnpyzafyccj";
                        String stringUserReceiverEmail = Email;


                        String stringHost = "smtp.gmail.com";
                        Properties properties = System.getProperties();

                        properties.put("mail.smtp.host", stringHost);
                        properties.put("mail.smtp.port", "465");
                        properties.put("mail.smtp.ssl.enable", "true");
                        properties.put("mail.smtp.auth", "true");

                        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                            }
                        });


                        //owner
                        MimeMessage mimeMessage = new MimeMessage(session);
                        //user
                        MimeMessage mimeMessage1 = new MimeMessage(session);

                        //owner
                        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
                        mimeMessage.setSubject("Home services");
                        mimeMessage.setText("Worker_name: " + name + "\n\nWorker_Profession: " + field + "\n\nUser_mail: " + Email + "\n\nLocation: " + location + "\n\nDate: " + date + "\n\nUser_Phone: " + Phone);

                        //users
                        mimeMessage1.addRecipient(Message.RecipientType.TO,new InternetAddress(stringUserReceiverEmail));
                        mimeMessage1.setSubject("Home services");
                        mimeMessage1.setText("Worker_name: "+name+"\n\nWorker_Profession: "+field+"\n\nDate: "+date+ "\n\nIf any issues please contact: 9510848551 "+ "\n\n\nBooked Successfully..."+"\n\nThank you...");

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Transport.send(mimeMessage);
                                    Transport.send(mimeMessage1);
                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        thread.start();
                    }
                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }


            }
            private void bookNow() {
                String Email = EmailText.getText().toString().trim();
                String Phone = PhoneText.getText().toString().trim();
                String location = Location.getText().toString().trim();
                String date = Date.getText().toString().trim();

                if (location.isEmpty()){
                    Location.setError("Location is required!");
                    Location.requestFocus();
                    return;
                }
                if (date.isEmpty()){
                    Date.setError("Date is required!");
                    Date.requestFocus();
                    return;
                }

                Book book = new Book(Email,Phone,location,date,name,field);
                FirebaseDatabase.getInstance().getReference("Book")
                        .child(FirebaseAuth.getInstance().getUid())
                        .setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AppointmentActivity.this,"  Booked successfully",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            Toast.makeText(AppointmentActivity.this,"Failed to Book! Try again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });



    }

}