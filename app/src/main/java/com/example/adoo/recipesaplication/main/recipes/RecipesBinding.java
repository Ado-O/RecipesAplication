package com.example.adoo.recipesaplication.main.recipes;

import android.content.res.AssetManager;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.generated.callback.OnClickListener;
import com.example.adoo.recipesaplication.main.description.DescriptionAdapter;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.io.IOException;
import java.io.InputStream;
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

//    @SuppressWarnings("unchecked")
//    @BindingAdapter({"app:lottieAnim"})
//    public static void setAnim(LottieAnimationView lottieAnimationView, RecipesViewModel recipe) {
//        Log.e(TAG, String.valueOf(recipe.mBooleans));
//
////        if (recipe.mBooleans == 0) {
////        }
//
//
//    }
}
