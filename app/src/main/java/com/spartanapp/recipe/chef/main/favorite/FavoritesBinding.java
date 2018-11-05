package com.spartanapp.recipe.chef.main.favorite;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.RecipeApp;

import java.util.ArrayList;
import java.util.List;

public class FavoritesBinding {

    private static final String TAG = FavoritesBinding.class.getSimpleName();

    /*************************
     *add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:favoritesItem"})
    public static void setFavoritesItem(RecyclerView recyclerView, List favoriteItem) {

        List<Object> itemList = new ArrayList<>();
        if (RecipeApp.IS_SUB){
            itemList.add("Unlock all Recipe!");
            itemList.addAll(favoriteItem);
        }else {
            itemList.addAll(favoriteItem);
        }

            if (itemList != null) {
            ((FavoritesAdapter) recyclerView.getAdapter()).setItems(itemList);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:rvRecipe", "app:rvView"})
    public static void setError(RecyclerView recyclerView, List favoriteItem, View view) {

        if (favoriteItem.size() == 0){
            recyclerView.setVisibility(view.GONE);
        }else{
            recyclerView.setVisibility(view.VISIBLE);
        }

    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:favoritesImage"})
    public static void setFavoritesImage(ImageView imageView, List favoriteItem) {

        if (favoriteItem.size() == 0) {
            imageView.setImageResource(R.drawable.ic_like_hart_clik);
        } else {
            imageView.setImageResource(0);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:favoritesDes"})
    public static void setFavoritesDes(TextView textView, List favoriteItem) {

        if (favoriteItem.size() == 0) {
            textView.setText("ADD YOUR FAVORITE RECIPES");
        } else {
            textView.setText(null);
        }
    }


}
