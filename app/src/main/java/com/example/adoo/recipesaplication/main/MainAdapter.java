package com.example.adoo.recipesaplication.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragment;

    //constructor
    public MainAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        mFragment = fragment;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
