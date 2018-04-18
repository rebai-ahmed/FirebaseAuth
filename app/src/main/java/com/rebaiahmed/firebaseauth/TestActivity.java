package com.rebaiahmed.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button submit;
    private TextView valueQ1, valueQ2, valueQ3, valueQ4, valueQ5;
    private SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        submit = (Button) findViewById(R.id.submitResults);
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
            score = Integer.parseInt(valueQ1.getText().toString()) + Integer.parseInt(valueQ2.getText().toString()) + Integer.parseInt(valueQ3.getText().toString()) + Integer.parseInt(valueQ4.getText().toString()) + Integer.parseInt(valueQ5.getText().toString());
            Intent resultIntent = new Intent(TestActivity.this, ResultActivity.class);
            resultIntent.putExtra("score", score);
            setResult(RESULT_OK, resultIntent);
//            startActivity(resultIntent);
            finish();
        }
    }
}
