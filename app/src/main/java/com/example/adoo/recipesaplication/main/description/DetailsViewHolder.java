package com.example.adoo.recipesaplication.main.description;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.DescriptionDetailsItemBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

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
