package com.s164150.hangman.settings;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.s164150.hangman.R;
import com.s164150.hangman.data.Word;
import com.s164150.hangman.data.Words;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> implements View.OnClickListener {

    private Words words;
    private ArrayList<Word> wordDataSet;
    private TextView word;
    private Button used;
    private HashMap<Button, TextView> relationships;

    public static class SettingViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout view;
        SettingViewHolder(ConstraintLayout v) {
            super(v);
            view = v;
        }
    }

    SettingAdapter(Words dataSet) {
        words = dataSet;
        wordDataSet = words.getWordList();
        relationships = new HashMap<>();
    }

    @Override
    public SettingAdapter.SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_list_element, parent, false);

        return new SettingAdapter.SettingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SettingAdapter.SettingViewHolder holder, int position) {
        word = holder.view.findViewById(R.id.wordlistword);
        used = holder.view.findViewById(R.id.usedBtn);

        String wordData = wordDataSet.get(position).getWord();
        String usedData = String.valueOf(wordDataSet.get(position).getUsed());
        word.setText(wordData);

        if(usedData.equals("0")) {
            System.out.println(position);
            used.setText(R.string.enable);
            int color = ContextCompat.getColor(holder.view.getContext(), R.color.red);
            used.setTextColor(color);
        } else {
            int color = ContextCompat.getColor(holder.view.getContext(), R.color.white);
            used.setTextColor(color);
            used.setText(R.string.disable);
        }

        relationships.put(used,word);

        used.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button buttonclicked = (Button) v;
        TextView wordclicked = relationships.get(buttonclicked);
        Word word = null;
        for(Word curr : wordDataSet) {
            if(curr.getWord().equals(wordclicked.getText())) {
                word = curr;
                break;
            }
        }

        if(word == null) {
            return;
        }

        if(buttonclicked.getText().equals("DISABLE")) {
            buttonclicked.setText(R.string.enable);
            int color = ContextCompat.getColor(v.getContext(), R.color.red);
            buttonclicked.setTextColor(color);
            word.switchUsed();
        } else if(buttonclicked.getText().equals("ENABLE")) {
            buttonclicked.setText(R.string.disable);
            int color = ContextCompat.getColor(v.getContext(), R.color.white);
            buttonclicked.setTextColor(color);
            word.switchUsed();
        }
        words.commitWords();
    }

    @Override
    public int getItemCount() {
        return wordDataSet.size();
    }

}
