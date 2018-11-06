package com.example.hangman.highscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.hangman.R;
import com.example.hangman.data.Highscores;

public class HighscoreActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Highscores highscores;
    private Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        recyclerView = findViewById(R.id.highscorelist);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        highscores = Highscores.getInstance(this);

        adapter = new HighscoreAdapter(highscores.getPlayerscores());
        recyclerView.setAdapter(adapter);

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
