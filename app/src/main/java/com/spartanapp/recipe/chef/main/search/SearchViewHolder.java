package com.spartanapp.recipe.chef.main.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.databinding.SearchItemBinding;
import com.spartanapp.recipe.chef.util.RecyclerViewClickListener;

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