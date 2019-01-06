package com.s164150.hangman;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.s164150.hangman.game.GameActivity;
import com.s164150.hangman.highscore.HighscoreActivity;
import com.s164150.hangman.settings.SettingActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton play, highscore, settings, about;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.hangmanlogo);

        GlideApp.with(this).load(R.drawable.logoanimated).into(logo);

        play = findViewById(R.id.playbtn);
        highscore = findViewById(R.id.highscorebtn);
        settings = findViewById(R.id.settignsbtn);
        about = findViewById(R.id.aboutbtn);

        play.setOnClickListener(this);
        highscore.setOnClickListener(this);
        settings.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == play) {
            Intent game = new Intent(this, GameActivity.class);
            startActivity(game);
        }
        if(v == highscore) {
            Intent highscoreList = new Intent(this, HighscoreActivity.class);
            startActivity(highscoreList);
        }
        if(v == settings) {
            Intent wordList = new Intent(this, SettingActivity.class);
            startActivity(wordList);
        }
        if(v == about) {
            System.out.println("About pressed");
        }
    }
}
