package com.spartanapp.recipe.chef.data.storage.local.favorite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.spartanapp.recipe.chef.data.Favorites;
import com.spartanapp.recipe.chef.data.Recipe;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT recipe_table.* FROM recipe_table " +
            "INNER JOIN favorite_table " +
            "ON recipe_table._id = favorite_table.recipe_id ")
    List<Recipe> getFavoriteRecipes();

    @Insert
    void addFavorite(Favorites favorite);

    @Query("DELETE FROM favorite_table WHERE recipe_id = :id")
    void removeFavorite(long id);

    @Query("SELECT * FROM favorite_table WHERE recipe_id = :recipeId")
    Favorites isFavorite(long recipeId);

}
