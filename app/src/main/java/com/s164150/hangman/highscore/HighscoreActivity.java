package com.s164150.hangman.highscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.s164150.hangman.R;
import com.s164150.hangman.data.Highscores;

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
