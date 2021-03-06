package com.example.iem.oldtam.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.manager.DataManager;
import com.example.iem.oldtam.manager.MqttManager;
import com.example.iem.oldtam.tools.CustomItemClickListener;
import com.example.iem.oldtam.view.activity.AdminAddMusicActivity;
import com.example.iem.oldtam.view.activity.AdminMusicDetailActivity;
import com.example.iem.oldtam.view.adapter.MusicListRecyclerAdapter;

public class AdminMusicListFragment extends Fragment {
    Context context;

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    MusicListRecyclerAdapter musicListRecyclerAdapter;
    DataManager dataManager;
    MqttManager mqttManager;

    public AdminMusicListFragment() {
        mqttManager = MqttManager.getInstance(getActivity());
        dataManager = DataManager.getInstance();
    }

    public static AdminMusicListFragment newInstance() {
        AdminMusicListFragment fragment = new AdminMusicListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        musicListRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_music_list, container, false);

        setRecyclerView(view);
        initAddButton(view);
        init();
        return view;
    }

    private void initAddButton(View view) {
        floatingActionButton = view.findViewById(R.id.admin_list_add_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminAddMusicActivity.class);

                startActivity(intent);
            }
        });
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
        recyclerView = view.findViewById(R.id.admin_list_recycler);
        recyclerView.setNestedScrollingEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        musicListRecyclerAdapter = new MusicListRecyclerAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(context, AdminMusicDetailActivity.class);
                intent.putExtra("id", dataManager.getListData().get(position).getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(musicListRecyclerAdapter);
        recyclerView.requestFocus();
    }

    public void init(){
        mqttManager.sendInit(dataManager.getListData());
    }
}
