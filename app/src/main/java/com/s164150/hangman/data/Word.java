package com.s164150.hangman.data;

public class Word implements Comparable {

    private String word;
    private int used;

    Word(String word, int used) {
        this.word = word;
        this.used = used;
    }

    public String getWord() {
        return word;
    }

    public int getUsed() {
        return used;
    }

    public void switchUsed() {
        if(used == 1) {
            used = 0;
        } else {
            used = 1;
        }
    }

    @Override
    public int compareTo(Object o) {
        Word other = (Word) o;
        return word.compareTo(other.getWord());
    }
}
