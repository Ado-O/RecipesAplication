package com.spartanapp.recipe.chef.main.subscribe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spartanapp.recipe.chef.databinding.SubscribeWeightFragBinding;

public class SubscribeWeightFragment extends Fragment {

    public static final String TAG = SubscribeWeightFragment.class.getSimpleName();

    private SubscribeWeightFragBinding mBinding;

    public static SubscribeWeightFragment newInstance() {
        return new SubscribeWeightFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SubscribeWeightFragBinding.inflate(inflater, container, false);


        return mBinding.getRoot();
    }
}
