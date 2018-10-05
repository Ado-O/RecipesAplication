package com.example.adoo.recipesaplication.data.storage.local.favorite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.adoo.recipesaplication.data.Favorite;
import com.example.adoo.recipesaplication.data.Recipe;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT recipe_table.* FROM recipe_table " +
            "LEFT JOIN favorite_table " +
            "ON recipe_table._id = favorite_table.recipe_id ")
    List<Recipe> getFavoriteRecipes();

   @Insert
    void addFavorite(Favorite favorite);

    @Query("DELETE FROM favorite_table WHERE recipe_id=:id")
    void removeFavorite(long id);

}
