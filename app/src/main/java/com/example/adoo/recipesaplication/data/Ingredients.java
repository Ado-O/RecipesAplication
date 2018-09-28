package com.example.adoo.recipesaplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "ingredients_table")
public class Ingredients {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "quantity")
    private String mQuantity;

    public Ingredients(Integer id, String name, String quantity) {
        mId = id;
        mName = name;
        mQuantity = quantity;
    }

    @Ignore
    public Ingredients(String name, String quantity) {
        mId = null;
        mName = name;
        mQuantity = quantity;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }

}
