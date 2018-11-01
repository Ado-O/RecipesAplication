package com.example.adoo.recipesaplication.main.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.SubRecipe;
import com.example.adoo.recipesaplication.databinding.SubRecipeBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

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
