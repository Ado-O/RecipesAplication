package com.example.adoo.recipesaplication.main.favorite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.FavoritesItemBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {

    private FavoritesItemBinding mBinding;

    public FavoritesViewHolder(@NonNull FavoritesItemBinding binding,
                               RecyclerViewClickListener recyclerViewClickListener) {
        super(binding.getRoot());

        mBinding = binding;
        mBinding.setListener(recyclerViewClickListener);
    }

    public void setup(Recipe recipe) {
        mBinding.setRecipes(recipe);
    }

}
