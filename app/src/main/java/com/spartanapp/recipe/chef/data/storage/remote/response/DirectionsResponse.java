package com.spartanapp.recipe.chef.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DirectionsResponse implements Serializable {

    @SerializedName("number")
    @Expose
    private String mNumber;

    @SerializedName("directions")
    @Expose
    private String mDirections;

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
