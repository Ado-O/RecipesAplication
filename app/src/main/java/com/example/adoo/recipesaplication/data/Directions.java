package com.example.adoo.recipesaplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName="directions_table")
public class Directions {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name= "recipes_id")
    private long mRecipesId;

    @ColumnInfo(name = "description")
    private String mDes;

    public Directions(Integer id, long recipesId, String des) {
        mId = id;
        mRecipesId = recipesId;
        mDes = des;
    }

    @Ignore
    public Directions(long recipesId, String des) {
        mId = null;
        mRecipesId = recipesId;
        mDes = des;
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

    public String getDes() {
        return mDes;
    }

    public void setDes(String des) {
        mDes = des;
    }

}
