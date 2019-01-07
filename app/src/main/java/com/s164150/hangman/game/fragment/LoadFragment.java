package com.s164150.hangman.game.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.s164150.hangman.Galgelogik;
import com.s164150.hangman.GlideApp;
import com.s164150.hangman.R;
import com.s164150.hangman.data.Word;
import com.s164150.hangman.data.Words;

import java.util.ArrayList;

public class LoadFragment extends Fragment {

    ImageView loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_load, container, false);

        loader = parent.findViewById(R.id.loaderimage);

        GlideApp.with(this).load(R.drawable.loaderanimated).into(loader);

        class AsyncTaskNetwork extends AsyncTask {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {

                    Words words = Words.getInstance(getActivity());
                    if(words.getWordList().size() == 0) {
                        Galgelogik.getinstance().hentOrdFraDr();
                        ArrayList<String> muligeOrd = Galgelogik.getinstance().getMuligeOrd();
                        String word;
                        for(int i = 0 ; i < muligeOrd.size() ; i++) {
                            word = muligeOrd.get(i);
                            if(!words.getWordList().contains(word)) {
                                words.addWord(word);
                            }
                        }
                        words.commitWords();
                    }
                    Galgelogik galgelogik = Galgelogik.getinstance();
                    ArrayList<Word> muligeOrd = Words.getInstance(getActivity()).getWordList();
                    ArrayList<String> newMuligeOrd = new ArrayList<>();
                    for(Word word : muligeOrd) {
                        if(word.getUsed() == 1) {
                            newMuligeOrd.add(word.getWord());
                        }
                    }
                    galgelogik.setMuligeOrd(newMuligeOrd);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Fragment gameFragment = new GameFragment();
                String tag = gameFragment.getClass().getSimpleName();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, gameFragment, tag)
                        .addToBackStack(tag)
                        .commit();
                super.onPostExecute(o);
            }
        }
        new AsyncTaskNetwork().execute();

        return parent;
    }
}