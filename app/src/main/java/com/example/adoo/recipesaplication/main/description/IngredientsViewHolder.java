package com.example.adoo.recipesaplication.main.description;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.databinding.DescriptionIngredientsItemBinding;

import java.util.List;

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
