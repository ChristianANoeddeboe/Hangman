package com.s164150.hangman.game.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;

import com.s164150.hangman.R;

public class ExitFragment extends Fragment implements View.OnClickListener {

    Button nobackbtn, yesbackbtn;
    ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_exit_dialog, container, false);
        this.container = container;

        nobackbtn = parent.findViewById(R.id.nobackbtn);
        yesbackbtn = parent.findViewById(R.id.yesbackbtn);

        int color = getResources().getColor(R.color.overlayBackground);
        container.setBackgroundColor(color);

        nobackbtn.setOnClickListener(this);
        yesbackbtn.setOnClickListener(this);

        return parent;
    }

    @Override
    public void onClick(View v) {
        if(v == nobackbtn) {
            container.setBackgroundColor(getResources().getColor(R.color.transparent));
            getFragmentManager().popBackStack();
        }
        if(v == yesbackbtn) {
            getActivity().finish();
        }
    }
}
