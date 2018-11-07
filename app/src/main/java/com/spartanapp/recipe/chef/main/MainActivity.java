package com.spartanapp.recipe.chef.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.spartanapp.recipe.chef.Injection;
import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.main.favorite.FavoritesViewModel;
import com.spartanapp.recipe.chef.main.description.DescriptionActivity;
import com.spartanapp.recipe.chef.main.favorite.FavoritesFragment;
import com.spartanapp.recipe.chef.main.policy.PolicyActivity;
import com.spartanapp.recipe.chef.main.search.SearchFragment;
import com.spartanapp.recipe.chef.main.search.SearchViewModel;
import com.spartanapp.recipe.chef.main.recipes.RecipesFragment;

import java.util.ArrayList;
import java.util.List;

import com.spartanapp.recipe.chef.databinding.MainActBinding;
import com.spartanapp.recipe.chef.main.recipes.RecipesViewModel;
import com.spartanapp.recipe.chef.main.subscribe.SubscribeActivity;
import com.spartanapp.recipe.chef.util.AppExecutors;
import com.spartanapp.recipe.chef.util.LinkUtils;
import com.spartanapp.recipe.chef.util.SimpleDialog;
import com.spartanapp.recipe.chef.util.ViewModelFactory;
import com.spartanapp.recipe.chef.util.billing.BillingManager;

import static com.spartanapp.recipe.chef.RecipeApp.SUB_1_MONTH;

public class MainActivity extends AppCompatActivity implements
        BillingManager.BillingUpdatesListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainAdapter mAdapter;
    private MainActBinding mMainActBinding;
    private RecipesViewModel mRecipesViewModel;
    private FavoritesViewModel mFavoritesViewModel;
    private SearchViewModel mSearchViewModel;
    private BillingManager mBillingManager;

    public static void startActivity(Activity activity) {

        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);

    }

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

        mBillingManager = new BillingManager(this, this);
        mBillingManager.start();

        //setup
        setupSherPreference();
        setupPager();
        setupBottomNavigationView();
        setupEvent();
        showRateUsDialog();
    }

    public void setupSherPreference() {
        //policyActivity
        SharedPreferences settings = getSharedPreferences("my_prefs", 0);
        int id = settings.getInt("id", 0);

        if (id == 0) {
            PolicyActivity.startActivity(this);
        }

        //substrice
        SharedPreferences sub = getSharedPreferences("is_sub", 0);
        boolean isSub = sub.getBoolean("free", false);

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

    /******************
     * open subscrition activity
     *****************/
    public void SubRecipe(View v) {
        SubscribeActivity.startActivity(this, true);
    }

    public void SubClick(View v) {
        SubscribeActivity.startActivity(this, true);
    }

    public void LearnClick(View v) {
        SubscribeActivity.startActivity(this, false);
    }

    /************
     * rate us
     ************/
    private void showRateUsDialog() {
        //createing
        SharedPreferences preferences = getSharedPreferences("preferences", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("incres", preferences.getInt("incres", 0) + 1);
        editor.commit();

        //adding
        SharedPreferences incresing = getSharedPreferences("preferences", 0);
        int id = incresing.getInt("incres", 0);

        //cheking if user have go to rate
        SharedPreferences dialogOut = getSharedPreferences("dialog", 0);
        boolean dlg = dialogOut.getBoolean("dialog", false);

        if (id % 3 == 0) {
            if (!dlg) {
                SimpleDialog.areYouSure(
                        this,
                        "Rate Us",
                        "Support recipe apps",
                        () -> {
                            LinkUtils.openLink(this, getString(R.string.appLink));

                            SharedPreferences dialogIn = getSharedPreferences("dialog", 0);
                            SharedPreferences.Editor editorDialog = dialogIn.edit();
                            editorDialog.putBoolean("dialog", true);
                            editorDialog.commit();
                        });
            }
        }
    }


    /******************
     * billingManager
     *****************/
    @Override
    public void onBillingClientSetupFinished() {
        mBillingManager.querySubscriptions();

    }

    @Override
    public void onPurchasesUpdated(List<Purchase> purchases) {

        if (purchases.isEmpty()) {
            SharedPreferences preferences = getSharedPreferences("is_sub", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("free", false);
            editor.commit();

        }
    }
}



