package com.example.adoo.recipesaplication.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IngredientsResponse implements Serializable {


    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("amount")
    @Expose
    private String mAmount;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        this.mAmount = amount;
    }
}
