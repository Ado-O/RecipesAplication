package com.spartanapp.recipe.chef.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "ingredients_table")
public class Ingredients implements Parcelable{

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name= "recipes_id")
    private long mRecipesId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "amount")
    private String mQuantity;

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

    public Ingredients(Integer id, long recipesId,String name, String quantity) {
        mId = id;
        mRecipesId = recipesId;
        mName = name;
        mQuantity = quantity;
    }

    @Ignore
    public Ingredients(long recipesId, String name, String quantity) {
        mId = null;
        mRecipesId = recipesId;
        mName = name;
        mQuantity = quantity;
    }

    protected Ingredients(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mRecipesId = in.readLong();
        mName = in.readString();
        mQuantity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mId);
        }
        dest.writeLong(mRecipesId);
        dest.writeString(mName);
        dest.writeString(mQuantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
