package com.example.hangman.data;

public class Player implements Comparable {

    private String name;
    private String word;
    private int score;

    Player(String word, int score) {
        this.word = word;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public int getScore() {
        return score;
    }


    @Override
    public int compareTo(Object o) {
        Player other = (Player) o;
        if(score > other.getScore()) {
            return -1;
        }
        if(score == other.getScore()) {
            return 0;
        }
        return 1;
    }
}
