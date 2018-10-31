package com.example.adoo.recipesaplication.data.storage;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.storage.local.favorite.FavoriteLocalDataSource;

import java.util.List;

public class FavoriteRepository {

    private static final String TAG = FavoriteRepository.class.getSimpleName();

    private static FavoriteRepository sInstance = null;

    private final FavoriteLocalDataSource mFavoriteLocalDataSource;

    public FavoriteRepository(FavoriteLocalDataSource favoriteLocalDataSource) {
        mFavoriteLocalDataSource = favoriteLocalDataSource;
    }

    public static FavoriteRepository getInstance(FavoriteLocalDataSource favoriteLocalDataSource) {
        if (sInstance == null) {
            sInstance = new FavoriteRepository(favoriteLocalDataSource);
        }
        return sInstance;
    }

    /**
     * get SuggestedRecipes
     */
    public void getFavoriteRecipes(GetFavoriteRecipesCallback callback) {
        mFavoriteLocalDataSource.getFavoriteRecipes(callback);
    }

    /**
     * add
     */
    public void addFavorite(long respiseId) {
        mFavoriteLocalDataSource.addFavorite(respiseId);
    }

    /**
     * get remove
     */
    public void getRemoveFavorite(long id) {
        mFavoriteLocalDataSource.getRemove(id);
    }


    /**
     * Callback
     */
    public interface GetFavoriteRecipesCallback {
        void onSuccess(List<Recipe> recipes);

        void onError();
    }
}
