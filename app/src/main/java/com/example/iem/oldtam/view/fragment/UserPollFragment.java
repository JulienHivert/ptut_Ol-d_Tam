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
import com.example.iem.oldtam.view.adapter.PollRecyclerAdapter;

public class UserPollFragment extends Fragment {
    Context context;

    RecyclerView recyclerView;

    public UserPollFragment() {

    }

    public static UserPollFragment newInstance() {
        UserPollFragment fragment = new UserPollFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_poll, container, false);

        setRecyclerView(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        if (getActivity() != null) {
            getActivity().setTitle("Voter");
        }
    }

    private void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.poll_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new PollRecyclerAdapter());
    }
}
