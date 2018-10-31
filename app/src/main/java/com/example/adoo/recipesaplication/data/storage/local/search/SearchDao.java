package com.example.adoo.recipesaplication.data.storage.local.search;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.adoo.recipesaplication.data.Recipe;

import java.util.List;

@Dao
public interface SearchDao {

    @Query("SELECT * FROM recipe_table WHERE name LIKE :title || '%'")
    List<Recipe> getSearchRecipes(String title);

}
