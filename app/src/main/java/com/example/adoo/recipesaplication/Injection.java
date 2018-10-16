package com.example.adoo.recipesaplication;

import android.content.Context;

import com.example.adoo.recipesaplication.data.Favorite;
import com.example.adoo.recipesaplication.data.storage.ContentRepository;
import com.example.adoo.recipesaplication.data.storage.FavoriteRepository;
import com.example.adoo.recipesaplication.data.storage.RecipesRepository;
import com.example.adoo.recipesaplication.data.storage.SuggestedRepository;
import com.example.adoo.recipesaplication.data.storage.local.AppDatabase;
import com.example.adoo.recipesaplication.data.storage.local.content.ContentLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.local.favorite.FavoriteLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.local.recipes.RecipesLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.local.suggested.SuggestedLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.remote.content.ContentRemoteDataSource;
import com.example.adoo.recipesaplication.util.AppExecutors;

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
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getRecipesDao(),
                provideAppDatabase(context.getApplicationContext()).getFavoriteDao()
        );
    }

    public static SuggestedLocalDataSource provideSuggestedLocalDataSource(Context context){
        return SuggestedLocalDataSource.getInstance(
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getSuggestedDao()
        );
    }

    public static FavoriteLocalDataSource provideFavoriteLocalDataSource(Context context){
        return FavoriteLocalDataSource.getInstance(
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getFavoriteDao()
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

    public static SuggestedRepository provideSuggestedRepository(Context context){
        return SuggestedRepository.getInstance(
                provideSuggestedLocalDataSource(context)
        );
    }

    public static FavoriteRepository provideFavoriteRepository(Context context){
        return FavoriteRepository.getInstance(
                provideFavoriteLocalDataSource(context)
        );
    }
}
