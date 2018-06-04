package com.example.iem.oldtam.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.iem.oldtam.R;

public class PollRecyclerAdapter extends RecyclerView.Adapter<PollRecyclerAdapter.MyHolder> {

    public PollRecyclerAdapter() {

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_poll, viewGroup, false);

        final MyHolder holder = new MyHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.voteCheckBox.isChecked()) {
                    holder.voteCheckBox.setChecked(false);
                }
                else {
                    holder.voteCheckBox.setChecked(true);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        //TODO Remplacer les textes par les informations de l'objet
        holder.titleTextView.setText("Titre de la musique");
        holder.artistTextView.setText("Nom artistes");
        holder.voteCheckBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        //TODO Retourner la size de la liste
        return 3;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView artistTextView;
        CheckBox voteCheckBox;

        MyHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recycler_poll_title);
            artistTextView = itemView.findViewById(R.id.recycler_poll_artist);
            voteCheckBox = itemView.findViewById(R.id.recycler_poll_check);
        }
    }
}