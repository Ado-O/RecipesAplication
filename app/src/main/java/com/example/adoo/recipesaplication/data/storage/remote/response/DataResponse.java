package com.example.adoo.recipesaplication.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataResponse implements Serializable {

    @SerializedName("tags")
    @Expose
    private List<TagsResponse> tags = null;

    @SerializedName("recipes")
    @Expose
    private List<RecipeResponse> recipes = null;

    public List<TagsResponse> getTags() {
        return tags;
    }

    public void setTags(List<TagsResponse> tags) {
        this.tags = tags;
    }

    public List<RecipeResponse> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeResponse> recipes) {
        this.recipes = recipes;
    }

}
