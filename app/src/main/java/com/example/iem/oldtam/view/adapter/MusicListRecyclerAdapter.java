package com.example.iem.oldtam.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.manager.DataManager;
import com.example.iem.oldtam.tools.CustomItemClickListener;

public class MusicListRecyclerAdapter extends RecyclerView.Adapter<MusicListRecyclerAdapter.MyHolder> {

    private CustomItemClickListener listener;
    private DataManager dataManager;
//    private Context context;

//    public Context getContext() {
//        return context;
//    }

    public MusicListRecyclerAdapter(CustomItemClickListener listener) {
        this.listener = listener;
        this.dataManager = DataManager.getInstance();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_music_list, viewGroup, false);

        final MyHolder holder = new MyHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getLayoutPosition());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.titleTextView.setText(dataManager.getListData().get(position).getTitre());
        holder.artistTextView.setText(dataManager.getListData().get(position).getArtiste());
    }

    @Override
    public int getItemCount() {
        return dataManager.getListData().size();
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