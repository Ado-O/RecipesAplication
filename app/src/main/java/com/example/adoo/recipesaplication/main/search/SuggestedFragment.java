package com.example.adoo.recipesaplication.main.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.databinding.SuggestedFragBinding;
import com.example.adoo.recipesaplication.main.MainActivity;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

public class SuggestedFragment extends Fragment {

    private SuggestedFragBinding mBinding;
    private SearchViewModel mSearchViewModel;

    public static SuggestedFragment newInstance() {
        return new SuggestedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SuggestedFragBinding.inflate(inflater, container, false);

        mSearchViewModel = ViewModelFactory.obtainViewModel(getActivity(), SearchViewModel.class);

        mSearchViewModel.start();
        mBinding.setViewHolder(mSearchViewModel);

        setupData();
        setupToolbarOnClick();
        return mBinding.getRoot();
    }

    public void setupData() {

        SuggestedAdapter suggestedAdapter = new SuggestedAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3) == 0 ? 2 : 1;
            }
        });
        mBinding.rvSearch.setLayoutManager(gridLayoutManager);
        mBinding.rvSearch.setAdapter(suggestedAdapter);

    }

    public void setupToolbarOnClick() {
        mBinding.ivSearch.setOnClickListener(v ->
                SearchActivity.startActivity(getActivity(), 0));
    }
}
