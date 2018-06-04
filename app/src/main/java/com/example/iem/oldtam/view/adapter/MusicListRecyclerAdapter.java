package com.example.iem.oldtam.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iem.oldtam.R;

public class MusicListRecyclerAdapter extends RecyclerView.Adapter<MusicListRecyclerAdapter.MyHolder> {

    public MusicListRecyclerAdapter() {

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_music_list, viewGroup, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        //TODO Remplacer les textes par les informations de l'objet
        holder.titleTextView.setText("Titre de la musique");
        holder.artistTextView.setText("Nom artistes");
    }

    @Override
    public int getItemCount() {
        //TODO Retourner la size de la liste
        return 2;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView artistTextView;

        MyHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recycler_music_list_title);
            artistTextView = itemView.findViewById(R.id.recycler_music_list_artist);
        }
    }
}