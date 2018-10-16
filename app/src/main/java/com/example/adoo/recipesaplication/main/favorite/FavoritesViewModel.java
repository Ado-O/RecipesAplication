package com.example.adoo.recipesaplication.main.favorite;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.storage.FavoriteRepository;
import com.example.adoo.recipesaplication.util.SingleLiveEvent;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private static final String TAG = FavoritesViewModel.class.getSimpleName();

    private FavoriteRepository mFavoriteRepository;

    public final ObservableList<Recipe> mRecipes = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);

    private final SingleLiveEvent<Recipe> mOpenRecipesEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Recipe> mFavoritesEvent = new SingleLiveEvent<>();

    public FavoritesViewModel(@NonNull Application application,
                              FavoriteRepository favoriteRepository) {
        super(application);

        mFavoriteRepository = favoriteRepository;
    }

    /********************
     *add favorite recipes
     *********************/
    public void addFavorite(long respiseId) {
        mFavoriteRepository.addFavorite(respiseId);
    }


    /****************************
     *add all from favorite_table
     ***************************/
    public void start() {
        if (mRecipes.isEmpty()) {
            getFavorite();
        }
    }

    public void getFavorite() {

      mFavoriteRepository.getFavoriteRecipes(new FavoriteRepository.GetFavoriteRecipesCallback() {
          @Override
          public void onSuccess(List<Recipe> recipes) {
              mRecipes.clear();
              mRecipes.addAll(recipes);
              mError.set(mRecipes.isEmpty());
          }

          @Override
          public void onError() {

          }
      });
    }

    /*****************
     *delete favorite
     ****************/
    public void deleteFavorite(long id){
        mFavoriteRepository.getRemoveFavorite(id);
    }

    public SingleLiveEvent<Recipe> getOpenRecipeEvent() { return mOpenRecipesEvent; }
    public SingleLiveEvent<Recipe> getOpenFavoritesEvent(){ return mFavoritesEvent; }
}