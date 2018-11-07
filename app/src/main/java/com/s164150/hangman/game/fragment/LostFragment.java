package com.s164150.hangman.game.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.s164150.hangman.R;

public class LostFragment extends Fragment implements View.OnClickListener {

    Button noagainbtn, yesagainbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_lost, container, false);

        noagainbtn = parent.findViewById(R.id.lostno);
        yesagainbtn = parent.findViewById(R.id.lostyes);

        noagainbtn.setOnClickListener(this);
        yesagainbtn.setOnClickListener(this);

        return parent;
    }

    @Override
    public void onClick(View v) {
        if(v == noagainbtn) {
            getActivity().finish();
        }
        if(v == yesagainbtn) {
            getFragmentManager().popBackStack();
        }
    }
}