package com.example.adoo.recipesaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.storage.ContentRepository;
import com.example.adoo.recipesaplication.data.storage.RecipesRepository;
import com.example.adoo.recipesaplication.data.storage.remote.content.ContentRemoteDataSource;
import com.example.adoo.recipesaplication.data.storage.remote.response.BaseResponse;

import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    private static final String TAG = RecipesActivity.class.getSimpleName();

    private ContentRepository mContentRepository;
    private RecipesRepository mRecipesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_act);

        Injection.provideContentRepository(this).setContent();

        mRecipesRepository = Injection.provideRecipesRepository(this);
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
                Log.e(TAG, recipes.get(0).getName());
            }

            @Override
            public void onError() {

            }
        });
    }
}
