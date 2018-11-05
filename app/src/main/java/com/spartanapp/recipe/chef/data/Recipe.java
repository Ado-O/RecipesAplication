package com.spartanapp.recipe.chef.data;

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

    @ColumnInfo(name = "image")
    private String mImage;

    @ColumnInfo(name = "time")
    private String mTime;

    @ColumnInfo(name = "servers")
    private int mServes;

    @ColumnInfo(name = "level")
    private String mLevel;

    @ColumnInfo(name = "calorie")
    private String mCalorie;

    @ColumnInfo(name = "carbohydrates")
    private String mCarbs;

    @ColumnInfo(name = "protein")
    private String mProtein;

    @ColumnInfo(name = "fat")
    private String mFat;

    @Ignore
    private boolean mLike;

    @Ignore
    private boolean mPolicy;

    @Ignore
    private List<Ingredients> mIngredients = new ArrayList<>();

    @Ignore
    private List<Directions> mDirections = new ArrayList<>();

    @Ignore
    private List<Tag> mTag = new ArrayList<>();

    public Recipe(long id, String name, String image, String time, int serves,
                  String level, String calorie, String carbs, String protein,
                  String fat) {
        mId = id;
        mName = name;
        mImage = image;
        mTime = time;
        mServes = serves;
        mLevel = level;
        mCalorie = calorie;
        mCarbs = carbs;
        mProtein = protein;
        mFat = fat;
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

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public int getServes() {
        return mServes;
    }

    public void setServes(int serves) {
        mServes = serves;
    }

    public String getLevel() {
        return mLevel;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public String getCalorie() {
        return mCalorie;
    }

    public void setCalorie(String calorie) {
        mCalorie = calorie;
    }

    public String getCarbs() {
        return mCarbs;
    }

    public void setCarbs(String carbs) {
        mCarbs = carbs;
    }

    public String getProtein() {
        return mProtein;
    }

    public void setProtein(String protein) {
        mProtein = protein;
    }

    public String getFat() {
        return mFat;
    }

    public void setFat(String fat) {
        mFat = fat;
    }

    public boolean isLike() {
        return mLike;
    }

    public void setLike(boolean like) {
        mLike = like;
    }

    public boolean isPolicy() {
        return mPolicy;
    }

    public void setPolicy(boolean policy) {
        mPolicy = policy;
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
        mTime = in.readString();
        mServes = in.readInt();
        mLevel = in.readString();
        mCalorie = in.readString();
        mCarbs = in.readString();
        mProtein = in.readString();
        mFat = in.readString();
        mLike = in.readByte() != 0;
        mPolicy = in.readByte() != 0;
        mIngredients = in.createTypedArrayList(Ingredients.CREATOR);
        mDirections = in.createTypedArrayList(Directions.CREATOR);
        mTag = in.createTypedArrayList(Tag.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mImage);
        dest.writeString(mTime);
        dest.writeInt(mServes);
        dest.writeString(mLevel);
        dest.writeString(mCalorie);
        dest.writeString(mCarbs);
        dest.writeString(mProtein);
        dest.writeString(mFat);
        dest.writeByte((byte) (mLike ? 1 : 0));
        dest.writeByte((byte) (mPolicy ? 1 : 0));
        dest.writeTypedList(mIngredients);
        dest.writeTypedList(mDirections);
        dest.writeTypedList(mTag);
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
