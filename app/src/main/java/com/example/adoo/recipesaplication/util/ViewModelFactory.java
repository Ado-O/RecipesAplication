package com.example.adoo.recipesaplication.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;

import com.example.adoo.recipesaplication.Injection;
import com.example.adoo.recipesaplication.data.storage.FavoriteRepository;
import com.example.adoo.recipesaplication.data.storage.RecipesRepository;
import com.example.adoo.recipesaplication.data.storage.SuggestedRepository;
import com.example.adoo.recipesaplication.main.favorite.FavoritesViewModel;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.main.search.SearchViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final RecipesRepository mRecipesRepository;
    private final SuggestedRepository mSuggestedRepository;
    private final FavoriteRepository mFavoriteRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            application,
                            Injection.provideRecipesRepository(application),
                            Injection.provideSuggestedRepository(application),
                            Injection.provideFavoriteRepository(application)
                    );
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application,
                             RecipesRepository recipesRepository,
                             SuggestedRepository suggestedRepository,
                             FavoriteRepository favoriteRepository) {
        mApplication = application;

        mRecipesRepository = recipesRepository;
        mSuggestedRepository = suggestedRepository;
        mFavoriteRepository = favoriteRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipesViewModel.class)) {
            return (T) new RecipesViewModel(mApplication, mRecipesRepository);
        }else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(mApplication, mSuggestedRepository, mRecipesRepository);
        }else if (modelClass.isAssignableFrom(FavoritesViewModel.class)){
            return (T) new FavoritesViewModel(mApplication, mFavoriteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    public static <T extends AndroidViewModel> T obtainViewModel(FragmentActivity activity, Class<T> modelClass) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        T viewModel =
                ViewModelProviders.of(activity, factory).get(modelClass);

        return viewModel;
    }
}