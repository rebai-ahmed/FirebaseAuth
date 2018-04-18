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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText emailSignIn;
    private EditText passwordSignIn;
    private Button signIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailSignIn = (EditText) findViewById(R.id.emailSignIn);
        passwordSignIn = (EditText) findViewById(R.id.passwordSignIn);
        signIn = (Button) findViewById(R.id.signInBtn);
        progressDialog = new ProgressDialog(this);
        signIn.setOnClickListener(this);
        register = (TextView) findViewById(R.id.newUser);
        register.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(this,ProfileActivity.class));
        }

    }

    @Override
    public void onClick(View view) {
        if (view == signIn){
            userLogin();
        }
        if (view == register){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    private void userLogin() {
        String email = emailSignIn.getText().toString();
        String password = passwordSignIn.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Please wait ! ");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "You are logged in successfully", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                            finish();
                            startActivity(new Intent(getApplicationContext(),TestActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Login Failed, Please try again !",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
