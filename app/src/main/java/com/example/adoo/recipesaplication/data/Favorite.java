package com.example.adoo.recipesaplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName="favorite_table")
public class Favorite {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "recipe_id")
    private long mRecipeId;

    public Favorite(Integer id, long recipeId) {
        mId = id;
        mRecipeId = recipeId;
    }

    @Ignore
    public Favorite(long recipeId) {
        mId = null;
        mRecipeId = recipeId;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public long getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(long recipeId) {
        mRecipeId = recipeId;
    }

}
