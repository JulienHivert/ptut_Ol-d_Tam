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
            tabLayout.setTabTextColors(getColor(R.color.colorPrimaryLight),getColor(R.color.colorSecondary));
        }

        setupTabLayoutIcon();
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        if (tab != null) {
            tab.select();
        }
    }

    private void setupTabLayoutIcon() {
        final int[] imageResId = {
                R.drawable.ic_mic_primary_light_24dp,
                R.drawable.ic_library_music_primary_light_24dp,
                R.drawable.ic_settings_remote_primary_light_24dp};

        final int[] imageSelectId = {
                R.drawable.ic_mic_secondary_24dp,
                R.drawable.ic_library_music_secondary_24dp,
                R.drawable.ic_settings_remote_secondary_24dp};

        TabLayout.Tab tab;

        for (int i = 0; i < imageResId.length; i++) {
            tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setIcon(imageResId[i]);
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(imageSelectId[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(imageResId[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
