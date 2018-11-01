package com.example.adoo.recipesaplication.main.search;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchBinding {

    private static final String TAG = SearchBinding.class.getSimpleName();

    /*************************
     * add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({" app:subRecipes","app:searchItem"})
    public static void setSearchItem(RecyclerView recyclerView, List subSearch, List searchItem) {

        List<Object> itemList = new ArrayList<>();
        if (MainActivity.IS_SUB){
            itemList.add("Unlock all Recipe!");
            itemList.addAll(searchItem);
            itemList.addAll(subSearch);
        }else{
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
    public static void setError(RecyclerView recyclerView, List mRecipe, boolean mError, View view) {

        if (mRecipe.size() != 0 && mError == false || mRecipe.size() == 0 && mError == false){
            recyclerView.setVisibility(view.GONE);
        }else if (mRecipe.size() == 0 && mError == true){
            recyclerView.setVisibility(view.GONE);
        }else{
            recyclerView.setVisibility(view.VISIBLE);
        }

    }

    /**
     * imageView
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:imageRecipe", "app:imageError"})
    public static void setSearchItem(ImageView imageView, List mRecipe, boolean mError) {

       if (mRecipe.size() != 0 && mError == false || mRecipe.size() == 0 && mError == false){
           imageView.setImageResource(R.drawable.ic_search_color);
       }else if (mRecipe.size() == 0 && mError == true){
           imageView.setImageResource(R.drawable.ic_local_dining_color);
       }else{
           imageView.setImageResource(0);
       }
    }

    /**
     * headerText
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:headerError", "app:headerBoolean"})
    public static void setHeaderText(TextView textView, List mRecipe, boolean mError) {

        if (mRecipe.size() != 0 && mError == false || mRecipe.size() == 0 && mError == false){
            textView.setText("FIND YOUR RECIPE");
        }else if (mRecipe.size() == 0 && mError == true){
            textView.setText("SORRY,NO RESULTS");
        }else{
            textView.setText(" ");
        }
    }

    /**
     * descriptionText
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:desError", "app:desBoolean"})
    public static void setDesText(TextView textView, List mRecipe, boolean mError) {

        if (mRecipe.size() != 0 && mError == false || mRecipe.size() == 0 && mError == false){
            textView.setText("Type the name and wait for the results. Enjoy yout Meal!");
        }else if (mRecipe.size() == 0 && mError == true){
            textView.setText("Use the search bar, or click on the tags below to find the recipe. Enjoy!");
        }else{
            textView.setText(" ");
        }
    }


}
