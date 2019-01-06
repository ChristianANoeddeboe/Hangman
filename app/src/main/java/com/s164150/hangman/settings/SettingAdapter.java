package com.s164150.hangman.settings;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.s164150.hangman.R;
import com.s164150.hangman.data.Word;

import java.util.ArrayList;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {

    private ArrayList<Word> wordDataSet;
    private TextView word;
    private Button used;

    public static class SettingViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout view;
        SettingViewHolder(LinearLayout v) {
            super(v);
            view = v;
        }
    }

    SettingAdapter(ArrayList<Word> dataSet) {
        wordDataSet = dataSet;
    }

    @Override
    public SettingAdapter.SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_list_element, parent, false);

        return new SettingAdapter.SettingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SettingAdapter.SettingViewHolder holder, int position) {
        word = holder.view.findViewById(R.id.wordlistword);
        used = holder.view.findViewById(R.id.usedBtn);

        String data = wordDataSet.get(position).getWord();
        word.setText(data);

        data = String.valueOf(wordDataSet.get(position).getUsed());
        if(data.equals("0")) {
            used.setText(R.string.enable);
            int color = ContextCompat.getColor(holder.view.getContext(), R.color.lightgray);
            word.setTextColor(color);
        } else {
            used.setText(R.string.disable);
        }
    }

    @Override
    public int getItemCount() {
        return wordDataSet.size();
    }

}
