package com.example.adoo.recipesaplication.data.storage.local.content;

import com.example.adoo.recipesaplication.data.storage.converter.RemoteToLocal;
import com.example.adoo.recipesaplication.data.storage.remote.response.BaseResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.RecipeResponse;
import com.example.adoo.recipesaplication.data.storage.remote.response.TagsResponse;
import com.example.adoo.recipesaplication.util.AppExecutors;

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
             * add recipeResponses -> recipes
             */
            mContentDao.insertRecipes(RemoteToLocal.recipesConverter(recipeResponse));

            for (RecipeResponse r : recipeResponse) {


                mContentDao.insertIngredients(RemoteToLocal.ingredientsConverter(r.getIngredients()));
                mContentDao.insertDirections(RemoteToLocal.directionsConverter(r.getDirections()));
                mContentDao.clearTags(r.getId());
                mContentDao.insertRecipesTag(RemoteToLocal.recipesTagsConvertor(
                        r.getId(),
                        r.getTags()
                ));
            }

            /**
             * add TagResponse -> tag
             */
            mContentDao.insertTag(RemoteToLocal.tagConverter(tagsResponses));

            /**
             * add baseResponse -> suggested
             */
            mContentDao.insertSuggested(RemoteToLocal.suggestedConverter(baseResponse));

        });
    }



}
