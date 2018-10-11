package com.example.adoo.recipesaplication.main.description;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.DescriptionFragBinding;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFragment extends Fragment {

    private static final String TAG = DescriptionFragment.class.getSimpleName();

    private DescriptionFragBinding mBinding;
    private Recipe r;

    public static DescriptionFragment newInstance(Recipe recipe) {

        DescriptionFragment fragment = new DescriptionFragment();
        Bundle b = new Bundle();
        b.putParcelable("recipe", recipe);

        fragment.setArguments(b);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DescriptionFragBinding.inflate(inflater, container, false);

        r = getActivity().getIntent().getExtras().getParcelable("recipe");

        setupData();

        return mBinding.getRoot();
    }

    /**
     * add data in recycleview
     */
    public void setupData() {

        List itemList = new ArrayList<>();
        itemList.add(r);
        itemList.add("INGREDIENTS");
        itemList.addAll(r.getIngredients());
        itemList.add("DIRECTIONS");
        itemList.addAll(r.getDirections());

        mBinding.setItems(itemList);

        DescriptionAdapter adapter = new DescriptionAdapter(getActivity());
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvList.setAdapter(adapter);
    }
}
