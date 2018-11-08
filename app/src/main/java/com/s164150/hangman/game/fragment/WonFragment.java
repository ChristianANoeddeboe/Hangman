package com.s164150.hangman.game.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.s164150.hangman.R;

public class WonFragment extends Fragment implements View.OnClickListener {

    TextView numscore, numguess;
    Button noagainbtn, yesagainbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_won, container, false);

        noagainbtn = parent.findViewById(R.id.wonno);
        yesagainbtn = parent.findViewById(R.id.wonyes);

        numscore = parent.findViewById(R.id.numscore);
        numguess = parent.findViewById(R.id.numguess);

        int temp = getArguments().getInt("score",-1);
        numscore.setText(""+temp);
        temp = getArguments().getInt("guess",-1);
        numguess.setText(""+temp);

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