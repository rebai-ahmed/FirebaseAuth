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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private Button register;
    private TextView signIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        signIn = (TextView) findViewById(R.id.signIn);
        register.setOnClickListener(this);
        signIn.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),TestActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {

        if (view == register) {
            registerUser();
        }
        if (view == signIn) {
            signInUser();
        }
    }

    private void signInUser() {
        startActivity(new Intent(this,LoginActivity.class));

    }

    private void registerUser() {
        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();
        if (emailInput.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
            return;
        }
        if (passwordInput.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Please wait ! ");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "You are registered successfully", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Registration Failed, Please try again !",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
