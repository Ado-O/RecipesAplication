package com.example.adoo.recipesaplication.main.subscribe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.billingclient.api.Purchase;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.databinding.SubscribeEndFragBinding;
import com.example.adoo.recipesaplication.util.billing.BillingManager;

import java.util.List;

public class SubscribeEndFragment extends Fragment {

    public static final String TAG = SubscribeEndFragment.class.getSimpleName();

    private SubscribeEndFragBinding mBinding;

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


        mBinding.btnAdd.setOnClickListener(v -> {


        });

        return mBinding.getRoot();

    }
}
