package com.example.iem.oldtam.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.iem.oldtam.R;

public class MenuPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public MenuPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment();
            case 1:
                return new Fragment();
            case 2:
                return new Fragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
