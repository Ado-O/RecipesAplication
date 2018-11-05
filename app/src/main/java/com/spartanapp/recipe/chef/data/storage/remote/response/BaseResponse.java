package com.spartanapp.recipe.chef.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    @SerializedName("data")
    @Expose
    private DataResponse mDataResponse;

    public DataResponse getData() {
        return mDataResponse;
    }

    public void setData(DataResponse data) {
        this.mDataResponse = data;
    }
}
