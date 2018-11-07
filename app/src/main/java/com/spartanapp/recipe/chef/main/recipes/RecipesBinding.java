package com.spartanapp.recipe.chef.main.recipes;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.spartanapp.recipe.chef.RecipeApp;
import com.spartanapp.recipe.chef.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecipesBinding {

    private static final String TAG = RecipesBinding.class.getSimpleName();

    /*********************
     * call image throw Glide
     *********************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:loadImage"})
    public static void setImage(ImageView view, long img) {

        Glide.with(view.getContext())
                .load(Uri.parse("file:///android_asset/recipe_image/" + img + ".jpg"))
                .into(view);
    }

    /*************************
     * add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:recipesItem"})
    public static void setRecipesItem(RecyclerView recyclerView, List recipeItems) {

        if (recipeItems != null && recipeItems.size() > 0) {
            ((RecipesAdapter) recyclerView.getAdapter()).setItems(recipeItems);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:frame"})
    public static void setFramelayout(FrameLayout frameLayout, boolean loke) {

        if (loke) {
            frameLayout.setVisibility(View.GONE);
        }else{
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

}
