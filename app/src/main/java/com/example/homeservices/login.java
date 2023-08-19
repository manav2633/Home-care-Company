package com.example.homeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // firebase
        mAuth = FirebaseAuth.getInstance();

        TextView forgot = (TextView) findViewById(R.id.forgot);
        TextView signUp = (TextView) findViewById(R.id.sign);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIntent = new Intent(login.this, CreateActivity.class);
                startActivity(signIntent);

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotIntent = new Intent(login.this,ForgotActivity.class);
                startActivity(forgotIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userLogin();

            }

            private void userLogin() {
                String Email = editEmail.getText().toString().trim();
                String Password = editPassword.getText().toString().trim();

                if (Email.isEmpty()){
                    editEmail.setError("Email is required");
                    editEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    editEmail.setError("Please Enter Valid EmailID");
                    editEmail.requestFocus();
                    return;
                }

                if (Password.isEmpty()){
                    editPassword.setError("Password is required");
                    editPassword.requestFocus();
                    return;
                }

                if (Password.length() < 6){
                    editPassword.setError("Minimum password length should be 6 characters!");
                    editPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            // email verify
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user.isEmailVerified()){
                                Intent loginIntent = new Intent(login.this,MainActivity.class);
                                startActivity(loginIntent);
                            }else {
                                user.sendEmailVerification();
                                Toast.makeText(login.this,"Check your email to verify your account",Toast.LENGTH_LONG).show();
                            }



                            progressBar.setVisibility(View.GONE);

                        }else {
                            Toast.makeText(login.this,"Failed to login!\nPlease Check your credentials",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }
}