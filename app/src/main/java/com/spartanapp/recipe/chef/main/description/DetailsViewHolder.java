package com.spartanapp.recipe.chef.main.description;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.databinding.DescriptionDetailsItemBinding;

public class DetailsViewHolder extends RecyclerView.ViewHolder {

    private DescriptionDetailsItemBinding mBinding;

    public DetailsViewHolder(@NonNull DescriptionDetailsItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setup(Recipe recipe) {
       mBinding.setRecipe(recipe);
    }
}
