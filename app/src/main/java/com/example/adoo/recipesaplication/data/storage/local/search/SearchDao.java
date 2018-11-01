package com.example.adoo.recipesaplication.data.storage.local.search;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.SubRecipe;

import java.util.List;

@Dao
public interface SearchDao {

    @Query("SELECT * FROM recipe_table WHERE name LIKE :title || '%'")
    List<Recipe> getSearchRecipes(String title);

    @Query("SELECT * FROM sub_recipe_table WHERE name LIKE :title || '%'")
    List<SubRecipe> getSearchSubRecipes(String title);
}
