package com.spartanapp.recipe.chef.util;

import android.view.View;

import com.spartanapp.recipe.chef.data.Recipe;

public interface RecyclerViewClickListener {
    void recyclerViewListClicked(View v, Recipe recipe);

    void favoritesCLickListener(View v, Recipe recipe);

}
