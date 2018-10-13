package com.example.adoo.recipesaplication.main.recipes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.Tag;
import com.example.adoo.recipesaplication.databinding.RecipesFragBinding;
import com.example.adoo.recipesaplication.util.FilterClickListener;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

public class RecipesFragment extends Fragment implements RecyclerViewClickListener,FilterClickListener {

    private static final String TAG = RecipesFragment.class.getSimpleName();

    private RecipesFragBinding mRecipesFragBinding;
    private RecipesAdapter mRecipesAdapter;
    private RecipesViewModel mRecipesViewModel;
    private long filterTag = 0;
    private long id = 0;


    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecipesFragBinding = RecipesFragBinding.inflate(inflater, container, false);

        //add data for recipe
        mRecipesViewModel = ViewModelFactory.obtainViewModel(getActivity(), RecipesViewModel.class);
        mRecipesViewModel.start(filterTag);
        mRecipesFragBinding.setRecipesViewModel(mRecipesViewModel);

        //add data for tag
        mRecipesViewModel.startTag();
        mRecipesFragBinding.setRecipesViewModel(mRecipesViewModel);
        mRecipesFragBinding.setListener(this);

        setupToolbar();
        setupRv();

        return mRecipesFragBinding.getRoot();
    }

    /**
     * toolbar
     */
    public void setupToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mRecipesFragBinding.tbMain);
        activity.getSupportActionBar().setTitle("Recipe");
    }

    public void setupRv() {


        mRecipesAdapter = new RecipesAdapter(
                getActivity(), RecipesFragment.this);

        mRecipesFragBinding.rvRecipes.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        mRecipesFragBinding.rvRecipes.smoothScrollToPosition(0);
        mRecipesFragBinding.rvRecipes.setAdapter(mRecipesAdapter);
    }

    /**
     * logic when refresh filter_tag with on click
     */
    public void getTag(Tag tag) {

        if (id != tag.getId()) {
            mRecipesViewModel.getFilterItem(tag.getId());
            id = tag.getId();
        } else {
            mRecipesViewModel.getRecipes();
            id = 0;
        }
    }

    @Override
    public void recyclerViewListClicked(View v, Recipe recipe) {
        mRecipesViewModel.getOpenRecipeEvent().setValue(recipe);
    }

    @Override
    public void onClick(Tag tag) {
            getTag(tag);
    }
}