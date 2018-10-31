package com.example.adoo.recipesaplication.data.storage.local.favorite;

import android.util.Log;

import com.example.adoo.recipesaplication.data.Favorites;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.storage.FavoriteRepository;
import com.example.adoo.recipesaplication.data.storage.local.recipes.RecipesDao;
import com.example.adoo.recipesaplication.util.AppExecutors;

import java.util.List;

public class FavoriteLocalDataSource {

    private static final String TAG = FavoriteLocalDataSource.class.getSimpleName();

    private static FavoriteLocalDataSource sInstance = null;

    private final AppExecutors mAppExecutors;
    private final FavoriteDao mFavoriteDao;
    private final RecipesDao mRecipesDao;

    public FavoriteLocalDataSource(AppExecutors appExecutors, FavoriteDao favoriteDao, RecipesDao recipesDao) {
        mAppExecutors = appExecutors;
        mFavoriteDao = favoriteDao;
        mRecipesDao = recipesDao;
    }

    public static FavoriteLocalDataSource getInstance(AppExecutors appExecutors,
                                                      FavoriteDao favoriteDao,
                                                      RecipesDao recipesDao) {
        if (sInstance == null) {
            sInstance = new FavoriteLocalDataSource(appExecutors, favoriteDao, recipesDao);
        }
        return sInstance;
    }

    /******************
     *get all favorite
     *****************/
    public void getFavoriteRecipes(FavoriteRepository.GetFavoriteRecipesCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mFavoriteDao.getFavoriteRecipes();

            for (Recipe r : recipes) {
                r.setDirections(mRecipesDao.getRecipesDescription(r.getId()));
                r.setIngredients(mRecipesDao.getIngredients(r.getId()));
                r.setTag(mRecipesDao.getRecipesTag(r.getId()));
                r.setLike(mFavoriteDao.isFavorite(r.getId()) != null);
            }

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));
        });
    }

    /******
     * add
     *****/
    public void addFavorite(long recipeId){
        mAppExecutors.diskIO().execute(() ->
                mFavoriteDao.addFavorite(new Favorites(recipeId)));

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
