package com.example.adoo.recipesaplication.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.adoo.recipesaplication.Injection;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.storage.ContentRepository;
import com.example.adoo.recipesaplication.data.storage.FavoriteRepository;
import com.example.adoo.recipesaplication.data.storage.RecipesRepository;
import com.example.adoo.recipesaplication.data.storage.SuggestedRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ContentRepository mContentRepository;
    private RecipesRepository mRecipesRepository;
    private SuggestedRepository mSuggestedRepository;
    private FavoriteRepository mFavoriteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        Injection.provideContentRepository(this).setContent();
        mRecipesRepository = Injection.provideRecipesRepository(this);
        mSuggestedRepository = Injection.provideSuggestedRepository(this);
        mFavoriteRepository = Injection.provideFavoriteRepository(this);

        mRecipesRepository.getRecipes(new RecipesRepository.GetRecipesCallback() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                //   Log.e(TAG, recipes.get(0).getName());
//                Log.e(TAG, "directions "+recipes.get(0).getDirections().get(0).getDes());
//                Log.e(TAG, "ingredients "+recipes.get(0).getIngredients().get(0).getName());
//                Log.e(TAG, "tags "+recipes.get(0).getTag().get(0).getName());
            }

            @Override
            public void onError() {

            }
        });

        mRecipesRepository.getRecipesTag(1, new RecipesRepository.GetRecipesTagCallback() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                //Log.e(TAG, recipes.get(0).getName());
            }

            @Override
            public void onError() {

            }
        });

        mSuggestedRepository.getSuggestedRecipes(new SuggestedRepository.GetSuggestedRecipesCallback() {
            @Override
            public void onSuccess(List<Recipe> suggesteds) {
                //Log.e(TAG, suggesteds.get(0).getName());
            }

            @Override
            public void onError() {

            }
        });

        mSuggestedRepository.getSearchRecipes("Cereal with fruti jogurt", new SuggestedRepository.GetSearchCallback() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                //Log.e(TAG, recipes.get(0).getName());
            }

            @Override
            public void onError() {

            }
        });

        mFavoriteRepository.getFavoriteRecipes(new FavoriteRepository.GetFavoriteRecipesCallback() {
            @Override
            public void onSuccess(List<Recipe> suggesteds) {
                Log.e(TAG, suggesteds.get(0).getName());
            }

            @Override
            public void onError() {

            }
        });

        mFavoriteRepository.addFavorite(1);

    }
}
