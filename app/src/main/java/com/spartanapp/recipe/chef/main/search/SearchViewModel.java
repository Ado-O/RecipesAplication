package com.spartanapp.recipe.chef.main.search;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.data.storage.RecipesRepository;
import com.spartanapp.recipe.chef.data.storage.SearchRepository;
import com.spartanapp.recipe.chef.util.SingleLiveEvent;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private static final String TAG = SearchViewModel.class.getSimpleName();

    private SearchRepository mSearchRepository;
    private RecipesRepository mRecipesRepository;

    public final ObservableList<Recipe> mRecipes = new ObservableArrayList<>();
    public final ObservableList<SubRecipe> mSubRecipe = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);

    private final SingleLiveEvent<Recipe> mOpenRecipesEvent = new SingleLiveEvent<>();

    public SearchViewModel(@NonNull Application application,
                           SearchRepository searchRepository,
                           RecipesRepository recipesRepository) {
        super(application);

        mSearchRepository = searchRepository;
        mRecipesRepository = recipesRepository;
    }

    /***************
     *search recipes
     ***************/
    public void startRecipes(String title) {
        if (mRecipes.isEmpty()) {
            getRecipes();

        } else {
            getRecipesSearch(title);
        }
    }

    /********************************
     * get all recipes from recipes_table
     *******************************/
    public void getRecipes() {

        mRecipesRepository.getRecipes(new RecipesRepository.GetRecipesCallback() {
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

    /**********
     * subRecipe
     ***********/
    public void startSub(String title) {
        if (mSubRecipe.isEmpty()) {
            getSubRecipes();
        }else {
            getSubRecipesSearch(title);
        }
    }

    public void getSubRecipes() {

        mRecipesRepository.getSubRecipes(new RecipesRepository.GetSubRecipesCallback() {
            @Override
            public void onSuccess(List<SubRecipe> subRecipes) {
                mSubRecipe.clear();
                mSubRecipe.addAll(subRecipes);
                mError.set(mSubRecipe.isEmpty());
            }

            @Override
            public void onError() {

            }
        });
    }

    /********************************
     * add string and get recipes from recipes_table
     *******************************/
    public void getRecipesSearch(String title) {

        mSearchRepository.getSearchRecipes(title, new SearchRepository.GetSearchCallback() {
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

    /********************************
     * add string and get recipes from recipes_table
     *******************************/
    public void getSubRecipesSearch(String title) {

        mSearchRepository.getSubSearchRecipes(title, new SearchRepository.GetSubSearchCallback() {
            @Override
            public void onSuccess(List<SubRecipe> recipes) {
                mSubRecipe.clear();
                mSubRecipe.addAll(recipes);
                mError.set(mSubRecipe.isEmpty());
            }

            @Override
            public void onError() {

            }
        });
    }

    public SingleLiveEvent<Recipe> getOpenRecipeEvent() {
        return mOpenRecipesEvent;
    }

}
