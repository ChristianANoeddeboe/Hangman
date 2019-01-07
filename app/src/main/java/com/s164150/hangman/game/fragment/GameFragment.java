package com.s164150.hangman.game.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.s164150.hangman.Galgelogik;
import com.s164150.hangman.PlaySound;
import com.s164150.hangman.R;
import com.s164150.hangman.data.Highscores;

public class GameFragment extends Fragment implements View.OnClickListener {

    ImageButton exitbtn, guessbtn;
    ImageView levelView;
    TextView wordText, guessText;
    EditText guessInput;

    //Used for testing.
    public static boolean testing = false;

    InputMethodManager imm;

    Galgelogik galgelogik;

    Highscores highscores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_game, container, false);

        exitbtn = parent.findViewById(R.id.exitbtn);
        guessbtn = parent.findViewById(R.id.guessbtn);

        exitbtn.setOnClickListener(this);
        guessbtn.setOnClickListener(this);

        levelView = parent.findViewById(R.id.levelView);

        wordText = parent.findViewById(R.id.wordText);
        guessText = parent.findViewById(R.id.guessText);

        guessInput = parent.findViewById(R.id.guessInput);

        galgelogik = Galgelogik.getinstance();

        highscores = Highscores.getInstance(getActivity());

        newgame();

        PlaySound.getInstance(getContext()).playSong(R.raw.ambient);

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        guessInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                System.out.println("EnterKey Pressed: " + event.toString());
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                    guess();
                }
                return true;
            }
        });
        return parent;
    }

    @Override
    public void onClick(View v) {
        if(v == guessbtn) {
            guess();
        }
        if(v == exitbtn) {
            exit();
        }
    }

    void newgame() {

        galgelogik.nulstil();

        String word = galgelogik.getSynligtOrd();
        wordText.setText(word);

        levelView.setImageResource(R.drawable.level0);

        guessText.setText("");

        //Displays the word to guess in the "characters used" section.
        if(testing) {
            word = galgelogik.getOrdet();
            guessText.setText(word);
        }
    }

    void guess() {
        String input;
        input = guessInput.getText().toString().toLowerCase();
        if(input.length() > 1) {
            if(input.equals(galgelogik.getOrdet())) {
                galgelogik.setSynligtOrd(input);
                wordText.setText(input);
            }
        }
        if(input.length() == 1) {
            galgelogik.gætBogstav(input);
            if(galgelogik.erSidsteBogstavKorrekt()) {
                wordText.setText(galgelogik.getSynligtOrd());
            } else {
                switch (galgelogik.getAntalForkerteBogstaver()) {
                    case 1:
                        levelView.setImageResource(R.drawable.level1);
                        break;
                    case 2:
                        levelView.setImageResource(R.drawable.level2);
                        break;
                    case 3:
                        levelView.setImageResource(R.drawable.level3);
                        break;
                    case 4:
                        levelView.setImageResource(R.drawable.level4);
                        break;
                    case 5:
                        levelView.setImageResource(R.drawable.level5);
                        break;
                    case 6:
                        levelView.setImageResource(R.drawable.level6);
                        break;
                }
            }
            StringBuilder temp = new StringBuilder();
            for (int i = 0 ; i < galgelogik.getBrugteBogstaver().size() ; i++) {
                temp.append(galgelogik.getBrugteBogstaver().get(i));
            }
            guessText.setText(temp);
            //Displays the word to guess in the "characters used" section.
            if(testing) {
                guessText.setText(galgelogik.getOrdet());
            }
        }
        guessInput.setText("");
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        if(galgelogik.erSpilletSlut()) {
            gameover();
        }
    }

    void gameover() {
        if(galgelogik.erSpilletVundet()) {
            highscores.addScore(galgelogik.getOrdet(), galgelogik.getBrugteBogstaver().size());
            won();
        }
        if(galgelogik.erSpilletTabt()) {
            lost();
        }
    }

    void won() {
        PlaySound.getInstance(getContext()).playSong(R.raw.cheering);
        WonFragment wonFragment = new WonFragment();
        Bundle args = new Bundle();
        args.putInt("guess",galgelogik.getBrugteBogstaver().size());
        args.putInt("score",highscores.getLastscore());
        wonFragment.setArguments(args);
        String tag = wonFragment.getClass().getSimpleName();
        getFragmentManager().beginTransaction()
                .replace(R.id.overlaycontainer, wonFragment, tag)
                .addToBackStack(tag)
                .commit();
        newgame();
    }

    void lost() {

        Fragment lostFragment = new LostFragment();
        Bundle args = new Bundle();
        args.putCharSequence("word",galgelogik.getOrdet());
        lostFragment.setArguments(args);
        String tag = lostFragment.getClass().getSimpleName();
        getFragmentManager().beginTransaction()
                .replace(R.id.overlaycontainer, lostFragment, tag)
                .addToBackStack(tag)
                .commit();
        newgame();
    }

    public void exit() {
        Fragment exitFragment = new ExitFragment();
        String tag = exitFragment.getClass().getSimpleName();
        getFragmentManager().beginTransaction()
                .replace(R.id.overlaycontainer, exitFragment, tag)
                .addToBackStack(tag)
                .commit();
    }
}
