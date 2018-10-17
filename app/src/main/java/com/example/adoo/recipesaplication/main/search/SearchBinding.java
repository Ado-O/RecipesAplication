package com.example.adoo.recipesaplication.main.search;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.adoo.recipesaplication.R;

import java.util.List;

public class SearchBinding {

    private static final String TAG = SearchBinding.class.getSimpleName();

    /*************************
     * add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:suggestedItem"})
    public static void setSuggestedItem(RecyclerView recyclerView, List suggested) {

        if (suggested != null && suggested.size() > 0) {
            ((SuggestedAdapter) recyclerView.getAdapter()).setItems(suggested);
        }
    }

    /*************************
     * add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:searchItem"})
    public static void setSearchItem(RecyclerView recyclerView, List searchItem) {

        if (searchItem != null && searchItem.size() > 0) {
            ((SearchAdapter) recyclerView.getAdapter()).setItems(searchItem);
        }
    }

}
