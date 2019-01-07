package com.s164150.hangman.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Words {

    private ArrayList<Word> wordList = new ArrayList<>();
    private ArrayList<String> existingWords = new ArrayList<>();
    private static Words words;
    private SharedPreferences prefs;
    private Context ctx;

    private Words(Context ctx) {
        this.ctx = ctx;
        readWords();
    }

    public static Words getInstance(Context ctx) {
        if(words == null) {
            words= new Words(ctx);
        }
        return words;
    }

    public void addWord(String word) {
        if(existingWords.contains(word)) {
            return;
        }
        existingWords.add(word);
        wordList.add(new Word(word,1));
    }

    public void commitWords() {
        Collections.sort(wordList);
        saveWords();
    }

    private void saveWords() {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);

        String words = "";
        String used = "";

        int size = wordList.size();

        for(int i = 0 ; i < size-1 ; i++) {
            words += wordList.get(i).getWord()+",";
            used += wordList.get(i).getUsed()+",";
        }
        words += wordList.get(size-1).getWord();
        used += wordList.get(size-1).getUsed();

        prefs.edit().putString("WORD",words).apply();
        prefs.edit().putString("USED",used).apply();
    }

    private void readWords() {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        String wordstemp = prefs.getString("WORD", "Play to add scores!");
        String usedtemp = prefs.getString("USED","-1");
        String[] words;
        String[] uses;

        words = wordstemp.split(",");
        uses = usedtemp.split(",");

        for(int i = 0 ; i < words.length - 1 ; i++) {
            wordList.add(new Word(words[i],Integer.parseInt(uses[i])));
        }
    }

    public ArrayList<Word> getWordList() {
        return wordList;
    }
}
