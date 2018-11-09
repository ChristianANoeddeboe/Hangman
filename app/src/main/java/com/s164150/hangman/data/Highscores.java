package com.s164150.hangman.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Highscores {

    private ArrayList<Player> playerscores = new ArrayList<>();
    private static Highscores highscores;
    private SharedPreferences prefs;
    private Context ctx;
    private int lastscore;

    private Highscores(Context ctx) {
        this.ctx = ctx;
        readScore();
        //testData();
    }

    public static Highscores getInstance(Context ctx) {
        if(highscores == null) {
            highscores = new Highscores(ctx);
        }
        return highscores;
    }

    public void addScore(String word, int guesses) {
        removeinfo();
        int score = calculateScore(word,guesses);
        lastscore = score;
        playerscores.add(new Player(word, score));
        Collections.sort(playerscores);
        saveScore();
    }

    private void removeinfo() {
        Player last = playerscores.get(playerscores.size()-1);
        if(last.getScore() == 0) {
            playerscores.remove(last);
        }
    }

    public void testData() {
        String[] testWords = {"bil","computer","programmering","motorvej","busrute","gangsti","skovsnegl","solsort","seksten","sytten"};
        Player test;
        for(int i = 0 ; i < 10 ; i++) {
            test = new Player(testWords[i],calculateScore(testWords[i], (int) (Math.random()*10)));
            playerscores.add(test);
        }
        Collections.sort(playerscores);
    }

    public int calculateScore(String word, int guesses) {
        int score = 100;
        score -= guesses;
        score *= word.length();
        return score;
    }

    private void saveScore() {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        //TreeSet<String> word = new TreeSet<>();
        //TreeSet<String> score = new TreeSet<>();

        String words = "";
        String scores = "";

        int size = playerscores.size();

        for(int i = 0 ; i < size-1 ; i++) {
            words += playerscores.get(i).getWord()+",";
            scores += playerscores.get(i).getScore()+",";
            //word.add(player.getWord());
            //score.add(String.valueOf(player.getScore()));
        }
        words += playerscores.get(size-1).getWord();
        scores += playerscores.get(size-1).getScore();

        //prefs.edit().putStringSet("WORD",word).apply();
        //prefs.edit().putStringSet("SCORE",score).apply();
        prefs.edit().putString("WORD",words).apply();
        prefs.edit().putString("SCORE",scores).apply();
    }

    private void readScore() {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        String wordstemp = prefs.getString("WORD", "Play to add scores!");
        String scorestemp = prefs.getString("SCORE","0");
        //Set<String> tempword = prefs.getStringSet("WORD",new TreeSet<String>());
        //Set<String> tempscore = prefs.getStringSet("SCORE",new TreeSet<String>());
        //TreeSet<String> word = new TreeSet<>(tempword);
        //TreeSet<String> score = new TreeSet<>(tempscore);
        String[] words;
        String[] scores;

        words = wordstemp.split(",");
        scores = scorestemp.split(",");

        /*
        if(wordstemp.contains(",")) {
            words = wordstemp.split(",");
            size = words.length;
        } else {
            words = new String[]{wordstemp};
        }
        if(scorestemp.contains(",")) {
            scores = scorestemp.split(",");
        } else {
            scores = new String[]{scorestemp};
        }*/

        /*for(int i = 0 ; i < word.size() ; i++) {
            playerscores.add(new Player(word.iterator().next(), Integer.parseInt(score.iterator().next())));
        }*/

        for(int i = 0 ; i < words.length ; i++) {
            playerscores.add(new Player(words[i],Integer.parseInt(scores[i])));
        }
    }

    public ArrayList<Player> getPlayerscores() {
        return playerscores;
    }

    public int getLastscore() {
        return lastscore;
    }
}
