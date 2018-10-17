package com.example.adoo.recipesaplication.main;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adoo.recipesaplication.Injection;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.main.favorite.FavoritesViewModel;
import com.example.adoo.recipesaplication.main.description.DescriptionActivity;
import com.example.adoo.recipesaplication.main.favorite.FavoritesFragment;
import com.example.adoo.recipesaplication.main.search.SearchViewModel;
import com.example.adoo.recipesaplication.main.search.SuggestedFragment;
import com.example.adoo.recipesaplication.main.recipes.RecipesFragment;

import java.util.ArrayList;

import com.example.adoo.recipesaplication.databinding.MainActBinding;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainAdapter mAdapter;
    private MainActBinding mMainActBinding;
    private RecipesViewModel mRecipesViewModel;
    private FavoritesViewModel mFavoritesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        //add content
        Injection.provideContentRepository(this).setContent();

        mMainActBinding = DataBindingUtil.setContentView(this, R.layout.main_act);
        mRecipesViewModel = ViewModelFactory.obtainViewModel(this, RecipesViewModel.class);
        mFavoritesViewModel = ViewModelFactory.obtainViewModel(this, FavoritesViewModel.class);

        //setup
        setupPager();
        setupBottomNavigationView();
        setupEvent();
    }


    /***************************
     * Setting up the listView & its adapter
     **************************/
    private void setupPager() {

        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(RecipesFragment.newInstance());
        arrayList.add(SuggestedFragment.newInstance());
        arrayList.add(FavoritesFragment.newInstance());

        mAdapter = new MainAdapter(getSupportFragmentManager(), arrayList);
        mMainActBinding.vpMain.setAdapter(mAdapter);

        mMainActBinding.vpMain.setPagingEnabled(false);
    }

    /***********************
     * bottomNavigationView
     **********************/
    private void setupBottomNavigationView() {
        mMainActBinding.bottomNavigation.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_recipes:
                            item.setChecked(true);
                            mMainActBinding.vpMain.setCurrentItem(0, false);
                            break;
                        case R.id.action_suggested:
                            item.setChecked(true);
                            mMainActBinding.vpMain.setCurrentItem(1, false);
                            break;
                        case R.id.action_favorite:
                            item.setChecked(true);
                            mMainActBinding.vpMain.setCurrentItem(2, false);
                            break;
                    }
                    return false;
                });
    }

    /*************************
     * add clickListener open DescriptionActivity
     **********************/
    public void setupEvent() {

         //recipes
        mRecipesViewModel.getOpenRecipeEvent().observe(MainActivity.this, recipe ->
                DescriptionActivity.startActivity(MainActivity.this, recipe)
        );

         //favorite
        mFavoritesViewModel.getOpenRecipeEvent().observe(MainActivity.this, recipe ->
                DescriptionActivity.startActivity(MainActivity.this, recipe)
        );

    }

}

