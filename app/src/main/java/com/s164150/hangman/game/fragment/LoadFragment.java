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
                    Galgelogik.getinstance().hentOrdFraDr();
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
                        .addToBackStack(tag)
                        .replace(R.id.fragcontainer, gameFragment, "GameFragment")  // tom container i layout
                        .commit();
                super.onPostExecute(o);
            }
        }
        new AsyncTaskNetwork().execute();

        return parent;
    }
}