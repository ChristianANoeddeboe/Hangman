package com.s164150.hangman.game.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.s164150.hangman.Galgelogik;
import com.s164150.hangman.R;
import com.s164150.hangman.data.Highscores;

public class GameFragment extends Fragment implements View.OnClickListener {

    ImageButton exitbtn, guessbtn;
    ImageView levelView;
    TextView wordText, guessText;
    EditText guessInput;
    Galgelogik galgelogik;
    InputMethodManager imm;
    Button nobackbtn, yesbackbtn;
    //Button yesagainbtn, noagainbtn;

    //Dialog backdialog, gameoverdialog;
    Dialog backdialog;



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

        galgelogik = new Galgelogik();

        highscores = Highscores.getInstance(getActivity());

        newgame();

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        guessInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            //TODO Make GBoard keyboard work with 'enter' button
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

    public void initwords() {
        class AsyncTaskNetwork extends AsyncTask {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    galgelogik.hentOrdFraDr();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new AsyncTaskNetwork().execute();
    }

    @Override
    public void onClick(View v) {
        if(v == guessbtn) {
            guess();
        }
        if(v == exitbtn) {
            exit();
        }
        if(v == yesbackbtn) {
            getActivity().finish();
        }
        /*
        if(v == nobackbtn) {
            backdialog.dismiss();
        }
        if(v == yesbackbtn || v == noagainbtn) {
            getActivity().finish();
        }
        if(v == yesagainbtn) {
            gameoverdialog.dismiss();
            newgame();
        }
        */
    }

    void newgame() {
        //Fetches new words.
        galgelogik.nulstil();
        initwords();

        //Hides new word
        String word = galgelogik.getSynligtOrd();
        wordText.setText(word);

        //Resets picture.
        levelView.setImageResource(R.drawable.level0);

        //Resets used letters.
        guessText.setText("");
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
        WonFragment wonFragment = new WonFragment();
        Bundle args = new Bundle();
        args.putInt("guess",galgelogik.getBrugteBogstaver().size());
        args.putInt("score",highscores.getLastscore());
        wonFragment.setArguments(args);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.overlaycontainer, wonFragment)
                .addToBackStack(null)
                .commit();
        newgame();
    }

    void lost() {

        Fragment lostFragment = new LostFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.overlaycontainer, lostFragment)
                .addToBackStack(null)
                .commit();
        newgame();
    }

    /*
    void lost() {
        //TODO change to new activity.
        gameoverdialog = new Dialog(getActivity());
        gameoverdialog.setContentView(R.layout.fragment_lost);

        noagainbtn = gameoverdialog.findViewById(R.id.lostno);
        yesagainbtn = gameoverdialog.findViewById(R.id.lostyes);

        noagainbtn.setOnClickListener(this);
        yesagainbtn.setOnClickListener(this);

        //Makes dialog background transparent instead of default white color.
        gameoverdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        gameoverdialog.show();
    }
    */

    void exit() {
        backdialog = new Dialog(getActivity());
        backdialog.setContentView(R.layout.fragment_exit_dialog);

        nobackbtn = backdialog.findViewById(R.id.nobackbtn);
        yesbackbtn = backdialog.findViewById(R.id.yesbackbtn);

        nobackbtn.setOnClickListener(this);
        yesbackbtn.setOnClickListener(this);

        //Makes dialog background transparent instead of default white color.
        backdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        backdialog.show();

        /*Test to programmably set the layout according to screen dimensions.
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout(width, height);*/
    }
}
