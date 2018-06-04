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
import com.example.iem.oldtam.view.adapter.MusicListRecyclerAdapter;

public class MusicListFragment extends Fragment {
    Context context;

    RecyclerView recyclerView;

    public MusicListFragment() {

    }

    public static MusicListFragment newInstance() {
        MusicListFragment fragment = new MusicListFragment();
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
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);

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

    private void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.list_recycler);
        recyclerView.setNestedScrollingEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new MusicListRecyclerAdapter());
        recyclerView.requestFocus();
    }
}
