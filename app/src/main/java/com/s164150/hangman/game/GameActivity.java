package com.s164150.hangman.game;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.s164150.hangman.R;
import com.s164150.hangman.game.fragment.ExitFragment;
import com.s164150.hangman.game.fragment.GameFragment;
import com.s164150.hangman.game.fragment.LoadFragment;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(savedInstanceState == null) {
            Fragment loadFragment = new LoadFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragcontainer, loadFragment, "LoadFragment")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        //Ensures the back button will only show the back fragment if the gamefragment is on top
        FragmentManager fragmentManager = getSupportFragmentManager();
        int i = fragmentManager.getBackStackEntryCount();
        if(i > 0) {
            String fragmentTag = fragmentManager.getBackStackEntryAt(i - 1).getName();
            Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);

            if(currentFragment instanceof GameFragment) {
                GameFragment gameFragment = (GameFragment) currentFragment;
                gameFragment.exit();
                System.out.println("Top fragment is gamefragment");
            }
        }
    }
}
