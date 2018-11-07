package com.spartanapp.recipe.chef.main;

import android.databinding.BindingAdapter;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.view.LayoutInflater;

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.data.Tag;
import com.spartanapp.recipe.chef.util.FilterClickListener;

import java.util.List;

public class MainBinding {

    private static final String TAG = MainBinding.class.getSimpleName();

    /******************
     * in RecipesFragment we relase all tag from tag_table db and add setOnClickListener
     * @param chipGroup - parent and he have chip - tag
     * @param items - all tag in List</>
     * @param listener - interface wich he gave onClick
     ****************/
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:tags", "app:listener"})
    public static void setTags(ChipGroup chipGroup, List<Tag> items, FilterClickListener listener) {

        chipGroup.removeAllViews();

        if (items != null) {

            LayoutInflater inflater = LayoutInflater.from(chipGroup.getContext());
            for (Tag tag : items) {

                Chip chip = (Chip) inflater.inflate(
                        R.layout.recipes_tag, chipGroup, false
                );

                chip.setOnClickListener(v -> {

                    if (listener != null) {
                        listener.onClick(tag);
                    }
                });

                chip.setChipText(tag.getName());
                chipGroup.addView(chip);
            }
        }
    }

}