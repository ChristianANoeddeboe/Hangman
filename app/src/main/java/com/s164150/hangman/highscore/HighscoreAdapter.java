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
        // each data item is just a string in this case
        public LinearLayout view;
        public HighscoreViewHolder(LinearLayout v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HighscoreAdapter(ArrayList<Player> dataSet) {
        highscoreDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HighscoreAdapter.HighscoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_list_element, parent, false);



        return new HighscoreViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(HighscoreViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        word = holder.view.findViewById(R.id.highscoreword);
        score = holder.view.findViewById(R.id.highscorescore);

        String data = highscoreDataSet.get(position).getWord();
        word.setText(data);

        data = String.valueOf(highscoreDataSet.get(position).getScore());
        score.setText(data);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return highscoreDataSet.size();
    }
}
