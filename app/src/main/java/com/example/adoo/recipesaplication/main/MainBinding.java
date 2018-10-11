package com.example.adoo.recipesaplication.main;

import android.databinding.BindingAdapter;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Tag;
import com.example.adoo.recipesaplication.util.FilterClickListener;

import java.util.List;

public class MainBinding {

    private static final String TAG = MainBinding.class.getSimpleName();

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:tags", "app:listener"})
    public static void setTags(ChipGroup chipGroup, List<Tag> items, FilterClickListener listener) {

        chipGroup.removeAllViews();

        Log.e(TAG, "hi");

        if (items != null) {

            Log.e("tags", String.valueOf(items.size()));

            LayoutInflater inflater = LayoutInflater.from(chipGroup.getContext());
            for (Tag tag : items) {

                Chip chip = (Chip) inflater.inflate(
                        R.layout.filter_tag, chipGroup, false
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
