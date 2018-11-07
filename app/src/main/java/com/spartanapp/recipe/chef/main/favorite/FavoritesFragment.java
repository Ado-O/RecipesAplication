package com.spartanapp.recipe.chef.main.favorite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.databinding.FavoritesFragBinding;
import com.spartanapp.recipe.chef.main.MainActivity;
import com.spartanapp.recipe.chef.util.RecyclerViewClickListener;
import com.spartanapp.recipe.chef.util.ViewModelFactory;

public class FavoritesFragment extends Fragment implements RecyclerViewClickListener {

    private static final String TAG = FavoritesFragment.class.getSimpleName();

    private FavoritesFragBinding mBinding;
    private FavoritesViewModel mFavoritesViewModel;
    private FavoritesAdapter mFavoritesAdapter;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FavoritesFragBinding.inflate(inflater, container, false);

        mFavoritesViewModel = ViewModelFactory.obtainViewModel(getActivity(), FavoritesViewModel.class);
        mFavoritesViewModel.start();
        mBinding.setViewModel(mFavoritesViewModel);

        //lock subs
        SharedPreferences sub = getActivity().getSharedPreferences("is_sub", 0);
        boolean isSub = sub.getBoolean("free", false);

        if (!isSub){
            mBinding.setLockBoolean(true);
        }else{
            mBinding.setLockBoolean(false);
        }

        setupRv();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFavoritesViewModel.getFavorite();
    }

    /*************************
     * add layoutManger in RecycleViewAdapter
     **********************/
    public void setupRv() {

        mFavoritesAdapter = new FavoritesAdapter(getActivity(), FavoritesFragment.this);
        mBinding.rvFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvFavorites.setAdapter(mFavoritesAdapter);

    }

    /************************
     * RecycleViewClickListener
     ***********************/
    @Override
    public void recyclerViewListClicked(View v, Recipe recipe) {

        //send listener data in DescriptionLayout
        mFavoritesViewModel.getOpenRecipeEvent().setValue(recipe);
    }

    @Override
    public void favoritesCLickListener(View v, Recipe recipe) {

        //if size recipes 0 = close recycleView
        if (mFavoritesViewModel.mRecipes.size() == 1) {
            mBinding.rvFavorites.setVisibility(View.GONE);
        }

        //send listener data in favorite_table db for delete that row
        mFavoritesViewModel.deleteFavorite(recipe.getId());

        //get everting from favorite_table db
        mFavoritesViewModel.getFavorite();

        //snackbar - UNDO
        Snackbar snackbar = Snackbar
                .make(v.getRootView(), "Removed from Favorite", Snackbar.LENGTH_SHORT)
                .setAction("UNDO", view -> {

                    mBinding.rvFavorites.setVisibility(View.VISIBLE);
                    mFavoritesViewModel.addFavorite(recipe.getId());
                    mFavoritesViewModel.getFavorite();

                    Snackbar snackbar1 = Snackbar.make(view.getRootView(), "Recipe is restored!", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                });

        snackbar.show();
    }


}