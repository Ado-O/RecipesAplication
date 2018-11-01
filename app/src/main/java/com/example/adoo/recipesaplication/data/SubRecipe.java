package com.example.adoo.recipesaplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName="sub_recipe_table")
public class SubRecipe implements Parcelable {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "image")
    private String mImage;

    public SubRecipe(long id, String name, String image) {
        mId = id;
        mName = name;
        mImage = image;
    }

    protected SubRecipe(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mImage = in.readString();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubRecipe> CREATOR = new Creator<SubRecipe>() {
        @Override
        public SubRecipe createFromParcel(Parcel in) {
            return new SubRecipe(in);
        }

        @Override
        public SubRecipe[] newArray(int size) {
            return new SubRecipe[size];
        }
    };
}
