package com.spartanapp.recipe.chef.main.description;

import android.databinding.BindingAdapter;
import android.support.design.chip.ChipGroup;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.data.Tag;

import java.util.List;

public class DescriptionBinding {

    private static final String TAG = DescriptionBinding.class.getSimpleName();

    /************************
     * add tag with use ChipGroup
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:descTags"})
    public static void setLayout(ChipGroup chipGroup, List tags) {

        chipGroup.removeAllViews();

        if (tags != null) {
            LayoutInflater inflater = LayoutInflater.from(chipGroup.getContext());

            for (Tag tag : ((List<Tag>) tags)) {
          //      if (tags.size() <= 3) {
                        TextView view = (TextView) inflater.inflate(
                                R.layout.description_tag, chipGroup, false
                        );

                        view.setText(tag.getName());
                        chipGroup.addView(view);

                }

//                Button viewBtn = (Button) inflater.inflate(
//                        R.layout.description_tag_more, chipGroup, false
//                );
//                chipGroup.addView(viewBtn);


            //}
        }
    }

    /*************************
     *add list with use RecyclerView adapter
     ************************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:itemDesc"})
    public static void setItemDesc(RecyclerView recyclerView, List recipeItems) {

        if (recipeItems != null && recipeItems.size() > 0) {
            ((DescriptionAdapter) recyclerView.getAdapter()).setItems(recipeItems);
        }
    }

}

