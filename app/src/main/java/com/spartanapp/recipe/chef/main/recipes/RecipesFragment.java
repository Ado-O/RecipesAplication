package com.spartanapp.recipe.chef.main.recipes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.Tag;
import com.spartanapp.recipe.chef.databinding.RecipesFragBinding;
import com.spartanapp.recipe.chef.main.favorite.FavoritesViewModel;
import com.spartanapp.recipe.chef.util.FilterClickListener;
import com.spartanapp.recipe.chef.util.RecyclerViewClickListener;
import com.spartanapp.recipe.chef.util.ViewModelFactory;

public class RecipesFragment extends Fragment implements RecyclerViewClickListener,
        FilterClickListener {

    private static final String TAG = RecipesFragment.class.getSimpleName();

    private RecipesFragBinding mRecipesFragBinding;
    private RecipesAdapter mRecipesAdapter;
    private RecipesViewModel mRecipesViewModel;
    private FavoritesViewModel mFavoritesViewModel;
    private long filterTag = 0;
    private long id = 0;

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecipesFragBinding = RecipesFragBinding.inflate(inflater, container, false);

        mFavoritesViewModel = ViewModelFactory.obtainViewModel(getActivity(), FavoritesViewModel.class);

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

    @Override
    public void onResume() {
        super.onResume();
        mRecipesViewModel.getRecipes();
           mRecipesFragBinding.lottieAnimationView.cancelAnimation();
    }

    /**********
     * toolbar
     *********/
    public void setupToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mRecipesFragBinding.tbMain);
    }

    /*************************
     * add layoutManger in RecycleViewAdapter
     **********************/
    public void setupRv() {

        mRecipesAdapter = new RecipesAdapter(getActivity(), RecipesFragment.this);

        mRecipesFragBinding.rvRecipes.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        mRecipesFragBinding.rvRecipes.setAdapter(mRecipesAdapter);
    }

    /***************************
     * logic when refresh recipes_tag
     *    with onClick
     **************************/
    public void getTag(Tag tag) {

        if (id != tag.getId()) {
            mRecipesViewModel.getFilterItem(tag.getId());
            id = tag.getId();
        } else {
            mRecipesViewModel.getRecipes();
            id = 0;
        }
    }

    /************************
     * RecycleViewClickListener
     ***********************/
    @Override
    public void recyclerViewListClicked(View v, Recipe recipe) {

        //send listener data in DescriptionLayout
        mRecipesViewModel.getOpenRecipeEvent().setValue(recipe);


    }

    @Override
    public void favoritesCLickListener(View v, Recipe recipe) {

        // if Like from recipes_table false = add icon, set Like = true and in favorite_table data od id
        if (!recipe.isLike()) {
            v.setBackgroundResource(R.drawable.ic_like_hart_clik);
            recipe.setLike(true);
            mFavoritesViewModel.addFavorite(recipe.getId());
            //animation
            mRecipesFragBinding.lottieAnimationView.setAnimation("anim/heart-animation.json");
            mRecipesFragBinding.lottieAnimationView.playAnimation();
        } else {
            v.setBackgroundResource(R.drawable.ic_like_hart);
            recipe.setLike(false);
            mFavoritesViewModel.deleteFavorite(recipe.getId());

        }

    }

    @Override
    public void onClick(Tag tag) {

        //send tag when is he click
        getTag(tag);
    }

}