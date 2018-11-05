package com.spartanapp.recipe.chef.main.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.databinding.SubSearchBinding;

public class SubSearchViewHolder extends RecyclerView.ViewHolder {

    private SubSearchBinding mBinding;

    public SubSearchViewHolder(@NonNull SubSearchBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setup(SubRecipe subRecipe) {
        mBinding.setRecipes(subRecipe);
    }

}
