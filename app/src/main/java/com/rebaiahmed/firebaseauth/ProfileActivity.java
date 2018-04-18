package com.rebaiahmed.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView username;
    private EditText fullName;
    private EditText phoneNumber;
    private EditText postalAddress;
    private DatabaseReference databaseReference;
    private Button logOut;
    private Button saveInfo;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initializing views

        logOut = (Button) findViewById(R.id.logOut);
        username = (TextView) findViewById(R.id.userName);
        fullName = (EditText) findViewById(R.id.fullName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        postalAddress = (EditText) findViewById(R.id.postalAddress);
        saveInfo = (Button) findViewById(R.id.saveInfo);
        progressDialog = new ProgressDialog(this);
        saveInfo.setOnClickListener(this);
        logOut.setOnClickListener(this);

        //Initializing firebaseAuth

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            username.setText("Welcome " + firebaseAuth.getCurrentUser().getEmail());
        }

        //Initializing firebase Database

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void onClick(View view) {
        if (view == logOut) {
            logOutUser();
        }
        if (view == saveInfo) {
            saveUserInformation();
        }
    }

    private void goToAddFilm() {

        startActivity(new Intent(getApplicationContext(), FilmsActivity.class));
        finish();
    }

    private void logOutUser() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void saveUserInformation() {
        String fullNameInput = fullName.getText().toString().trim();
        String phoneNumberInput = phoneNumber.getText().toString().trim();
        String postalAddressInput = postalAddress.getText().toString().trim();

        UserInformation userInformation = new UserInformation(fullNameInput, phoneNumberInput, postalAddressInput);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        progressDialog.setMessage("Your information is being saved\nPlease wait ...");
        progressDialog.show();
        databaseReference.child(firebaseUser.getUid()).setValue(userInformation).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), "Information saved ...", Toast.LENGTH_LONG).show();
            }
        });

    }
}
