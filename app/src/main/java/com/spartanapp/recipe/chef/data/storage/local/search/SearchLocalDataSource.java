package com.spartanapp.recipe.chef.data.storage.local.search;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.data.storage.SearchRepository;
import com.spartanapp.recipe.chef.data.storage.local.favorite.FavoriteDao;
import com.spartanapp.recipe.chef.data.storage.local.recipes.RecipesDao;
import com.spartanapp.recipe.chef.util.AppExecutors;

import java.util.List;

public class SearchLocalDataSource {

    private static final String TAG = SearchLocalDataSource.class.getSimpleName();

    private static SearchLocalDataSource sInstance = null;

    private final AppExecutors mAppExecutors;
    private final SearchDao mSearchDao;
    private final RecipesDao mRecipesDao;
    private final FavoriteDao mFavoriteDao;

    public SearchLocalDataSource(AppExecutors appExecutors, SearchDao searchDao,
                                 RecipesDao recipesDao, FavoriteDao favoriteDao) {
        mAppExecutors = appExecutors;
        mSearchDao = searchDao;
        mRecipesDao = recipesDao;
        mFavoriteDao = favoriteDao;
    }

    public static SearchLocalDataSource getInstance(AppExecutors appExecutors,
                                                    SearchDao searchDao,
                                                    RecipesDao recipesDao,
                                                    FavoriteDao favoriteDao) {
        if (sInstance == null) {
            sInstance = new SearchLocalDataSource(appExecutors, searchDao,
                    recipesDao, favoriteDao);
        }
        return sInstance;
    }

    /************
     * get search
     ***********/
    public void getSearchRecipes(String title, SearchRepository.GetSearchCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mSearchDao.getSearchRecipes('%'+title+'%');

            for (Recipe r : recipes) {
                r.setDirections(mRecipesDao.getRecipesDescription(r.getId()));
                r.setIngredients(mRecipesDao.getIngredients(r.getId()));
                r.setTag(mRecipesDao.getRecipesTag(r.getId()));
                r.setLike(mFavoriteDao.isFavorite(r.getId()) != null);
            }

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));

        });
    }

    /************
     * get search
     ***********/
    public void getSearchSubRecipes(String title, SearchRepository.GetSubSearchCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<SubRecipe> subRecipes = mSearchDao.getSearchSubRecipes(title);

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(subRecipes));

        });
    }
}
