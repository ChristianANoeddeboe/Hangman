package com.s164150.hangman.highscore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.s164150.hangman.R;
import com.s164150.hangman.data.Player;

import java.util.ArrayList;

public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder> {

    private ArrayList<Player> highscoreDataSet;
    private TextView word, score;

    public static class HighscoreViewHolder extends  RecyclerView.ViewHolder {
        public LinearLayout view;
        HighscoreViewHolder(LinearLayout v) {
            super(v);
            view = v;
        }
    }

    HighscoreAdapter(ArrayList<Player> dataSet) {
        highscoreDataSet = dataSet;
    }

    @Override
    public HighscoreAdapter.HighscoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_list_element, parent, false);

        return new HighscoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HighscoreViewHolder holder, int position) {
        word = holder.view.findViewById(R.id.highscoreword);
        score = holder.view.findViewById(R.id.highscorescore);

        String data = highscoreDataSet.get(position).getWord();
        word.setText(data);

        data = String.valueOf(highscoreDataSet.get(position).getScore());
        if(!data.equals("0")) {
            score.setText(data);
        }
    }

    @Override
    public int getItemCount() {
        return highscoreDataSet.size();
    }
}
