package com.example.adoo.recipesaplication.util;

import android.view.View;
import android.widget.ImageView;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.Tag;

public interface RecyclerViewClickListener {
    void recyclerViewListClicked(View v, Recipe recipe);

    void favoritesCLickListener(View view, Recipe recipe);
}
