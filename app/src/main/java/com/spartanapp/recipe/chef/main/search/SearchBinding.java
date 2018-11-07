package com.spartanapp.recipe.chef.main.search;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.RecipeApp;
import com.spartanapp.recipe.chef.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchBinding {

    private static final String TAG = SearchBinding.class.getSimpleName();

    /*************************
     * add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:searchItem", "app:lockBooleanSearch"})
    public static void setSearchItem(RecyclerView recyclerView, List searchItem, boolean lock) {

        List<Object> itemList = new ArrayList<>();
        if (lock) {
            itemList.add("Unlock all Recipe!");
            itemList.addAll(searchItem);
        } else {
            itemList.addAll(searchItem);
        }

        if (itemList != null && itemList.size() > 0) {
            ((SearchAdapter) recyclerView.getAdapter()).setItems(itemList);
        }
    }

    /**
     * editText
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:rvRecipe", "app:rvError", "app:rvView"})
    public static void setError(RecyclerView recyclerView, List mRecipe,
                                boolean mError, View view) {

//        Log.e(TAG, "recipe: " + mRecipe);
//        Log.e(TAG, "subRecipe: " + mSubRecipe);
//        Log.e(TAG, "boolean: " + mError);

        if (mRecipe.size() != 0 && mError == false
                || mRecipe.size() == 0 && mError == false) {
            recyclerView.setVisibility(view.GONE);

        } else if (mRecipe.size() == 0 && mError == true) {
            recyclerView.setVisibility(view.GONE);

        } else {
            recyclerView.setVisibility(view.VISIBLE);
        }

    }

    /**
     * imageView
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:imageRecipe", "app:imageError"})
    public static void setSearchImage(ImageView imageView, List mRecipe, boolean mError) {

        if (mRecipe.size() != 0 && mError == false
                || mRecipe.size() == 0 && mError == false) {
            imageView.setImageResource(R.drawable.ic_search_color);

        } else if (mRecipe.size() == 0 && mError == true) {
            imageView.setImageResource(R.drawable.ic_local_dining_color);

        } else {
            imageView.setImageResource(0);
        }
    }

    /**
     * headerText
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:headerError", "app:headerBoolean"})
    public static void setHeaderText(TextView textView, List mRecipe, boolean mError) {

        if (mRecipe.size() != 0 && mError == false
                || mRecipe.size() == 0 && mError == false) {
            textView.setText("FIND YOUR RECIPE");

        } else if (mRecipe.size() == 0 && mError == true) {
            textView.setText("SORRY,NO RESULTS");

        } else {
            textView.setText(" ");
        }
    }

    /**
     * descriptionText
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:desError","app:desBoolean"})
    public static void setDesText(TextView textView, List mRecipe, boolean mError) {

        if (mRecipe.size() != 0 && mError == false
                || mRecipe.size() == 0 && mError == false) {
            textView.setText("Type the name and wait for the results. Enjoy your Meal!");

        } else if (mRecipe.size() == 0 && mError == true) {
            textView.setText("Use the search bar, or click on the tags below to find the recipe. Enjoy!");

        } else {
            textView.setText(" ");
        }
    }


}
