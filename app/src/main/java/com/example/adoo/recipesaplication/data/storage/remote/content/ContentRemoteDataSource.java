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
        Log.e(TAG, baseResponse.getData().getRecipes().get(0).getName());
        Log.e(TAG, "directions "+baseResponse.getData().getRecipes().get(0).getDirections());
        Log.e(TAG, "ingredients "+baseResponse.getData().getRecipes().get(0).getIngredients().get(0).getAmount());
        Log.e(TAG, "tags "+baseResponse.getData().getRecipes().get(0).getTags());
        Log.e(TAG, String.valueOf(baseResponse.getData().getSuggested().get(0)));
        Log.e(TAG, baseResponse.getData().getTags().get(0).getName());

    }

    public interface GetRecipeCallback {
        void onSuccess(BaseResponse baseResponse);

        void onError();
    }
}
