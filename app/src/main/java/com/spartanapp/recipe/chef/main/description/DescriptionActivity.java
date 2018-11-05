package com.spartanapp.recipe.chef.main.description;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.databinding.DescriptionActBinding;
import com.spartanapp.recipe.chef.util.ActivityUtils;

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
