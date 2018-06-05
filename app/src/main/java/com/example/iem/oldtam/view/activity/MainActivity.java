package com.example.iem.oldtam.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.Model.Chanson;
import com.example.iem.oldtam.view.fragment.InProgressFragment;
import com.example.iem.oldtam.view.fragment.MusicListFragment;
import com.example.iem.oldtam.view.fragment.PollFragment;
import com.example.iem.oldtam.view.manager.MqttManager;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MqttManager mqttManager = new MqttManager(getApplicationContext());
        try {
            mqttManager.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        Chanson chanson = new Chanson("456", "Yolo", "Yolo", "Yolo", "Yolo");
        initializeNav();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_admin){
            //TODO Lancer le mode admin
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeNav() {
        BottomNavigationView navigation = findViewById(R.id.menu_nav);

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_progress:
                        showFragment(InProgressFragment.newInstance());
                        return true;
                    case R.id.navigation_list:
                        showFragment(MusicListFragment.newInstance());
                        return true;
                    case R.id.navigation_poll:
                        showFragment(PollFragment.newInstance());
                        return true;
                }
                return false;
            }

        };

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_list);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.menu_frame, fragment)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
