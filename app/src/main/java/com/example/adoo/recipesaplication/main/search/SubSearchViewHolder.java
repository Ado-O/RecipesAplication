package com.example.adoo.recipesaplication.main.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adoo.recipesaplication.data.SubRecipe;
import com.example.adoo.recipesaplication.databinding.SubRecipeBinding;
import com.example.adoo.recipesaplication.databinding.SubSearchBinding;

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
