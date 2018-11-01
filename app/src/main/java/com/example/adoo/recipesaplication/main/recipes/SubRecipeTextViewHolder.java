package com.example.adoo.recipesaplication.main.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.adoo.recipesaplication.databinding.DescriptionHeaderItemBinding;
import com.example.adoo.recipesaplication.databinding.SubRecipeTextBinding;

public class SubRecipeTextViewHolder extends RecyclerView.ViewHolder {

    private SubRecipeTextBinding mBinding;

    public SubRecipeTextViewHolder(@NonNull SubRecipeTextBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setup(String text){
        mBinding.setString(text);
    }
}
