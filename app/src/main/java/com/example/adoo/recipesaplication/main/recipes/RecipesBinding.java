package com.example.adoo.recipesaplication.main.recipes;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.main.description.DescriptionAdapter;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.List;

public class RecipesBinding {

    private static final String TAG = RecipesBinding.class.getSimpleName();

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:loadImage"})
    public static void setImage(ImageView view, String img){

        Glide.with(view.getContext())
                .load(img)
                .into(view);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:recipesItem"})
    public static void setRecipesItem(RecyclerView recyclerView, List recipeItems){

        if(recipeItems!=null && recipeItems.size() > 0){
            ((RecipesAdapter)recyclerView.getAdapter()).setItems(recipeItems);
        }
    }


}
