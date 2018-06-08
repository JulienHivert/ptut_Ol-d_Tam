package com.example.iem.oldtam.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.manager.DataManager;
import com.example.iem.oldtam.manager.MqttManager;
import com.example.iem.oldtam.view.adapter.PollRecyclerAdapter;

public class AdminPollFragment extends Fragment {
    Context context;

    RecyclerView recyclerView;
    private Button proposeVote;
    private MqttManager mqttManager;
    private DataManager dataManager;

    public AdminPollFragment() {
        mqttManager = MqttManager.getInstance(getActivity());
        dataManager = DataManager.getInstance();
    }

    public static AdminPollFragment newInstance() {
        AdminPollFragment fragment = new AdminPollFragment();
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
        View view = inflater.inflate(R.layout.fragment_admin_poll, container, false);

        setRecyclerView(view);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        this.proposeVote = (Button) view.findViewById(R.id.admin_poll_button);
        proposeVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttManager.sendChansons(dataManager.getListData());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        if (getActivity() != null) {
            getActivity().setTitle("Proposer un vote");
        }

    }

    private void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.admin_poll_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PollRecyclerAdapter());
    }
}
