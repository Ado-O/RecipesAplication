package com.spartanapp.recipe.chef.main.description;

import android.content.Intent;
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

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.databinding.DescriptionFragBinding;
import com.spartanapp.recipe.chef.main.favorite.FavoritesViewModel;
import com.spartanapp.recipe.chef.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class DescriptionFragment extends Fragment {

    private static final String TAG = DescriptionFragment.class.getSimpleName();

    private DescriptionFragBinding mBinding;
    private FavoritesViewModel mFavoritesViewModel;
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

        r = getActivity().getIntent().getExtras().getParcelable("recipe");
        mBinding.setRecipes(r);


        setupToolbar();
        setupShare();
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
            mFavoritesViewModel.getFavorite();
        });

    }


    public void setupShare() {

        mBinding.ivShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out app at: " +
                            "https://play.google.com/store/apps/dev?id=8964689875647814161");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });
    }

    /***************************
     * change color when scrolling toolbar
     **************************/
    public void setupToolbarColor() {
        mBinding.appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                if (r.isLike()){
                    mBinding.ivLike.setBackgroundResource(R.drawable.ic_like_hart_clik);
                }else{
                    mBinding.ivLike.setBackgroundResource(R.drawable.ic_favorite_border_black);
                }

                int colorBlack = Color.parseColor("#000000");
                mBinding.ivArrow.setColorFilter(colorBlack);
                mBinding.ivShare.setColorFilter(colorBlack);
                mBinding.tlbTitle.setText(r.getName());
            } else if (verticalOffset == 0) {
                if (r.isLike()){
                    mBinding.ivLike.setBackgroundResource(R.drawable.ic_like_hart_clik);
                }else{
                    mBinding.ivLike.setBackgroundResource(R.drawable.ic_like_hart);
                }

              int colorWhite = Color.parseColor("#FFFFFF");
                mBinding.ivArrow.setColorFilter(colorWhite);
                mBinding.ivShare.setColorFilter(colorWhite);
                mBinding.tlbTitle.setText(null);
            }
        });
    }

    /***************************
     * add data in main recycleview
     **************************/
    public void setupData() {

        List<Object> itemList = new ArrayList<>();
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
    public void setupLike() {

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
