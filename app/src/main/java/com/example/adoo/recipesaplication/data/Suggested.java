package com.example.adoo.recipesaplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName="suggested_table")
public class Suggested{

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "recipe_id")
    private int mRecipeId;

    public Suggested(Integer id, int recipeId) {
        mId = id;
        mRecipeId = recipeId;
    }

    @Ignore
    public Suggested(int recipeId) {
        mId = null;
        mRecipeId = recipeId;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public int getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(int recipeId) {
        mRecipeId = recipeId;
    }

}
