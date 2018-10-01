package com.example.adoo.recipesaplication.data.storage.remote.content;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.adoo.recipesaplication.data.storage.remote.response.BaseResponse;
import com.example.adoo.recipesaplication.util.MockJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ContentRemoteDataSource {

    private static final String TAG = ContentRemoteDataSource.class.getSimpleName();

    private static ContentRemoteDataSource sInstance;
    private final Context mContext;

    public ContentRemoteDataSource(Context context) {
        mContext = context;
    }

    public static ContentRemoteDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ContentRemoteDataSource(context);
        }
        return sInstance;
    }

    public void getRecipe(final GetRecipeCallback callback) {
        /**
         * cod for MockJson
         */
        BaseResponse baseResponse =
                new Gson().fromJson(
                        MockJson.getJsonFromAsset(
                                mContext, "mock.txt"
                        ),
                        new TypeToken<BaseResponse>() {
                        }.getType()
                );

        callback.onSuccess(
                baseResponse
        );
    }

    public interface GetRecipeCallback {
        void onSuccess(BaseResponse baseResponse);

        void onError();
    }
}
