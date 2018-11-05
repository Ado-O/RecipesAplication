package com.spartanapp.recipe.chef.main.favorite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.databinding.FavoritesItemBinding;
import com.spartanapp.recipe.chef.util.RecyclerViewClickListener;

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
