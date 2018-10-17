package com.example.adoo.recipesaplication.main.favorite;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class FavoritesBinding {

    private static final String TAG = FavoritesBinding.class.getSimpleName();

    /*************************
     *add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:favoritesItem"})
    public static void setFavoritesItem(RecyclerView recyclerView, List favoriteItem) {

        if (favoriteItem != null && favoriteItem.size() > 0) {
            ((FavoritesAdapter) recyclerView.getAdapter()).items(favoriteItem);
        }
    }


}
