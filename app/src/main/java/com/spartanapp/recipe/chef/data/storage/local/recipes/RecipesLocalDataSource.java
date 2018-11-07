package com.spartanapp.recipe.chef.data.storage.local.recipes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.data.Tag;
import com.spartanapp.recipe.chef.data.storage.RecipesRepository;
import com.spartanapp.recipe.chef.data.storage.local.favorite.FavoriteDao;
import com.spartanapp.recipe.chef.main.MainActivity;
import com.spartanapp.recipe.chef.util.AppExecutors;

import java.util.List;

public class RecipesLocalDataSource {
    private static final String TAG = RecipesLocalDataSource.class.getSimpleName();

    private static RecipesLocalDataSource sInstance = null;

    private final AppExecutors mAppExecutors;
    private final RecipesDao mRecipesDao;
    private final FavoriteDao mFavoriteDao;
    @NonNull
    private final Context mContext;

    public RecipesLocalDataSource(Context context, AppExecutors appExecutors, RecipesDao recipesDao, FavoriteDao favoriteDao) {
        mContext = context;
        mAppExecutors = appExecutors;
        mRecipesDao = recipesDao;
        mFavoriteDao = favoriteDao;
    }

    public static RecipesLocalDataSource getInstance(Context context,
                                                     AppExecutors appExecutors,
                                                     RecipesDao recipesDao,
                                                     FavoriteDao favoriteDao) {
        if (sInstance == null) {
            sInstance = new RecipesLocalDataSource(context, appExecutors, recipesDao, favoriteDao);
        }
        return sInstance;
    }

    /*********
     * Recipes
     *********/
    public void getRecipes(RecipesRepository.GetRecipesCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mRecipesDao.getRecipes();

            for (Recipe r : recipes) {

                r.setDirections(mRecipesDao.getRecipesDescription(r.getId()));
                r.setIngredients(mRecipesDao.getIngredients(r.getId()));
                r.setTag(mRecipesDao.getRecipesTag(r.getId()));
                r.setLike(mFavoriteDao.isFavorite(r.getId()) != null);

                SharedPreferences sub = mContext.getSharedPreferences("is_sub", 0);
                boolean isSub = sub.getBoolean("free", false);

                if (!isSub) {
                    if (r.getId() == 1 || r.getId() == 2 || r.getId() == 3
                            || r.getId() == 4 || r.getId() == 5) {
                        r.setLock(true);
                    }else{
                        r.setLock(false);
                    }
                }else{
                    r.setLock(true);
                }

            }

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));
        });
    }


    /*********
     * Recipes
     *********/
    public void getSubRecipes(RecipesRepository.GetSubRecipesCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<SubRecipe> subRecipes = mRecipesDao.getSubRecipes();

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(subRecipes));
        });
    }

    /*******
     * Tag
     ********/
    public void getTag(RecipesRepository.GetTagCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Tag> tags = mRecipesDao.getTags();

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(tags));
        });
    }

    /*************
     * RecipesTag
     *************/
    public void getRecipesTag(long filteredTag, RecipesRepository.
            GetRecipesTagCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mRecipesDao.getFilterTag(filteredTag);

            for (Recipe r : recipes) {
                r.setDirections(mRecipesDao.getRecipesDescription(r.getId()));
                r.setIngredients(mRecipesDao.getIngredients(r.getId()));
                r.setTag(mRecipesDao.getRecipesTag(r.getId()));

                SharedPreferences sub = mContext.getSharedPreferences("is_sub", 0);
                boolean isSub = sub.getBoolean("free", false);

                if (!isSub) {
                    if (r.getId() == 1 || r.getId() == 2 || r.getId() == 3
                            || r.getId() == 4 || r.getId() == 5) {
                        r.setLock(true);
                    } else {
                        r.setLock(false);
                    }
                } else {
                    r.setLock(true);
                }
            }


            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));

        });
    }
}
