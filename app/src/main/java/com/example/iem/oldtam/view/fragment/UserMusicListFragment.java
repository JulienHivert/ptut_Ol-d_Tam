package com.example.iem.oldtam.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.tools.CustomItemClickListener;
import com.example.iem.oldtam.view.adapter.MusicListRecyclerAdapter;

public class UserMusicListFragment extends Fragment {
    Context context;

    RecyclerView recyclerView;

    public UserMusicListFragment() {

    }

    public static UserMusicListFragment newInstance() {
        UserMusicListFragment fragment = new UserMusicListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_music_list, container, false);

        setRecyclerView(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        if (getActivity() != null) {
            getActivity().setTitle("Liste des musiques");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (recyclerView != null) {
            setNewRecyclerAdapter();
        }
    }

    private void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.list_recycler);
        recyclerView.setNestedScrollingEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        setNewRecyclerAdapter();
        recyclerView.requestFocus();
    }

    private void setNewRecyclerAdapter() {
        recyclerView.setAdapter(new MusicListRecyclerAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        }));
    }
}
