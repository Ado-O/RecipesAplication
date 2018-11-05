package com.spartanapp.recipe.chef.data.storage.local.recipes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.spartanapp.recipe.chef.data.Directions;
import com.spartanapp.recipe.chef.data.Ingredients;
import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.data.Tag;

import java.util.List;

@Dao
public interface RecipesDao {

    /*******************
     *get all from table
     ********************/
    @Query("SELECT * FROM recipe_table")
    List<Recipe> getRecipes();

    @Query("SELECT * FROM sub_recipe_table")
    List<SubRecipe> getSubRecipes();

    @Query("SELECT * FROM tag_table")
    List<Tag> getTags();


    /*********
     * List<>
     ********/
    @Query("SELECT * FROM directions_table WHERE recipes_id =:id")
    List<Directions> getRecipesDescription(long id);

    @Query("SELECT * FROM ingredients_table WHERE recipes_id = :id")
    List<Ingredients> getIngredients(long id);

    @Query("SELECT tag_table.* FROM tag_table " +
            "INNER JOIN recipes_tag_table " +
            "ON tag_table._id= recipes_tag_table.tag_id " +
            "WHERE recipes_tag_table.recipes_id = :recipesId")
    List<Tag> getRecipesTag(long recipesId);


    /***************
     * get filterTag
     ***************/
    @Query("SELECT recipe_table.* FROM recipe_table " +
            "INNER JOIN recipes_tag_table " +
            "ON recipe_table._id = recipes_tag_table.recipes_id " +
            "WHERE recipes_tag_table.tag_id = :filteredTag")
    List<Recipe> getFilterTag(long filteredTag);


}
