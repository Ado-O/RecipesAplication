package com.spartanapp.recipe.chef.data.storage.local.search;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;

import java.util.List;

@Dao
public interface SearchDao {

    @Query("SELECT * FROM recipe_table WHERE name LIKE :title")
    List<Recipe> getSearchRecipes(String title);

    @Query("SELECT * FROM sub_recipe_table WHERE name LIKE :title ||'%or%'")
    List<SubRecipe> getSearchSubRecipes(String title);
}
