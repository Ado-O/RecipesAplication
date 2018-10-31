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

    @SerializedName("image")
    @Expose
    private String mImageUrl;

    @SerializedName("time")
    @Expose
    private String mTime;

    @SerializedName("servers")
    @Expose
    private int mServings;

    @SerializedName("level")
    @Expose
    private String mLevel;

    @SerializedName("calorie")
    @Expose
    private String mCalorie;

    @SerializedName("carbohydrates")
    @Expose
    private String mCarbs;

    @SerializedName("protein")
    @Expose
    private String mProteins;

    @SerializedName("fat")
    @Expose
    private String mFats;

    @SerializedName("Ingredients")
    @Expose
    private List<IngredientsResponse> mIngredients = null;

    @SerializedName("Directions")
    @Expose
    private List<DirectionsResponse> mDirections = null;

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

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public int getServings() {
        return mServings;
    }

    public void setServings(int servings) {
        mServings = servings;
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

    public String getProteins() {
        return mProteins;
    }

    public void setProteins(String proteins) {
        mProteins = proteins;
    }

    public String getFats() {
        return mFats;
    }

    public void setFats(String fats) {
        mFats = fats;
    }

    public List<IngredientsResponse> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<IngredientsResponse> ingredients) {
        mIngredients = ingredients;
    }

    public List<DirectionsResponse> getDirections() {
        return mDirections;
    }

    public void setDirections(List<DirectionsResponse> directions) {
        mDirections = directions;
    }

    public List<Integer> getTags() {
        return mTags;
    }

    public void setTags(List<Integer> tags) {
        mTags = tags;
    }
}
