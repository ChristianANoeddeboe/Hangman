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
        FragmentManager fragmentManager = getSupportFragmentManager();
        int i = fragmentManager.getBackStackEntryCount();
        String fragmentTag = fragmentManager.getBackStackEntryAt(i - 1).getName();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);

        if(currentFragment instanceof GameFragment) {
            System.out.println("Top fragment is gamefragment");
        }

        GameFragment gameFragment = (GameFragment) getSupportFragmentManager().findFragmentByTag("GameFragment");
        ExitFragment exitFragment = (ExitFragment) getSupportFragmentManager().findFragmentByTag("ExitFragment");
        //isResumed used instead of isVisible as this ensures the fragment is actually usable by the user.
        if(gameFragment != null && gameFragment.isResumed() && exitFragment == null) {
            gameFragment.exit();
        }
        //Used to disable back button in the game, such people wouldn't interrupt their game.
    }
}
