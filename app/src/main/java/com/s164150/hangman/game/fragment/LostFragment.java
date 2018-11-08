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
    ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_lost, container, false);
        this.container = container;

        noagainbtn = parent.findViewById(R.id.lostno);
        yesagainbtn = parent.findViewById(R.id.lostyes);

        int color = getResources().getColor(R.color.overlayBackground);
        container.setBackgroundColor(color);

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
            container.setBackgroundColor(getResources().getColor(R.color.transparent));
            getFragmentManager().popBackStack();
        }
    }
}