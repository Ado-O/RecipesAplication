package com.example.adoo.recipesaplication.main.favorite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.FavoritesFragBinding;
import com.example.adoo.recipesaplication.main.MainActivity;
import com.example.adoo.recipesaplication.main.description.DescriptionActivity;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

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


        setupRv();
        return mBinding.getRoot();
    }

    public void setupRv() {

        mFavoritesAdapter = new FavoritesAdapter(getActivity(), FavoritesFragment.this);
        mBinding.rvFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvFavorites.setAdapter(mFavoritesAdapter);

    }


    @Override
    public void recyclerViewListClicked(View v, Recipe recipe) {
        mFavoritesViewModel.getOpenRecipeEvent().setValue(recipe);
    }

    @Override
    public void favoritesCLickListener(View imageView, Recipe recipe) {
        if (mFavoritesViewModel.mRecipes.size() == 0){
            mBinding.rvFavorites.setVisibility(View.GONE);
        }
        mFavoritesViewModel.deleteFavorite(recipe.getId());
        mFavoritesViewModel.getFavorite();
    }

}