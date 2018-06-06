package com.example.iem.oldtam.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.fragment.AdminMusicListFragment;
import com.example.iem.oldtam.view.fragment.AdminPollFragment;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initializeNav();
    }

    private void initializeNav() {
        BottomNavigationView navigation = findViewById(R.id.admin_nav);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.admin_nav_list:
                        showFragment(AdminMusicListFragment.newInstance());
                        return true;
                    case R.id.admin_nav_poll:
                        showFragment(AdminPollFragment.newInstance());
                        return true;
                }
                return false;
            }

        });

        navigation.setSelectedItemId(R.id.admin_nav_list);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.admin_frame, fragment)
                .commit();
    }
}
