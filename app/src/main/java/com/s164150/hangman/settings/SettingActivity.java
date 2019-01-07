package com.s164150.hangman.settings;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.s164150.hangman.R;
import com.s164150.hangman.data.Words;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Words words;
    private Button backbtn;
    private Button[] disablebtns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        recyclerView = findViewById(R.id.wordlist);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        words = words.getInstance(this);

        adapter = new SettingAdapter(words.getWordList());
        recyclerView.setAdapter(adapter);



        for(int position = 0 ; position < adapter.getItemCount() ; position++) {

        }

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
