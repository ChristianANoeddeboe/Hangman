package com.example.hangman.game;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hangman.Galgelogik;
import com.example.hangman.R;
import com.example.hangman.data.Highscores;
import com.example.hangman.data.Player;

import java.util.TreeSet;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton exitbtn, guessbtn;
    ImageView levelView;
    TextView wordText, guessText;
    EditText guessInput;
    Galgelogik galgelogik;
    InputMethodManager imm;
    Button nobackbtn, yesbackbtn, yesagainbtn, noagainbtn;

    Dialog backdialog, gameoverdialog;

    Highscores highscores;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        exitbtn = findViewById(R.id.exitbtn);
        guessbtn = findViewById(R.id.guessbtn);

        exitbtn.setOnClickListener(this);
        guessbtn.setOnClickListener(this);

        levelView = findViewById(R.id.levelView);

        wordText = findViewById(R.id.wordText);
        guessText = findViewById(R.id.guessText);

        guessInput = findViewById(R.id.guessInput);

        highscores = Highscores.getInstance(this);

        galgelogik = new Galgelogik();

        newgame();

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

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
        if(v == nobackbtn) {
            backdialog.dismiss();
        }
        if(v == yesbackbtn || v == noagainbtn) {
            finish();
        }
        if(v == yesagainbtn) {
            gameoverdialog.dismiss();
            newgame();
        }
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
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        if(galgelogik.erSpilletSlut()) {
            gameover();
        }
    }

    void gameover() {
        if(galgelogik.erSpilletVundet()) {
            won();
        }
        if(galgelogik.erSpilletTabt()) {
            lost();
        }
        highscores.addScore(galgelogik.getOrdet(), galgelogik.getBrugteBogstaver().size());
    }



    void won() {
        gameoverdialog = new Dialog(this);
        gameoverdialog.setContentView(R.layout.fragment_won);

        noagainbtn = gameoverdialog.findViewById(R.id.noagainbtn);
        yesagainbtn = gameoverdialog.findViewById(R.id.yesagainbtn);

        noagainbtn.setOnClickListener(this);
        yesagainbtn.setOnClickListener(this);

        //Makes dialog background transparent instead of default white color.
        gameoverdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        gameoverdialog.show();
    }

    void lost() {
        gameoverdialog = new Dialog(this);
        gameoverdialog.setContentView(R.layout.fragment_lost);

        noagainbtn = gameoverdialog.findViewById(R.id.lostno);
        yesagainbtn = gameoverdialog.findViewById(R.id.lostyes);

        noagainbtn.setOnClickListener(this);
        yesagainbtn.setOnClickListener(this);

        //Makes dialog background transparent instead of default white color.
        gameoverdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        gameoverdialog.show();
    }

    void exit() {
        backdialog = new Dialog(this);
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
