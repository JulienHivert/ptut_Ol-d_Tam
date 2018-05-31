package com.example.iem.oldtam.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.iem.oldtam.R;
import com.example.iem.oldtam.view.fragment.InProgressFragment;
import com.example.iem.oldtam.view.fragment.MusicListFragment;
import com.example.iem.oldtam.view.fragment.PollFragment;

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
                return InProgressFragment.newInstance();
            case 1:
                return MusicListFragment.newInstance();
            case 2:
                return PollFragment.newInstance();
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
        switch (position) {
            case 0:
                return mContext.getString(R.string.menu_encours);
            case 1:
                return mContext.getString(R.string.menu_liste);
            case 2:
                return mContext.getString(R.string.menu_votes);
            default:
                return null;
        }
    }

}
