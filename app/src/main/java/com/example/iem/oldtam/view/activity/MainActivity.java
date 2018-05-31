package com.example.iem.oldtam.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.adapter.MenuPagerAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewPager();
    }

    private void initializeViewPager() {

        tabLayout = findViewById(R.id.main_tabLayout);
        if (tabLayout != null) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);

            viewPager = findViewById(R.id.main_viewPager);
            viewPager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager(),this));
            viewPager.setClipToPadding(false);
            viewPager.setPageMargin(12);

            tabLayout.setupWithViewPager(viewPager);
        }

        setupTabLayoutIcon();
    }

    private void setupTabLayoutIcon() {
        int[] imageResId = {
                R.drawable.ic_mic_primary_light_24dp,
                R.drawable.ic_library_music_primary_light_24dp,
                R.drawable.ic_settings_remote_primary_light_24dp};

        for (int i = 0; i < imageResId.length; i++) {
            if (tabLayout.getTabAt(i) != null) {
                tabLayout.getTabAt(i).setIcon(imageResId[i]);
            }
        }
    }
}
