package com.spartanapp.recipe.chef.main.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.databinding.SubRecipeBinding;

public class SubRecipeViewHolder extends RecyclerView.ViewHolder {

    private SubRecipeBinding mBinding;

    public SubRecipeViewHolder(@NonNull SubRecipeBinding binding) {
        super(binding.getRoot());

        mBinding = binding;
    }

    public void setup(SubRecipe subRecipe) {
        mBinding.setSubRecipe(subRecipe);
    }

}
