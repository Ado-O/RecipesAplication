package com.example.adoo.recipesaplication.main.description;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.DescriptionActBinding;
import com.example.adoo.recipesaplication.databinding.DescriptionDirectionsItemBinding;
import com.example.adoo.recipesaplication.main.favorite.FavoritesViewModel;
import com.example.adoo.recipesaplication.util.ActivityUtils;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity  {

    private static final String TAG = DescriptionActivity.class.getSimpleName();

    private DescriptionActBinding mDescriptionActBinding;
    private Recipe r;

    public static void startActivity(Activity activity, Recipe recipe) {

        Intent intent = new Intent(activity, DescriptionActivity.class);
        intent.putExtra("recipe", recipe);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_act);

        mDescriptionActBinding = DataBindingUtil.setContentView(this, R.layout.description_act);

        r = getIntent().getExtras().getParcelable("recipe");

        setupDataFrag();
    }

    /************************
     * send data in fragment
     ***********************/
    public void setupDataFrag() {

        DescriptionFragment descriptionFragment = (DescriptionFragment) getSupportFragmentManager().findFragmentById(
                mDescriptionActBinding.fragDesc.getId());
        if (descriptionFragment == null) {
            descriptionFragment = DescriptionFragment.newInstance(getIntent().getParcelableExtra("recipe"));
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), descriptionFragment, R.id.frag_desc
            );
        }
    }

}
