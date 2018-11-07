package com.spartanapp.recipe.chef.main.subscribe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spartanapp.recipe.chef.RecipeApp;
import com.spartanapp.recipe.chef.databinding.SubscribeEndFragBinding;

public class SubscribeEndFragment extends Fragment {

    public static final String TAG = SubscribeEndFragment.class.getSimpleName();

    private SubscribeEndFragBinding mBinding;

    public static SubscribeEndFragment newInstance() {
        return new SubscribeEndFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SubscribeEndFragBinding.inflate(inflater, container, false);

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getActivity());
        String name = preferences.getString("Price", "");

        mBinding.setItem(name);

        return mBinding.getRoot();

    }
}
