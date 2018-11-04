package com.example.hangman.highscore.data;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Highscores {

    ArrayList<Player> playerscores = new ArrayList<>();

    public Highscores() {
        testData();
    }

    public void addScore(String word, int guesses) {
        int score = calculateScore(word,guesses);
        Player[] temp = new Player[playerscores.size()];
        playerscores.add(new Player(word, score));
        Collections.sort(playerscores);
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

    public ArrayList<Player> getPlayerscores() {
        return playerscores;
    }

    public void writeHighscore() {
        //File scorelist = new File("Hangman")
    }


    public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody){
        File file = new File(mcoContext.getFilesDir(),"mydir");
        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
