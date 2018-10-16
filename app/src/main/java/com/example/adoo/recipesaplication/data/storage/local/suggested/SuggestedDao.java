package com.example.adoo.recipesaplication.data.storage.local.suggested;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.Suggested;

import java.util.List;

@Dao
public interface SuggestedDao {

    @Query("SELECT recipe_table.* FROM recipe_table " +
            "INNER JOIN suggested_table " +
            "ON recipe_table._id = suggested_table.recipe_id ")
    List<Recipe> getSuggestedRecipes();

    @Query("SELECT * FROM recipe_table WHERE name LIKE :title || '%'")
    List<Recipe> getSearchRecipes(String title);

}
