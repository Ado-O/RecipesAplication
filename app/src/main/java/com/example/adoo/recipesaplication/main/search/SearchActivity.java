package com.example.adoo.recipesaplication.main.search;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;

import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.databinding.SearchActBinding;
import com.example.adoo.recipesaplication.main.MainActivity;
import com.example.adoo.recipesaplication.main.MainAdapter;
import com.example.adoo.recipesaplication.main.description.DescriptionActivity;
import com.example.adoo.recipesaplication.main.recipes.RecipesFragment;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.other.ThreeFragment;
import com.example.adoo.recipesaplication.util.ActivityUtils;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchActBinding mSearchActBinding;
    private SearchViewModel mSearchViewModel;

    public static void startActivity(Activity activity, int id) {
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_act);

        mSearchActBinding = DataBindingUtil.setContentView(this, R.layout.search_act);
        mSearchViewModel = ViewModelFactory.obtainViewModel(this, SearchViewModel.class);


        setupFragment();
        setupEvent();
    }

    /**
     * Fragment
     */
    private void setupFragment() {

        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(mSearchActBinding.fragSearch.getId());
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance(getIntent().getIntExtra("id", 0));
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), searchFragment, R.id.frag_search
            );
        }
    }

    /**
     * add clickListener
     */
    public void setupEvent() {

        mSearchViewModel.getOpenRecipeEvent().observe(SearchActivity.this, recipe ->
                DescriptionActivity.startActivity(SearchActivity.this, recipe)
        );
    }

}