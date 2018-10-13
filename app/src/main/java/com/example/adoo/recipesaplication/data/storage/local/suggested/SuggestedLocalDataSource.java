package com.example.adoo.recipesaplication.data.storage.local.suggested;

import android.util.Log;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.Suggested;
import com.example.adoo.recipesaplication.data.storage.SuggestedRepository;
import com.example.adoo.recipesaplication.util.AppExecutors;

import java.util.List;

public class SuggestedLocalDataSource {

    private static final String TAG = SuggestedLocalDataSource.class.getSimpleName();

    private static SuggestedLocalDataSource sInstance = null;

    private final AppExecutors mAppExecutors;
    private final SuggestedDao mSuggestedDao;

    public SuggestedLocalDataSource(AppExecutors appExecutors, SuggestedDao suggestedDao) {
        mAppExecutors = appExecutors;
        mSuggestedDao = suggestedDao;
    }

    public static SuggestedLocalDataSource getInstance(AppExecutors appExecutors, SuggestedDao suggestedDao) {
        if (sInstance == null) {
            sInstance = new SuggestedLocalDataSource(appExecutors, suggestedDao);
        }
        return sInstance;
    }

    /******************
     *get all suggested
     *****************/
    public void getSuggestedRecipes(SuggestedRepository.GetSuggestedRecipesCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mSuggestedDao.getSuggestedRecipes();

            Log.e(TAG, String.valueOf(recipes.size()));
            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));
        });
    }

    /************
     * get search
     ***********/
    public void getSearchRecipes(String title, SuggestedRepository.GetSearchCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mSuggestedDao.getSearchRecipes(title);

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));

        });
    }






}
