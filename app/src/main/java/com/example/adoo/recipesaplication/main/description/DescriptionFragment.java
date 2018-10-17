package com.example.adoo.recipesaplication.main.description;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.DescriptionDirectionsItemBinding;
import com.example.adoo.recipesaplication.databinding.DescriptionFragBinding;
import com.example.adoo.recipesaplication.main.favorite.FavoritesViewModel;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFragment extends Fragment {

    private static final String TAG = DescriptionFragment.class.getSimpleName();

    private DescriptionFragBinding mBinding;
    private FavoritesViewModel mFavoritesViewModel;
    private RecipesViewModel mRecipesViewModel;
    private Recipe r;

    public static DescriptionFragment newInstance(Recipe recipe) {

        DescriptionFragment fragment = new DescriptionFragment();
        Bundle b = new Bundle();
        b.putParcelable("recipe", recipe);

        fragment.setArguments(b);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DescriptionFragBinding.inflate(inflater, container, false);

        mFavoritesViewModel = ViewModelFactory.obtainViewModel(getActivity(), FavoritesViewModel.class);
        mRecipesViewModel = ViewModelFactory.obtainViewModel(getActivity(), RecipesViewModel.class);

        r = getActivity().getIntent().getExtras().getParcelable("recipe");

        mBinding.setRecipes(r);

        setupToolbar();
        setupImg();
        setupToolbarColor();
        setupData();
        setupLike();


        return mBinding.getRoot();
    }

    /**********
     * toolbar
     *********/
    public void setupToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mBinding.tlb);

        //back button
        mBinding.ivArrow.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }

    /********
     * image
     ********/
    public void setupImg() {
        Glide.with(this)
                .load(r.getImage())
                .into(mBinding.ivRecipes);
    }

    /***************************
     * change color when scrolling toolbar
     **************************/
    public void setupToolbarColor() {
        mBinding.appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                int colorBlack = Color.parseColor("#000000");
                mBinding.ivArrow.setColorFilter(colorBlack);
                mBinding.ivLike.setColorFilter(colorBlack);
                mBinding.ivShare.setColorFilter(colorBlack);
            } else if (verticalOffset == 0) {
                int colorWhite = Color.parseColor("#FFFFFF");
                mBinding.ivArrow.setColorFilter(colorWhite);
                mBinding.ivLike.setColorFilter(colorWhite);
                mBinding.ivShare.setColorFilter(colorWhite);
            }
        });
    }

    /***************************
     * add data in main recycleview
     **************************/
    public void setupData() {

        List itemList = new ArrayList<>();
        itemList.add(r);
        itemList.add("INGREDIENTS");
        itemList.addAll(r.getIngredients());
        itemList.add("DIRECTIONS");
        itemList.addAll(r.getDirections());

        mBinding.setItems(itemList);

        DescriptionAdapter adapter = new DescriptionAdapter(getActivity());
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvList.smoothScrollToPosition(0);
        mBinding.rvList.setAdapter(adapter);
    }

    /*************
     * like onClick
     *************/
    public void setupLike(){

        mBinding.ivLike.setOnClickListener(v -> {
            if (!r.isLike()) {
                mBinding.ivLike.setBackgroundResource(R.drawable.ic_like_hart_clik);
                r.setLike(true);
                mFavoritesViewModel.addFavorite(r.getId());
            } else {
                mBinding.ivLike.setBackgroundResource(R.drawable.ic_like_hart);
                r.setLike(false);
                mFavoritesViewModel.deleteFavorite(r.getId());
            }
        });
    }

}
