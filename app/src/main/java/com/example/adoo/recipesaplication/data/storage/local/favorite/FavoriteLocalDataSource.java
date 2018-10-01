package com.example.adoo.recipesaplication.data.storage.local.favorite;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.storage.FavoriteRepository;
import com.example.adoo.recipesaplication.data.storage.local.suggested.SuggestedDao;
import com.example.adoo.recipesaplication.data.storage.local.suggested.SuggestedLocalDataSource;
import com.example.adoo.recipesaplication.util.AppExecutors;

import java.util.List;

public class FavoriteLocalDataSource {

    private static final String TAG = FavoriteLocalDataSource.class.getSimpleName();

    private static FavoriteLocalDataSource sInstance = null;

    private final AppExecutors mAppExecutors;
    private final FavoriteDao mFavoriteDao;

    public FavoriteLocalDataSource(AppExecutors appExecutors, FavoriteDao favoriteDao) {
        mAppExecutors = appExecutors;
        mFavoriteDao = favoriteDao;
    }

    public static FavoriteLocalDataSource getInstance(AppExecutors appExecutors, FavoriteDao favoriteDao) {
        if (sInstance == null) {
            sInstance = new FavoriteLocalDataSource(appExecutors, favoriteDao);
        }
        return sInstance;
    }

    /******************
     *get all favorite
     *****************/
    public void getFavoriteRecipes(FavoriteRepository.GetFavoriteRecipesCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mFavoriteDao.getFavoriteRecipes();

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));
        });
    }

    /******
     * add
     *****/
    public void addFavorite(int RecipesId){
        mAppExecutors.diskIO().execute(() -> {

            mFavoriteDao.addFavorite(RecipesId);

        });
    }

    /********
     * remove
     ********/
    public void getRemove(long id){
        mAppExecutors.diskIO().execute(() -> {

            mFavoriteDao.removeFavorite(id);

        });
    }


}
