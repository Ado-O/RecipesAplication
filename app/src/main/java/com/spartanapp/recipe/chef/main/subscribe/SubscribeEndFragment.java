package com.spartanapp.recipe.chef.main.subscribe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.billingclient.api.Purchase;
import com.spartanapp.recipe.chef.data.PackageItem;
import com.spartanapp.recipe.chef.databinding.SubscribeEndFragBinding;
import com.spartanapp.recipe.chef.util.billing.BillingManager;

import java.util.List;

public class SubscribeEndFragment extends Fragment{

    public static final String TAG = SubscribeEndFragment.class.getSimpleName();

    private SubscribeEndFragBinding mBinding;
    private PackageItem packageItem;

    public static SubscribeEndFragment newInstance(int id) {

        SubscribeEndFragment fragment = new SubscribeEndFragment();
        Bundle b = new Bundle();
        b.putInt("id", id);

        fragment.setArguments(b);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SubscribeEndFragBinding.inflate(inflater, container, false);



        return mBinding.getRoot();

    }
}
