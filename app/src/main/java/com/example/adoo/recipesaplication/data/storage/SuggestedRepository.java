package com.example.adoo.recipesaplication.data.storage;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.Suggested;
import com.example.adoo.recipesaplication.data.storage.local.suggested.SuggestedLocalDataSource;

import java.util.List;

public class SuggestedRepository {

    private static final String TAG = SuggestedRepository.class.getSimpleName();

    private static SuggestedRepository sInstance = null;

    private final SuggestedLocalDataSource mSuggestedLocalDataSource;

    public SuggestedRepository(SuggestedLocalDataSource suggestedLocalDataSource) {
        mSuggestedLocalDataSource = suggestedLocalDataSource;
    }

    public static SuggestedRepository getInstance(SuggestedLocalDataSource suggestedLocalDataSource) {
        if (sInstance == null) {
            sInstance = new SuggestedRepository(suggestedLocalDataSource);
        }
        return sInstance;
    }

    /**
     * get SuggestedRecipes
     */
    public void getSuggestedRecipes(GetSuggestedRecipesCallback callback) {
        mSuggestedLocalDataSource.getSuggestedRecipes(callback);
    }

    /**
     * get Search
     */
    public void getSearchRecipes(String title, GetSearchCallback callback) {
        mSuggestedLocalDataSource.getSearchRecipes(title, callback);
    }

    /**
     * Callback
     */
    public interface GetSuggestedRecipesCallback {
        void onSuccess(List<Recipe> suggesteds);

        void onError();
    }


    public interface GetSearchCallback {
        void onSuccess(List<Recipe> recipes);

        void onError();
    }
}
