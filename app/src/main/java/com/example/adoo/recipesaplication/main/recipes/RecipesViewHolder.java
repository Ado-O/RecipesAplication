package com.example.adoo.recipesaplication.main.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.RecipesItemBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

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