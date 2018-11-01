package com.example.adoo.recipesaplication.data.storage;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.SubRecipe;
import com.example.adoo.recipesaplication.data.storage.local.search.SearchLocalDataSource;

import java.util.List;

public class SearchRepository {

    private static final String TAG = SearchRepository.class.getSimpleName();

    private static SearchRepository sInstance = null;

    private final SearchLocalDataSource mSearchLocalDataSource;

    public SearchRepository(SearchLocalDataSource searchLocalDataSource) {
        mSearchLocalDataSource = searchLocalDataSource;
    }

    public static SearchRepository getInstance(SearchLocalDataSource searchLocalDataSource) {
        if (sInstance == null) {
            sInstance = new SearchRepository(searchLocalDataSource);
        }
        return sInstance;
    }

    /**
     * get Search
     */
    public void getSearchRecipes(String title, GetSearchCallback callback) {
        mSearchLocalDataSource.getSearchRecipes(title, callback);
    }

    /**
     * get SubSearch
     */
    public void getSubSearchRecipes(String title, GetSubSearchCallback callback) {
        mSearchLocalDataSource.getSearchSubRecipes(title, callback);
    }



    public interface GetSearchCallback {
        void onSuccess(List<Recipe> recipes);

        void onError();
    }

    public interface GetSubSearchCallback {
        void onSuccess(List<SubRecipe> recipes);

        void onError();
    }
}
