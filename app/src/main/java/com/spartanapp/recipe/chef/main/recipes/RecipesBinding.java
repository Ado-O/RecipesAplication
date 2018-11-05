package com.spartanapp.recipe.chef.main.recipes;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.spartanapp.recipe.chef.RecipeApp;

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
    @BindingAdapter({"app:recipesItem", "app:subRecipes"})
    public static void setRecipesItem(RecyclerView recyclerView, List recipeItems, List subRecipe) {

        List<Object> itemList = new ArrayList<>();
        if (RecipeApp.IS_SUB){
            itemList.addAll(recipeItems);
            itemList.addAll(subRecipe);
        }else{
            itemList.addAll(recipeItems);
        }

        if (itemList != null && itemList.size() > 0) {
            ((RecipesAdapter) recyclerView.getAdapter()).setItems(itemList);
        }
    }

//    @SuppressWarnings("unchecked")
//    @BindingAdapter({"app:lottie"})
//    public static void setAnima(LottieAnimationView lottieAnimationView, boolean like) {
//
//        Log.e(TAG, "lottie; "+RecipesFragment.IS_ANIM);
//        Log.e(TAG, "like"+ like);
//
//        if (RecipesFragment.IS_ANIM) {
//            //animation
//            lottieAnimationView.setAnimation("anim/heart-animation.json");
//            lottieAnimationView.playAnimation();
//        } else {
//            lottieAnimationView.cancelAnimation();
//        }
//    }

}
