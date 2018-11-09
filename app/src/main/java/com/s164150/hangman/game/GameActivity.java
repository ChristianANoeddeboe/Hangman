package com.s164150.hangman.game;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.s164150.hangman.R;
import com.s164150.hangman.game.fragment.GameFragment;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(savedInstanceState == null) {
            Fragment gameFragment = new GameFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragcontainer, gameFragment)
                    .commit();
        }


    }

}
