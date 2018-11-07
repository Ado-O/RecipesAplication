package com.spartanapp.recipe.chef.data.storage.local.content;

import com.spartanapp.recipe.chef.data.storage.converter.RemoteToLocal;
import com.spartanapp.recipe.chef.data.storage.remote.response.BaseResponse;
import com.spartanapp.recipe.chef.data.storage.remote.response.RecipeResponse;
import com.spartanapp.recipe.chef.data.storage.remote.response.TagsResponse;
import com.spartanapp.recipe.chef.util.AppExecutors;

import java.util.List;

public class ContentLocalDataSource {

    private static final String TAG = ContentLocalDataSource.class.getSimpleName();

    private static ContentLocalDataSource sInstance = null;

    private ContentDao mContentDao;
    private AppExecutors mAppExecutors;

    public ContentLocalDataSource(ContentDao contentDao, AppExecutors appExecutors) {
        mContentDao = contentDao;
        mAppExecutors = appExecutors;
    }

    public static ContentLocalDataSource getInstance(ContentDao contentDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new ContentLocalDataSource(contentDao, appExecutors);
        }
        return sInstance;
    }

    public void setContent(BaseResponse baseResponse) {

        mAppExecutors.diskIO().execute(() -> {

            List<RecipeResponse> recipeResponse = baseResponse.getData().getRecipes();
            List<TagsResponse> tagsResponses = baseResponse.getData().getTags();

            /**
             * clear
             */
            mContentDao.clearRecipe();
            mContentDao.clearSubRecipe();
            mContentDao.clearDirections();
           mContentDao.clearIngredients();

            /**
             * add recipeResponses -> recipes
             */
            mContentDao.insertRecipes(RemoteToLocal.recipesConverter(recipeResponse));
            mContentDao.insertSubRecipes(RemoteToLocal.subRecipesConverter(recipeResponse));
            mContentDao.insertIngredients(RemoteToLocal.ingredientsConverter(recipeResponse));
            mContentDao.insertDirections(RemoteToLocal.directionsConverter(recipeResponse));

            /**
             * add recipeResponses -> recipes_tags
             */
            for (RecipeResponse r : recipeResponse) {

                mContentDao.clearTags(r.getId());
                mContentDao.insertRecipesTag(RemoteToLocal.recipesTagsConvertor(
                        r.getId(),
                        r.getTags()
                ));
            }

            /**
             * add TagResponse -> recipes_tag
             */
            mContentDao.insertTag(RemoteToLocal.tagConverter(tagsResponses));

        });
    }


}