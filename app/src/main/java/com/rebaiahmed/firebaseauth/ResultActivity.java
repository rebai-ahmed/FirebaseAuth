package com.rebaiahmed.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score = (TextView) findViewById(R.id.score);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int resultat;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            resultat = Integer.parseInt(data.getExtras().toString());
            if (resultat >= 100) {
                score.setText("You're in a good shape. Thank you for your precious time.");
            }
            if (resultat >= 80 && resultat < 100) {
                score.setText("You probably feel bored because you don't get enough entertainment.\nWe suggest you go for a jog and listen to some music.\nIt would certainly help.\nThank you for your precious time.");
            }
            if (resultat >= 60 && resultat < 80) {
                score.setText("You are having some serious problems ? \nWe suggest you continue our survey so you can determine your cause of anxiety.");
            }
            if (resultat < 60) {
                score.setText("Nothing to worry about but you should probably consult a doctor.");
            }
        }
    }
}
