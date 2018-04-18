package com.rebaiahmed.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FilmsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText filmName;
    private Button submit;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        filmName = (EditText) findViewById(R.id.filmName);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("films");

    }

    @Override
    public void onClick(View view) {
        if (view == submit) {
            addFilm();
        }
    }

    private void addFilm() {
        String filmNameInput = filmName.getText().toString().trim();
        String userId = firebaseAuth.getCurrentUser().getUid();
        Log.i("userId",""+userId);
        Film film = new Film(filmNameInput, userId);
        String filmId = databaseReference.push().getKey();
        databaseReference.child(filmId).setValue(film).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Film added ...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
