package com.example.hangman.game;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import com.example.hangman.game.fragment.ExitDialogFragment;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton exitbtn, guessbtn;
    ImageView levelView;
    TextView wordText, guessText;
    EditText guessInput;
    Galgelogik galgelogik;
    InputMethodManager imm;
    Button nobackbtn, yesbackbtn;

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

        galgelogik = new Galgelogik();

        initwords();

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
                    String word = galgelogik.getSynligtOrd();
                    wordText.setText(word);
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
    }

    void won() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_won);

        nobackbtn = dialog.findViewById(R.id.nobackbtn);
        yesbackbtn = dialog.findViewById(R.id.yesbackbtn);

        nobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yesbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        //Makes dialog background transparent instead of default white color.
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbackground);
    }

    void lost() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_lost);

        nobackbtn = dialog.findViewById(R.id.nobackbtn);
        yesbackbtn = dialog.findViewById(R.id.yesbackbtn);

        nobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yesbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        //Makes dialog background transparent instead of default white color.
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbackground);
    }

    void exit() {
        ExitDialogFragment exit = new ExitDialogFragment();
        exit.show(getSupportFragmentManager(),"Exit");

        nobackbtn = exit.getView().findViewById(R.id.nobackbtn);
        yesbackbtn = exit.getView().findViewById(R.id.yesbackbtn);

        nobackbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v){
                System.out.println("no");
            }
        });
        yesbackbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                System.out.println("Yes");
            }
        });

        /*
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_exit_dialog);

        nobackbtn = dialog.findViewById(R.id.nobackbtn);
        yesbackbtn = dialog.findViewById(R.id.yesbackbtn);

        nobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yesbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        //Makes dialog background transparent instead of default white color.
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbackground);*/

        /*Test to programmably set the layout according to screen dimensions.
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout(width, height);*/
    }



}
