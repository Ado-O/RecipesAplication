package com.example.adoo.recipesaplication.data.storage.local.content;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.RecipesTag;
import com.example.adoo.recipesaplication.data.Suggested;
import com.example.adoo.recipesaplication.data.Tag;

import java.util.List;

@Dao
public interface ContentDao {
    /********
     *Recipes
     ********/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(List<Recipe> recipes);

    @Query("DELETE FROM recipe_table")
    void clearRecipe();

    /************
     *Ingredients
     ************/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredients(List<Ingredients> ingredients);

    @Query("DELETE FROM ingredients_table")
    void clearIngredients();

    /***********
     *Directions
     ***********/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDirections(List<Directions> directions);

    @Query("DELETE FROM directions_table")
    void clearDirections();

    /*****
     *Tag
     *****/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTag(List<Tag> recipes);

    @Insert
    void insertRecipesTag(List<RecipesTag> recipesTags);

    @Query("DELETE FROM recipes_tag_table WHERE recipes_id=:recipesId")
    void clearTags(long recipesId);

    /**********
     *Suggested
     **********/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSuggested(List<Suggested> suggesteds);

    @Query("DELETE FROM suggested_table WHERE recipe_id=:recipesId")
    void clearSuggested(long recipesId);
}
