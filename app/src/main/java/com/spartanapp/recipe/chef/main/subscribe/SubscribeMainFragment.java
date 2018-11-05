package com.spartanapp.recipe.chef.main.subscribe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spartanapp.recipe.chef.databinding.SubscribeMainFragBinding;

public class SubscribeMainFragment extends Fragment {

    public static final String TAG = SubscribeMainFragment.class.getSimpleName();

    private SubscribeMainFragBinding mBinding;

    public static SubscribeMainFragment newInstance() {
        return new SubscribeMainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SubscribeMainFragBinding.inflate(inflater, container, false);


        return mBinding.getRoot();
    }
}
