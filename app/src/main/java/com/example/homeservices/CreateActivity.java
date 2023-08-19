package com.example.homeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
//    private EditText editUserName,editPassword,editEmail,editPhone,editAddress;
//    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mAuth = FirebaseAuth.getInstance();

        EditText editUserName = (EditText) findViewById(R.id.editUserName);
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        EditText editPhone = (EditText) findViewById(R.id.editPhone);
        EditText editAddress = (EditText) findViewById(R.id.editAddress);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        Button createButton = (Button) findViewById(R.id.createButton);

        // progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);





        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createButton();


            }

            private void createButton() {
                String userName = editUserName.getText().toString().trim();
                String Email = editEmail.getText().toString().trim();
                String Phone = editPhone.getText().toString().trim();
                String Address = editAddress.getText().toString().trim();
                String Password = editPassword.getText().toString().trim();



                if (userName.isEmpty()){
                    editUserName.setError("Username is required");
                    editUserName.requestFocus();
                    return;
                }

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

                if (Phone.isEmpty()){
                    editPhone.setError("Phone no. is required");
                    editPhone.requestFocus();
                    return;
                }

                if (!Patterns.PHONE.matcher(Phone).matches()){
                    editPhone.setError("Please Enter Valid Phone number");
                    editPhone.requestFocus();
                    return;
                }
                if (Address.isEmpty()){
                    editAddress.setError("Address is required");
                    editAddress.requestFocus();
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
                mAuth.createUserWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    User user = new User(userName,Email,Phone,Address);

                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(CreateActivity.this,"User has been register successfully",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);

                                                Intent createIntent = new Intent(CreateActivity.this,login.class);
                                                startActivity(createIntent);

                                            }
                                            else {
                                                Toast.makeText(CreateActivity.this,"Failed to Register! Try again!",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });

                                }else {
                                    Toast.makeText(CreateActivity.this,"Failed to Register",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                            }
                        });
            }
        });





        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}