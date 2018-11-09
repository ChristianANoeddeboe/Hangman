package com.s164150.hangman.game.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;

import com.s164150.hangman.R;

public class ExitDialogFragment extends DialogFragment {

    Button nobackbtn, yesbackbtn;
    AlertDialog a;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_exit_dialog, null);

        nobackbtn = v.findViewById(R.id.nobackbtn);
        yesbackbtn = v.findViewById(R.id.yesbackbtn);

        builder.setView(v);
        a = builder.create();

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
}
