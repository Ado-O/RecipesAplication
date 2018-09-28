package com.example.adoo.recipesaplication.data.storage;

import android.content.Context;
import android.util.Log;

import com.example.adoo.recipesaplication.data.storage.local.content.ContentLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.local.recipes.RecipesLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.remote.content.ContentRemoteDataSource;
import com.example.adoo.recipesaplication.data.storage.remote.response.BaseResponse;
import com.example.adoo.recipesaplication.util.AppExecutors;

public class ContentRepository {
    private static final String TAG = ContentRepository.class.getSimpleName();

    private final Context mContext;
    private final ContentRemoteDataSource mContentRemoteDataSource;
    private final ContentLocalDataSource mContentLocalDataSource;
    private final RecipesLocalDataSource mRecipesLocalDataSource;
    private final AppExecutors mAppExecutors;


    private static ContentRepository sInstance = null;

    public ContentRepository(Context context,
                             ContentRemoteDataSource contentRemoteDataSource,
                             ContentLocalDataSource contentLocalDataSource,
                             RecipesLocalDataSource recipesLocalDataSource,
                             AppExecutors appExecutors) {
        mContext = context;
        mContentRemoteDataSource = contentRemoteDataSource;
        mContentLocalDataSource = contentLocalDataSource;
        mRecipesLocalDataSource = recipesLocalDataSource;
        mAppExecutors = appExecutors;
    }

    public static ContentRepository getInstance(Context context,
                                                ContentRemoteDataSource contentRemoteDataSource,
                                                ContentLocalDataSource contentLocalDataSource,
                                                RecipesLocalDataSource recipesLocalDataSource,
                                                AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new ContentRepository(context, contentRemoteDataSource,
                    contentLocalDataSource, recipesLocalDataSource, appExecutors);
        }
        return sInstance;
    }


    public void setContent() {

        mContentRemoteDataSource.getRecipe(new ContentRemoteDataSource.GetRecipeCallback() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                mAppExecutors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mContentLocalDataSource.setContent(
                                baseResponse);
                    }
                });
            }

            @Override
            public void onError() {
                Log.e(TAG, "OnError");
            }
        });
    }

}
