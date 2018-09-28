package com.example.adoo.recipesaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.adoo.recipesaplication.data.storage.remote.content.ContentRemoteDataSource;
import com.example.adoo.recipesaplication.data.storage.remote.response.BaseResponse;

public class RecipesActivity extends AppCompatActivity {

    private static final String TAG = RecipesActivity.class.getSimpleName();

    private ContentRemoteDataSource mContentRemoteDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_act);
        Log.e(TAG, "hi");

        mContentRemoteDataSource = new ContentRemoteDataSource(this);
        mContentRemoteDataSource.getRecipe(new ContentRemoteDataSource.GetRecipeCallback() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                Log.e(TAG, "hi");
                Log.e(TAG, String.valueOf(baseResponse.getData().getRecipes().size()));
            }

            @Override
            public void onError() {

            }
        });
    }
}
