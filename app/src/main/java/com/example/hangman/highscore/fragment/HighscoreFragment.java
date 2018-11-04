package com.example.hangman.highscore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hangman.R;
import com.example.hangman.highscore.data.Highscores;

import java.util.ArrayList;

/**
 * Unused fragment.
 * TODO Remove this
 */

public class HighscoreFragment extends Fragment {

    RecyclerView recyclerView;
    Highscores highscores = new Highscores();

    public HighscoreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_highscore, container, false);
        // Vi laver en arrayliste så vi kan fjerne/indsætte elementer
        recyclerView = v.findViewById(R.id.highscorelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        // Inflate the layout for this fragment
        return v;
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter<ListeelemViewholder>() {
        @Override
        public int getItemCount()  {
            return highscores.getPlayerscores().size();
        }

        @Override
        public ListeelemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.highscore_list_element, parent, false);
            ListeelemViewholder vh = new ListeelemViewholder(view);
            vh.highscorelistitem = view.findViewById(R.id.highscorelistitem);

            return vh;
        }

        @Override
        public void onBindViewHolder(ListeelemViewholder vh, int position) {
            TextView word = vh.word;
            word.setText(highscores.getPlayerscores().get(position).getWord());
            TextView score = vh.score;
            score.setText(highscores.getPlayerscores().get(position).getScore());
        }
    };

    class ListeelemViewholder extends RecyclerView.ViewHolder {
        LinearLayout highscorelistitem;
        TextView word;
        TextView score;
        public ListeelemViewholder(View itemView) {
            super(itemView);
        }
    }

    // Læs mere på https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.fjo359jbr
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP,ItemTouchHelper.DOWN) { // swipeDirs

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder vh, int actionState) {
            super.onSelectedChanged(vh, actionState);
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                vh.itemView.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.6f);
            }
        }

        @Override
        public boolean onMove(RecyclerView rv, RecyclerView.ViewHolder vh, RecyclerView.ViewHolder target) {
            return false; // false hvis rykket ikke skal foretages
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder vh) {
            super.clearView(recyclerView, vh);
            vh.itemView.animate().scaleX(1).scaleY(1).alpha(1);
        }

    };
}
