package com.rebaiahmed.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button submit;
    private Button goToProfile;
    private TextView valueQ1, valueQ2, valueQ3, valueQ4, valueQ5;
    private SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        submit = (Button) findViewById(R.id.submitResults);
        submit.setOnClickListener(this);
        goToProfile = (Button) findViewById(R.id.goToProfile);
        goToProfile.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("testResults");
        valueQ1 = (TextView) findViewById(R.id.value1);
        valueQ2 = (TextView) findViewById(R.id.value2);
        valueQ3 = (TextView) findViewById(R.id.value3);
        valueQ4 = (TextView) findViewById(R.id.value4);
        valueQ5 = (TextView) findViewById(R.id.value5);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar == seekBar1)
                {
                    valueQ1.setText(""+i);
                }
                if (seekBar == seekBar2)
                {
                    valueQ2.setText(""+i);
                }
                if (seekBar == seekBar3)
                {
                    valueQ3.setText(""+i);
                }
                if (seekBar == seekBar4)
                {
                    valueQ4.setText(""+i);
                }
                if (seekBar == seekBar5)
                {
                    valueQ5.setText(""+i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        seekBar1.setOnSeekBarChangeListener(listener);
        seekBar2.setOnSeekBarChangeListener(listener);
        seekBar3.setOnSeekBarChangeListener(listener);
        seekBar4.setOnSeekBarChangeListener(listener);
        seekBar5.setOnSeekBarChangeListener(listener);


    }

    @Override
    public void onClick(View view) {
        if (view == submit) {
            Log.e("Hello","I'm at submit");
            if (firebaseAuth.getCurrentUser()!=null)
            {
                addTestResults();
            }
            score = seekBar1.getProgress() + seekBar2.getProgress() + seekBar3.getProgress() + seekBar4.getProgress() + seekBar5.getProgress();
            Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
            resultIntent.putExtra("score", score);
            setResult(RESULT_OK, resultIntent);
            startActivityForResult(resultIntent,100);
            finish();
        }
        if (view == goToProfile)
        {
            startActivity(new Intent(getApplication(),ProfileActivity.class));
            finish();
        }
    }

    private void addTestResults() {
        String userId = firebaseAuth.getCurrentUser().getUid();
        int valueQ1 = seekBar1.getProgress();
        int valueQ2 = seekBar2.getProgress();
        int valueQ3 = seekBar3.getProgress();
        int valueQ4 = seekBar4.getProgress();
        int valueQ5 = seekBar5.getProgress();

        Test test = new Test(userId,valueQ1,valueQ2,valueQ3,valueQ4,valueQ5);
        String testId = databaseReference.push().getKey();
        Log.d("TestId : ",testId);
        databaseReference.child(testId).setValue(test).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(TestActivity.this, "You have successfully uploaded your results.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
