package com.spartanapp.recipe.chef.main.subscribe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SubscribeFragmentAdapter extends FragmentPagerAdapter {


    private final List<Fragment> mFragment;

    public SubscribeFragmentAdapter(FragmentManager fm,List<Fragment> fragment) {
        super(fm);
        mFragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
