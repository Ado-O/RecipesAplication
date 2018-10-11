package com.example.adoo.recipesaplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName="recipe_table")
public class Recipe implements Parcelable {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "image_url")
    private String mImage;

    @ColumnInfo(name = "info")
    private String mInfo;

    @ColumnInfo(name = "time")
    private int mTime;

    @ColumnInfo(name = "calorie")
    private int mCalorie;

    @ColumnInfo(name = "carbs")
    private int mCarbs;

    @ColumnInfo(name = "fat")
    private int mFat;

    @ColumnInfo(name = "protein")
    private int mProtein;

    @ColumnInfo(name = "level")
    private String mLevel;

    @ColumnInfo(name = "serves")
    private int mServes;

    @Ignore
    private boolean mLike;

    @Ignore
    private List<Ingredients> mIngredients = new ArrayList<>();

    @Ignore
    private List<Directions> mDirections = new ArrayList<>();

    @Ignore
    private List<Tag> mTag = new ArrayList<>();

    public Recipe(long id, String name, String image, String info, int time,
                  int calorie, int carbs, int fat, int protein, String level,
                  int serves) {
        mId = id;
        mName = name;
        mImage = image;
        mInfo = info;
        mTime = time;
        mCalorie = calorie;
        mCarbs = carbs;
        mFat = fat;
        mProtein = protein;
        mLevel = level;
        mServes = serves;
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

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
    }

    public int getCalorie() {
        return mCalorie;
    }

    public void setCalorie(int calorie) {
        mCalorie = calorie;
    }

    public int getCarbs() {
        return mCarbs;
    }

    public void setCarbs(int carbs) {
        mCarbs = carbs;
    }

    public int getFat() {
        return mFat;
    }

    public void setFat(int fat) {
        mFat = fat;
    }

    public int getProtein() {
        return mProtein;
    }

    public void setProtein(int protein) {
        mProtein = protein;
    }

    public String getLevel() {
        return mLevel;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public int getServes() {
        return mServes;
    }

    public void setServes(int serves) {
        mServes = serves;
    }

    public boolean isLike() {
        return mLike;
    }

    public void setLike(boolean like) {
        mLike = like;
    }

    public List<Ingredients> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        mIngredients = ingredients;
    }

    public List<Directions> getDirections() {
        return mDirections;
    }

    public void setDirections(List<Directions> directions) {
        mDirections = directions;
    }

    public List<Tag> getTag() {
        return mTag;
    }

    public void setTag(List<Tag> tag) {
        mTag = tag;
    }

    protected Recipe(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mImage = in.readString();
        mInfo = in.readString();
        mTime = in.readInt();
        mCalorie = in.readInt();
        mCarbs = in.readInt();
        mFat = in.readInt();
        mProtein = in.readInt();
        mLevel = in.readString();
        mServes = in.readInt();
        mLike = in.readByte() != 0;
        in.readList(this.mDirections, Directions.class.getClassLoader());
        in.readList(this.mIngredients, Ingredients.class.getClassLoader());
        in.readList(this.mTag, Tag.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mImage);
        dest.writeString(mInfo);
        dest.writeInt(mTime);
        dest.writeInt(mCalorie);
        dest.writeInt(mCarbs);
        dest.writeInt(mFat);
        dest.writeInt(mProtein);
        dest.writeString(mLevel);
        dest.writeInt(mServes);
        dest.writeByte((byte) (mLike ? 1 : 0));
        dest.writeList(mDirections);
        dest.writeList(mIngredients);
        dest.writeList(mTag);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
