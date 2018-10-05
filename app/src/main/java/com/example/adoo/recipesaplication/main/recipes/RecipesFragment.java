package com.example.adoo.recipesaplication.main.recipes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.RecipesFragBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

public class RecipesFragment extends Fragment implements RecyclerViewClickListener{

    private static final String TAG = RecipesFragment.class.getSimpleName();

    private RecipesFragBinding mRecipesFragBinding;
    private RecipesAdapter mRecipesAdapter;
    private RecipesViewModel mRecipesViewModel;
    private long filterTag = 0;

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecipesFragBinding = RecipesFragBinding.inflate(inflater, container, false);

        mRecipesViewModel = ViewModelFactory.obtainViewModel(getActivity(), RecipesViewModel.class);
        mRecipesViewModel.start(filterTag);
        mRecipesFragBinding.setRecipesViewModel(mRecipesViewModel);


        setupRv();

        return mRecipesFragBinding.getRoot();
    }

    public void setupRv() {


        mRecipesAdapter = new RecipesAdapter(
                getActivity(), RecipesFragment.this);

        mRecipesFragBinding.rvRecipes.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        mRecipesFragBinding.rvRecipes.smoothScrollToPosition(0);
        //TODO scrolling on bot
        mRecipesFragBinding.rvRecipes.setAdapter(mRecipesAdapter);
    }

    @Override
    public void recyclerViewListClicked(View v, Recipe recipe) {
        mRecipesViewModel.getOpenRecipeEvent().setValue(recipe);
    }
}