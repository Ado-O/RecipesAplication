package com.spartanapp.recipe.chef.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;

import com.spartanapp.recipe.chef.Injection;
import com.spartanapp.recipe.chef.data.storage.FavoriteRepository;
import com.spartanapp.recipe.chef.data.storage.RecipesRepository;
import com.spartanapp.recipe.chef.data.storage.SearchRepository;
import com.spartanapp.recipe.chef.main.favorite.FavoritesViewModel;
import com.spartanapp.recipe.chef.main.recipes.RecipesViewModel;
import com.spartanapp.recipe.chef.main.search.SearchViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final RecipesRepository mRecipesRepository;
    private final SearchRepository mSearchRepository;
    private final FavoriteRepository mFavoriteRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            application,
                            Injection.provideRecipesRepository(application),
                            Injection.provideSearchRepository(application),
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
                             SearchRepository searchRepository,
                             FavoriteRepository favoriteRepository) {
        mApplication = application;

        mRecipesRepository = recipesRepository;
        mSearchRepository = searchRepository;
        mFavoriteRepository = favoriteRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipesViewModel.class)) {
            return (T) new RecipesViewModel(mApplication, mRecipesRepository);
        }else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(mApplication, mSearchRepository, mRecipesRepository);
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