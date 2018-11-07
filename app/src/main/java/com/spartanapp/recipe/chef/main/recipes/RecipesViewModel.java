package com.spartanapp.recipe.chef.main.recipes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.data.Tag;
import com.spartanapp.recipe.chef.data.storage.RecipesRepository;
import com.spartanapp.recipe.chef.util.SingleLiveEvent;

import java.util.List;

public class RecipesViewModel extends AndroidViewModel {

    private static final String TAG = RecipesViewModel.class.getSimpleName();

    private RecipesRepository mRecipesRepository;

    public final ObservableList<Recipe> mRecipes = new ObservableArrayList<>();
    public final ObservableList<SubRecipe> mSubRecipes = new ObservableArrayList<>();
    public final ObservableList<Tag> mTags = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);

    private final SingleLiveEvent<Recipe> mOpenRecipesEvent = new SingleLiveEvent<>();

    public RecipesViewModel(@NonNull Application application,
                            RecipesRepository recipesRepository) {
        super(application);

        mRecipesRepository = recipesRepository;
    }

    /*************************
     * get all recipes from recipes_table
     * when is add data in filterTag we resive that data
     **************************/
    public void start(long filterTag) {
        if (mRecipes.isEmpty()) {
            getRecipes();
        } else {
            getFilterItem(filterTag);
        }
    }

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
    public void startSub() {
        if (mSubRecipes.isEmpty()) {
            getSubRecipes();
        }
    }

    public void getSubRecipes() {

        mRecipesRepository.getSubRecipes(new RecipesRepository.GetSubRecipesCallback() {
            @Override
            public void onSuccess(List<SubRecipe> subRecipes) {
                mSubRecipes.clear();
                mSubRecipes.addAll(subRecipes);
                mError.set(mSubRecipes.isEmpty());
            }

            @Override
            public void onError() {

            }
        });
    }

    /***************************
     * with selected id get all from recipes_table but
     **************************/
    public void startTag() {
        if (mTags.isEmpty()) {
            getTags();
        }
    }

    public void getFilterItem(long filterTag) {
        mRecipesRepository.getRecipesTag(filterTag, new RecipesRepository.GetRecipesTagCallback() {
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


    /*************************
     * get all from tag_table
     ************************/
    public void getTags() {
        mRecipesRepository.getTag(new RecipesRepository.GetTagCallback() {
            @Override
            public void onSuccess(List<Tag> tags) {
                mTags.clear();
                mTags.addAll(tags);
                mError.set(mTags.isEmpty());
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