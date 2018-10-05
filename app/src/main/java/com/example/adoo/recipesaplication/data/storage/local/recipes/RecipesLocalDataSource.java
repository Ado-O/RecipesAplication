package com.example.adoo.recipesaplication.data.storage.local.recipes;

import android.util.Log;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.Tag;
import com.example.adoo.recipesaplication.data.storage.RecipesRepository;
import com.example.adoo.recipesaplication.util.AppExecutors;

import java.util.List;

public class RecipesLocalDataSource {
        private static final String TAG = RecipesLocalDataSource.class.getSimpleName();

    private static RecipesLocalDataSource sInstance = null;

    private final AppExecutors mAppExecutors;
    private final RecipesDao mRecipesDao;

    public RecipesLocalDataSource(AppExecutors appExecutors, RecipesDao recipesDao) {
        mAppExecutors = appExecutors;
        mRecipesDao = recipesDao;
    }

    public static RecipesLocalDataSource getInstance(AppExecutors appExecutors, RecipesDao recipesDao) {
        if (sInstance == null) {
            sInstance = new RecipesLocalDataSource(appExecutors, recipesDao);
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
            }

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));
        });
    }

    /*******
     * Tag
     ********/
    public void getTag(RecipesRepository.GetTagCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Tag> tags = mRecipesDao.getTags();

            Log.e("tags-LocalData", String.valueOf(tags.size())+" ....");
            mAppExecutors.mainThread().execute(() -> callback.onSuccess(tags));
        });
    }

    /*************
     * RecipesTag
     *************/
    public void getRecipesTag(long filteredTag, RecipesRepository.GetRecipesTagCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Recipe> recipes = mRecipesDao.getFilterTag(filteredTag);

            for (Recipe r : recipes) {
                r.setDirections(mRecipesDao.getRecipesDescription(r.getId()));
                r.setIngredients(mRecipesDao.getIngredients(r.getId()));
                r.setTag(mRecipesDao.getRecipesTag(r.getId()));
            }


            mAppExecutors.mainThread().execute(() -> callback.onSuccess(recipes));

        });
    }
}
