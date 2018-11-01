package com.example.adoo.recipesaplication.main.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.SubRecipe;
import com.example.adoo.recipesaplication.databinding.SearchFragBinding;
import com.example.adoo.recipesaplication.main.favorite.FavoritesViewModel;
import com.example.adoo.recipesaplication.main.recipes.RecipesAdapter;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.main.subscribe.SubscribeActivity;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.adoo.recipesaplication.main.MainActivity.hideKeyboard;

public class SearchFragment extends Fragment implements RecyclerViewClickListener {

    private static final String TAG = SearchFragment.class.getSimpleName();

    private SearchFragBinding mBinding;
    private SearchViewModel mSearchViewModel;
    private SearchAdapter mSearchAdapter;
    private String title = "";

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SearchFragBinding.inflate(inflater, container, false);

        mSearchViewModel = ViewModelFactory.obtainViewModel(getActivity(), SearchViewModel.class);
        mSearchViewModel.startRecipes(title);
        mBinding.setViewModel(mSearchViewModel);

        mSearchViewModel.startSub(title);
        mBinding.setSubRecipes(mSearchViewModel);

        setupRv();
        setupClear();
        setupEditText();

        return mBinding.getRoot();
    }


    /*************************
     * add layoutManger in RecycleViewAdapter
     **********************/
    public void setupRv() {

        mSearchAdapter = new SearchAdapter(getActivity(), SearchFragment.this);
        mBinding.rvEditSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvEditSearch.setAdapter(mSearchAdapter);

    }

    /**************************
     * onCLick right icon in edit text
     *************************/
    public void setupClear() {

        mBinding.etSimple.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= mBinding.etSimple.getRight() - mBinding.etSimple.getTotalPaddingRight()) {

                        mBinding.etSimple.setText(null);
                        hideKeyboard(getActivity());
                        mBinding.setErrorBoolean(false);
                        mBinding.etSimple.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_search_black, 0, 0, 0);

                        return true;
                    }
                }
                return false;
            }
        });
    }

    /***********
     * edit Text
     ***********/
    public void setupEditText() {

        //editText - TextWatcher
        mBinding.etSimple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSearchViewModel.getRecipesSearch(s.toString());
                mSearchViewModel.getSubRecipesSearch(s.toString());


                if (!s.toString().isEmpty()) {
                    mBinding.etSimple.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_search_black, 0, R.drawable.ic_close_black, 0);
                    mBinding.setErrorBoolean(true);
                } else {
                    mBinding.etSimple.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_search_black, 0, R.drawable.ic_close_black, 0);
                    mBinding.setErrorBoolean(false);
                }
            }
        });
    }


    /************************
     * RecycleViewClickListener
     ***********************/
    @Override
    public void recyclerViewListClicked(View v, Recipe recipe) {

        //send listener data in DescriptionLayout
        mSearchViewModel.getOpenRecipeEvent().setValue(recipe);
    }

    @Override
    public void favoritesCLickListener(View v, Recipe recipe) {
    }

}
