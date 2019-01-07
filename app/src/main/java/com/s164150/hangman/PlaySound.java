package com.s164150.hangman;

import android.content.Context;
import android.media.MediaPlayer;

public class PlaySound {

    private static PlaySound playSound;
    private MediaPlayer mp;
    private Context ctx;

    PlaySound(Context ctx) {
        this.ctx = ctx;
    }

    public static PlaySound getInstance(Context ctx) {
        if(playSound == null) {
            playSound = new PlaySound(ctx);
        }
        return playSound;
    }

    public void playSong(int song) {
        stopSong();
        mp = MediaPlayer.create(ctx, song);
        mp.start();
    }

    public void stopSong() {
        if(mp != null) {
            mp.stop();
        }
    }
}
