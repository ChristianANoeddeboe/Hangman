package com.s164150.hangman.game.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.s164150.hangman.PlaySound;
import com.s164150.hangman.R;

public class LostFragment extends Fragment implements View.OnClickListener {

    Button noagainbtn, yesagainbtn;
    ViewGroup container;
    TextView wordwas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_lost, container, false);
        this.container = container;

        noagainbtn = parent.findViewById(R.id.lostno);
        yesagainbtn = parent.findViewById(R.id.lostyes);

        wordwas = parent.findViewById(R.id.wordwas);

        int color = getResources().getColor(R.color.overlayBackground);
        container.setBackgroundColor(color);

        String word = (String) getArguments().getCharSequence("word");
        word = word.toUpperCase();
        wordwas.setText(word);

        noagainbtn.setOnClickListener(this);
        yesagainbtn.setOnClickListener(this);

        PlaySound.getInstance(getContext()).playSong(R.raw.booing);

        return parent;
    }

    @Override
    public void onClick(View v) {
        if(v == noagainbtn) {
            getActivity().finish();
            PlaySound.getInstance(getContext()).playSong(R.raw.ambient);
        }
        if(v == yesagainbtn) {
            container.setBackgroundColor(getResources().getColor(R.color.transparent));
            getFragmentManager().popBackStack();
            PlaySound.getInstance(getContext()).playSong(R.raw.ambient);
        }
    }
}