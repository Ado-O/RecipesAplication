package com.spartanapp.recipe.chef.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName="directions_table")
public class Directions implements Parcelable {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name= "recipes_id")
    private long mRecipesId;

    @ColumnInfo(name = "number")
    private String mNumber;

    @ColumnInfo(name = "directions")
    private String mDirections;

    public Directions(Integer id, long recipesId, String number, String directions) {
        mId = id;
        mRecipesId = recipesId;
        mNumber = number;
        mDirections = directions;
    }

    @Ignore
    public Directions(long recipesId, String number, String directions) {
        mId = null;
        mRecipesId = recipesId;
        mNumber = number;
        mDirections = directions;
    }

    protected Directions(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mRecipesId = in.readLong();
        mNumber = in.readString();
        mDirections = in.readString();
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
        dest.writeString(mNumber);
        dest.writeString(mDirections);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Directions> CREATOR = new Creator<Directions>() {
        @Override
        public Directions createFromParcel(Parcel in) {
            return new Directions(in);
        }

        @Override
        public Directions[] newArray(int size) {
            return new Directions[size];
        }
    };

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

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getDirections() {
        return mDirections;
    }

    public void setDirections(String directions) {
        mDirections = directions;
    }
}
