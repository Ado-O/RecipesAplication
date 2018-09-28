package com.example.adoo.recipesaplication;

import android.content.Context;

import com.example.adoo.recipesaplication.data.storage.ContentRepository;
import com.example.adoo.recipesaplication.data.storage.RecipesRepository;
import com.example.adoo.recipesaplication.data.storage.local.AppDatabase;
import com.example.adoo.recipesaplication.data.storage.local.content.ContentLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.local.recipes.RecipesLocalDataSource;
import com.example.adoo.recipesaplication.data.storage.remote.content.ContentRemoteDataSource;
import com.example.adoo.recipesaplication.util.AppExecutors;

public class Injection {
    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext());
    }

    public static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    public static ContentRemoteDataSource provideContentRemoteDataSource(Context context) {
        return ContentRemoteDataSource.getInstance(context);
    }

    public static ContentLocalDataSource provideContentLocalDataSource(Context context) {
        return ContentLocalDataSource.getInstance(
                provideAppDatabase(context.getApplicationContext()).getContentDao(),
                provideAppExecutors()
        );
    }

    public static RecipesLocalDataSource provideRecipesLocalDataSource(Context context) {
        return RecipesLocalDataSource.getInstance(
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getRecipesDao()
        );
    }

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
}
