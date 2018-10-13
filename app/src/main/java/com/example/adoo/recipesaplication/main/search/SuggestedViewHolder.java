package com.example.adoo.recipesaplication.main.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.SuggestedItemBinding;

public class SuggestedViewHolder extends RecyclerView.ViewHolder {

    private SuggestedItemBinding mBinding;

    public SuggestedViewHolder(@NonNull SuggestedItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setup(Recipe recipe) {
        mBinding.setRecipe(recipe);
    }
}
