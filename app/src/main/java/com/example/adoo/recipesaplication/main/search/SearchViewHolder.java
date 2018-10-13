package com.example.adoo.recipesaplication.main.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.RecipesItemBinding;
import com.example.adoo.recipesaplication.databinding.SearchItemBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    private final SearchItemBinding mBinding;

    public SearchViewHolder(@NonNull SearchItemBinding binding,
                             RecyclerViewClickListener listener) {
        super(binding.getRoot());

        mBinding = binding;
        mBinding.setListener(listener);
    }

    public void setup(Recipe recipe) {
        mBinding.setRecipes(recipe);
    }

}