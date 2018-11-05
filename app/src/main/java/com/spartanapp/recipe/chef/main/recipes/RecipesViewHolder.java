package com.spartanapp.recipe.chef.main.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.databinding.RecipesItemBinding;
import com.spartanapp.recipe.chef.util.RecyclerViewClickListener;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    public static final String TAG = RecipesViewHolder.class.getSimpleName();

    private final RecipesItemBinding mBinding;

    public RecipesViewHolder(@NonNull RecipesItemBinding binding,
                             RecyclerViewClickListener listener) {
        super(binding.getRoot());

        mBinding = binding;
        mBinding.setListener(listener);
    }

    public void setup(Recipe recipe) {
        mBinding.setRecipes(recipe);
    }
}