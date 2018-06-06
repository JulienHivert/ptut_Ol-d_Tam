package com.example.iem.oldtam.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.fragment.UserInProgressFragment;
import com.example.iem.oldtam.view.fragment.UserMusicListFragment;
import com.example.iem.oldtam.view.fragment.UserPollFragment;

public class UserActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initializeNav();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_admin){
            new AlertDialog.Builder(this)
                    .setMessage("Voulez-vous vous connecter en tant qu'administrateur ?")
                    .setCancelable(true)
                    .setPositiveButton("Se connecter",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getBaseContext(), AdminActivity.class);

                            //TODO Gestion de la connexion sécurisée
                            startActivity(intent);
                        }
                    })

                    .setNegativeButton("Annuler",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeNav() {
        BottomNavigationView navigation = findViewById(R.id.user_nav);

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.user_nav_progress:
                        showFragment(UserInProgressFragment.newInstance());
                        return true;
                    case R.id.user_nav_list:
                        showFragment(UserMusicListFragment.newInstance());
                        return true;
                    case R.id.user_nav_poll:
                        showFragment(UserPollFragment.newInstance());
                        return true;
                }
                return false;
            }

        };

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.user_nav_list);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.user_frame, fragment)
                .commit();
    }
}
