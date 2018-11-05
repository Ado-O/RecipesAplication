package com.spartanapp.recipe.chef.data.storage;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.data.Tag;
import com.spartanapp.recipe.chef.data.storage.local.recipes.RecipesLocalDataSource;

import java.util.List;

public class RecipesRepository {
        private static final String TAG = RecipesRepository.class.getSimpleName();

    private static RecipesRepository sInstance = null;

    private final RecipesLocalDataSource mRecipesLocalDataSource;

    public RecipesRepository(RecipesLocalDataSource recipesLocalDataSource) {
        mRecipesLocalDataSource = recipesLocalDataSource;
    }

    public static RecipesRepository getInstance(RecipesLocalDataSource recipesLocalDataSource) {
        if (sInstance == null) {
            sInstance = new RecipesRepository(recipesLocalDataSource);
        }
        return sInstance;
    }

    /**
     * get Recipes
     */
    public void getRecipes(GetRecipesCallback callback) {
        mRecipesLocalDataSource.getRecipes(callback);
    }

    /**
     * get getSubRecipes
     */
    public void getSubRecipes(GetSubRecipesCallback callback) {
        mRecipesLocalDataSource.getSubRecipes(callback);
    }

    /**
     * get Tag
     */
    public void getTag(GetTagCallback callback) {
        mRecipesLocalDataSource.getTag(callback);
    }

    /**
     * get RecipesTag
     */
    public void getRecipesTag(long filterTag, GetRecipesTagCallback callback) {
        mRecipesLocalDataSource.getRecipesTag(filterTag, callback);
    }

    /************
     * Callback
     ***********/
    public interface GetRecipesCallback {
        void onSuccess(List<Recipe> recipes);

        void onError();
    }

    public interface GetSubRecipesCallback {
        void onSuccess(List<SubRecipe> subRecipes);

        void onError();
    }

    public interface GetTagCallback{
        void onSuccess(List<Tag> tags);

        void onError();
    }

    public interface GetRecipesTagCallback {
        void onSuccess(List<Recipe> recipes);

        void onError();
    }

}
