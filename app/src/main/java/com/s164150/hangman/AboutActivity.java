package com.s164150.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == backbtn) {
            finish();
        }
    }
}
