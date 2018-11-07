package com.spartanapp.recipe.chef;

import android.content.Context;

import com.spartanapp.recipe.chef.data.storage.ContentRepository;
import com.spartanapp.recipe.chef.data.storage.FavoriteRepository;
import com.spartanapp.recipe.chef.data.storage.RecipesRepository;
import com.spartanapp.recipe.chef.data.storage.SearchRepository;
import com.spartanapp.recipe.chef.data.storage.local.AppDatabase;
import com.spartanapp.recipe.chef.data.storage.local.content.ContentLocalDataSource;
import com.spartanapp.recipe.chef.data.storage.local.favorite.FavoriteLocalDataSource;
import com.spartanapp.recipe.chef.data.storage.local.recipes.RecipesLocalDataSource;
import com.spartanapp.recipe.chef.data.storage.local.search.SearchLocalDataSource;
import com.spartanapp.recipe.chef.data.storage.remote.content.ContentRemoteDataSource;
import com.spartanapp.recipe.chef.util.AppExecutors;

public class Injection {

    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext());
    }

    public static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    /********
     * remote
     *********/
    public static ContentRemoteDataSource provideContentRemoteDataSource(Context context) {
        return ContentRemoteDataSource.getInstance(context);
    }

    /*******
     * local
     *******/
    public static ContentLocalDataSource provideContentLocalDataSource(Context context) {
        return ContentLocalDataSource.getInstance(
                provideAppDatabase(context.getApplicationContext()).getContentDao(),
                provideAppExecutors()
        );
    }

    public static RecipesLocalDataSource provideRecipesLocalDataSource(Context context) {
        return RecipesLocalDataSource.getInstance(
                context,
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getRecipesDao(),
                provideAppDatabase(context.getApplicationContext()).getFavoriteDao()
        );
    }

    public static SearchLocalDataSource provideSearchLocalDataSource(Context context){
        return SearchLocalDataSource.getInstance(
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getSuggestedDao(),
                provideAppDatabase(context.getApplicationContext()).getRecipesDao(),
                provideAppDatabase(context.getApplicationContext()).getFavoriteDao(),
                context
                );
    }

    public static FavoriteLocalDataSource provideFavoriteLocalDataSource(Context context){
        return FavoriteLocalDataSource.getInstance(
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getFavoriteDao(),
                provideAppDatabase(context.getApplicationContext()).getRecipesDao()
        );
    }

    /*************
     * repository
     ************/
    public static ContentRepository provideContentRepository(Context context) {
        return ContentRepository.getInstance(
                context,
                provideContentRemoteDataSource(context),
                provideContentLocalDataSource(context),
                provideRecipesLocalDataSource(context),
                provideAppExecutors()
        );
    }

    public static RecipesRepository provideRecipesRepository(Context context) {
        return RecipesRepository.getInstance(
                provideRecipesLocalDataSource(context)
        );
    }

    public static SearchRepository provideSearchRepository(Context context){
        return SearchRepository.getInstance(
                provideSearchLocalDataSource(context)
        );
    }

    public static FavoriteRepository provideFavoriteRepository(Context context){
        return FavoriteRepository.getInstance(
                provideFavoriteLocalDataSource(context)
        );
    }
}
