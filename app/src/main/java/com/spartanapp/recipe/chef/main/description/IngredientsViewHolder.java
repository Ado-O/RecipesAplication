package com.spartanapp.recipe.chef.main.description;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.Ingredients;
import com.spartanapp.recipe.chef.databinding.DescriptionIngredientsItemBinding;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {

    private DescriptionIngredientsItemBinding mBinding;

    public IngredientsViewHolder(@NonNull DescriptionIngredientsItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;

    }

    public void setup(Ingredients ingredients){
       mBinding.setIngredients(ingredients);
    }
}
