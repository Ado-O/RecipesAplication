package com.example.adoo.recipesaplication.main;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.adoo.recipesaplication.Injection;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Tag;
import com.example.adoo.recipesaplication.other.ThreeFragment;
import com.example.adoo.recipesaplication.other.TwoFragment;
import com.example.adoo.recipesaplication.main.recipes.RecipesFragment;

import java.util.ArrayList;

import com.example.adoo.recipesaplication.databinding.MainActBinding;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.util.ActivityUtils;
import com.example.adoo.recipesaplication.util.FilterClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

public class MainActivity extends AppCompatActivity implements FilterClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainAdapter mAdapter;
    private MainActBinding mMainActBinding;
    private RecipesViewModel mRecipesViewModel;
    private long id= 0;
    private long filterTag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        //add content
        Injection.provideContentRepository(this).setContent();

        mMainActBinding = DataBindingUtil.setContentView(this, R.layout.main_act);
        mRecipesViewModel = ViewModelFactory.obtainViewModel(this, RecipesViewModel.class);

        mRecipesViewModel.startTag();
        mMainActBinding.setRecipesViewModel(mRecipesViewModel);
        mMainActBinding.setListener(this);

        //setup
        setupToolbar();
        setupPager();
        setupBottomNavigationView();
        setupEvent();
    }

    /**
     * Setting up the toolbar, toolbar actions & title
     */
    private void setupToolbar() {
        setSupportActionBar(mMainActBinding.tbMain);
        getSupportActionBar().setTitle("Recipes");
        mMainActBinding.tbMain.setTitleTextColor(Color.BLACK);
    }

    /**
     * Setting up the listView & its adapter
     */
    private void setupPager() {

        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(RecipesFragment.newInstance());
        arrayList.add(TwoFragment.newInstance());
        arrayList.add(ThreeFragment.newInstance());

        mAdapter = new MainAdapter(getSupportFragmentManager(), arrayList);
        mMainActBinding.vpMain.setAdapter(mAdapter);

        mMainActBinding.vpMain.setPagingEnabled(false);

    }

    /**
     * bottomNavigationView
     */
    private void setupBottomNavigationView() {
        mMainActBinding.bottomNavigation.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_recipes:
                            item.setChecked(true);
                            mMainActBinding.vpMain.setCurrentItem(0, false);
                            break;
                        case R.id.action_search:
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

    /**
     * add clickListener
     */
    public void setupEvent() {

        mRecipesViewModel.getOpenRecipeEvent().observe(MainActivity.this, recipe ->
                Toast.makeText(this, recipe.getName(), Toast.LENGTH_SHORT).show()
        );
    }

    public void getTag(Tag tag) {

        if (id != tag.getId()) {
            mRecipesViewModel.getFilterItem(tag.getId());
            id = tag.getId();
        } else{
            id = 0;
            mRecipesViewModel.getRecipes();
        }
    }

    @Override
    public void onClick(Tag tag) {
        getTag(tag);
    }
}

