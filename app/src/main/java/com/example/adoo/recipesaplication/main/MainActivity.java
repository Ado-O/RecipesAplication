package com.example.adoo.recipesaplication.main;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.adoo.recipesaplication.Injection;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.databinding.RecipesItemBinding;
import com.example.adoo.recipesaplication.main.favorite.FavoritesViewModel;
import com.example.adoo.recipesaplication.main.description.DescriptionActivity;
import com.example.adoo.recipesaplication.main.favorite.FavoritesFragment;
import com.example.adoo.recipesaplication.main.search.SearchFragment;
import com.example.adoo.recipesaplication.main.search.SearchViewModel;
import com.example.adoo.recipesaplication.main.recipes.RecipesFragment;

import java.util.ArrayList;

import com.example.adoo.recipesaplication.databinding.MainActBinding;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.main.subscribe.SubscribeActivity;
import com.example.adoo.recipesaplication.main.subscribe.SubscribeEndFragment;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainAdapter mAdapter;
    private MainActBinding mMainActBinding;
    private RecipesViewModel mRecipesViewModel;
    private FavoritesViewModel mFavoritesViewModel;
    private SearchViewModel mSearchViewModel;
    // if is lock true else false
    public static final boolean IS_SUB = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        //add content
        Injection.provideContentRepository(this).setContent();

        mMainActBinding = DataBindingUtil.setContentView(this, R.layout.main_act);
        mRecipesViewModel = ViewModelFactory.obtainViewModel(this, RecipesViewModel.class);
        mFavoritesViewModel = ViewModelFactory.obtainViewModel(this, FavoritesViewModel.class);
        mSearchViewModel = ViewModelFactory.obtainViewModel(this, SearchViewModel.class);

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
        arrayList.add(SearchFragment.newInstance());
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
                            hideKeyboard(MainActivity.this);
                            break;
                        case R.id.action_suggested:
                            item.setChecked(true);
                            mMainActBinding.vpMain.setCurrentItem(1, false);
                            break;
                        case R.id.action_favorite:
                            item.setChecked(true);
                            mMainActBinding.vpMain.setCurrentItem(2, false);
                            hideKeyboard(MainActivity.this);
                            break;
                    }
                    return false;
                });
    }

    /**
     * forse to hide keyboard
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
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

        mSearchViewModel.getOpenRecipeEvent().observe(MainActivity.this, recipe ->
                DescriptionActivity.startActivity(MainActivity.this, recipe)
        );

    }

    public void SubClick(View v) {
        SubscribeActivity.startActivity(this, true);
    }

    public void LearnClick(View v) {
        SubscribeActivity.startActivity(this, false);
    }
}



