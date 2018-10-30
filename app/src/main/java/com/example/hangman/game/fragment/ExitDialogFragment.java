package com.example.hangman.game.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;

import com.example.hangman.R;

public class ExitDialogFragment extends DialogFragment {

    //TODO Make the dimensions of the dialog box fit. Ask next time.

    Button nobackbtn, yesbackbtn;
    AlertDialog a;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_exit_dialog, null));
        a = builder.create();

        nobackbtn = a.findViewById(R.id.nobackbtn);
        yesbackbtn = a.findViewById(R.id.yesbackbtn);

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

        return a;
    }

    public void prepareBtns() {

    }
}
