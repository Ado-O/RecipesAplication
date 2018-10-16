package com.example.adoo.recipesaplication.main.description;

import android.databinding.BindingAdapter;
import android.support.design.chip.ChipGroup;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DescriptionBinding {

    private static final String TAG = DescriptionBinding.class.getSimpleName();

    /*********
     * add tag with use ChipGroup
     *********/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:descTags"})
    public static void setLayout(ChipGroup chipGroup, List tags) {

        chipGroup.removeAllViews();

        if (tags != null) {
            LayoutInflater inflater = LayoutInflater.from(chipGroup.getContext());
            for (Tag tag : ((List<Tag>) tags)) {

                TextView view = (TextView) inflater.inflate(
                        R.layout.description_tag, chipGroup, false
                );

                view.setText(tag.getName());
                chipGroup.addView(view);
            }
        }
    }

    /**********
     * convert number in minute
     *********/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:duration"})
    public static void setNumber(TextView textView, int number) {

        int minutes = (int) ((number % 3600) / 60);

        String addMinuit = minutes + " min";

        textView.setText(String.valueOf(addMinuit));
    }

    /**********
     *add list with use RecyclerView adapter
     *********/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:itemDesc"})
    public static void setItemDesc(RecyclerView recyclerView, List recipeItems) {

        if (recipeItems != null && recipeItems.size() > 0) {
            ((DescriptionAdapter) recyclerView.getAdapter()).setItems(recipeItems);
        }
    }
    /**********
     *create number list in direction layout
     *********/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:textCount"})
    public static void setTextCount(TextView textView, int directionId) {

        String number = directionId + ".";

            textView.setText(number);

      //  Log.e(TAG, String.valueOf(directionId));
    }
}

