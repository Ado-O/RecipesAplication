package com.spartanapp.recipe.chef.main.subscribe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spartanapp.recipe.chef.databinding.SubscribeDesFragBinding;

public class SubscribeDesFragment extends Fragment {

    public static final String TAG = SubscribeDesFragment.class.getSimpleName();

    private SubscribeDesFragBinding mBinding;

    public static SubscribeDesFragment newInstance() {
        return new SubscribeDesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SubscribeDesFragBinding.inflate(inflater, container, false);


        return mBinding.getRoot();
    }
}


