package com.example.adoo.recipesaplication.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RecipeResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("image_url")
    @Expose
    private String mImageUrl;

    @SerializedName("info")
    @Expose
    private String mInfo;

    @SerializedName("time")
    @Expose
    private int mTime;

    @SerializedName("calorie")
    @Expose
    private int mCalorie;

    @SerializedName("carbs")
    @Expose
    private int mCarbs;

    @SerializedName("fats")
    @Expose
    private int mFats;

    @SerializedName("proteins")
    @Expose
    private int mProteins;

    @SerializedName("level")
    @Expose
    private String mLevel;

    @SerializedName("servings")
    @Expose
    private int mServings;

    @SerializedName("ingredients")
    @Expose
    private List<IngredientsResponse> mIngredients = null;

    @SerializedName("directions")
    @Expose
    private List<String> mDirections = null;

    @SerializedName("tags")
    @Expose
    private List<Integer> mTags = null;

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

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
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

    public int getFats() {
        return mFats;
    }

    public void setFats(int fats) {
        mFats = fats;
    }

    public int getProteins() {
        return mProteins;
    }

    public void setProteins(int proteins) {
        mProteins = proteins;
    }

    public String getLevel() {
        return mLevel;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public int getServings() {
        return mServings;
    }

    public void setServings(int servings) {
        mServings = servings;
    }

    public List<IngredientsResponse> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<IngredientsResponse> ingredients) {
        mIngredients = ingredients;
    }

    public List<String> getDirections() {
        return mDirections;
    }

    public void setDirections(List<String> directions) {
        mDirections = directions;
    }

    public List<Integer> getTags() {
        return mTags;
    }

    public void setTags(List<Integer> tags) {
        mTags = tags;
    }
}
