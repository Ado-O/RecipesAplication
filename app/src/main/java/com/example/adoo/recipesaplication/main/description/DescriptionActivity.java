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

import com.bumptech.glide.Glide;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.DescriptionActBinding;
import com.example.adoo.recipesaplication.databinding.DescriptionDirectionsItemBinding;
import com.example.adoo.recipesaplication.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {

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

        setSupportActionBar(mDescriptionActBinding.tlb);

        setupBackArrow();
        setupToolbarColor();
        setupImg();
        setupDataFrag();
    }

    /**
     * OnClickListener for the toolbar back button
     */
    private void setupBackArrow() {
        mDescriptionActBinding.ivArrow.setOnClickListener(v -> onBackPressed());
    }

    /**
     * change color when scrolling toolbar
     */
    public void setupToolbarColor() {
        mDescriptionActBinding.appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                int colorBlack = Color.parseColor("#000000");
                mDescriptionActBinding.ivArrow.setColorFilter(colorBlack);
                mDescriptionActBinding.ivLike.setColorFilter(colorBlack);
                mDescriptionActBinding.ivShare.setColorFilter(colorBlack);
            } else if (verticalOffset == 0) {
                int colorWhite = Color.parseColor("#FFFFFF");
                mDescriptionActBinding.ivArrow.setColorFilter(colorWhite);
                mDescriptionActBinding.ivLike.setColorFilter(colorWhite);
                mDescriptionActBinding.ivShare.setColorFilter(colorWhite);
                //TODO add text title
            }
        });
    }

    /**
     * add data in description_act
     */
    public void setupImg() {
        Glide.with(this)
                .load(r.getImage())
                .into(mDescriptionActBinding.ivRecipes);
    }

    /**
     * send data in fragment
     */
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
