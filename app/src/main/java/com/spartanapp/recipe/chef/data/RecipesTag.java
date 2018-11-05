package com.spartanapp.recipe.chef.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "recipes_tag_table")
public class RecipesTag {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "recipes_id")
    private long mRecipesId;

    @ColumnInfo(name = "tag_id")
    private long mTagId;

    public RecipesTag(Integer id, long recipesId, long tagId) {
        mId = id;
        mRecipesId = recipesId;
        mTagId = tagId;
    }

    @Ignore
    public RecipesTag(long recipesId, long tagId) {
        mId = null;
        mRecipesId = recipesId;
        mTagId = tagId;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public long getRecipesId() {
        return mRecipesId;
    }

    public void setRecipesId(long recipesId) {
        mRecipesId = recipesId;
    }

    public long getTagId() {
        return mTagId;
    }

    public void setTagId(long tagId) {
        mTagId = tagId;
    }
}

