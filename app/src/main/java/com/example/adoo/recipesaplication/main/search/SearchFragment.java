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
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.SearchFragBinding;
import com.example.adoo.recipesaplication.main.favorite.FavoritesViewModel;
import com.example.adoo.recipesaplication.main.recipes.RecipesAdapter;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewModel;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;
import com.example.adoo.recipesaplication.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements RecyclerViewClickListener {

    private static final String TAG = SearchFragment.class.getSimpleName();

    private SearchFragBinding mBinding;
    private SearchViewModel mSearchViewModel;
    private SearchAdapter mSearchAdapter;
    private FavoritesViewModel mFavoritesViewModel;
    private String title = "";

    public static SearchFragment newInstance(int id) {
        SearchFragment searchfragment = new SearchFragment();
        Bundle b = new Bundle();
        b.putInt("id", id);

        searchfragment.setArguments(b);

        return searchfragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = SearchFragBinding.inflate(inflater, container, false);

        mFavoritesViewModel = ViewModelFactory.obtainViewModel(getActivity(), FavoritesViewModel.class);

        mSearchViewModel = ViewModelFactory.obtainViewModel(getActivity(), SearchViewModel.class);
        mSearchViewModel.startRecipes(title);
        mBinding.setViewModel(mSearchViewModel);

        setupRv();
        setupBackArrow();
        setupEditText();

        return mBinding.getRoot();
    }

    public void setupRv() {

        mSearchAdapter = new SearchAdapter(getActivity(), SearchFragment.this);
        mBinding.rvEditSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvEditSearch.setAdapter(mSearchAdapter);

    }
    /**
     * OnClickListener for the toolbar back button
     */
    private void setupBackArrow() {
        mBinding.ivArrow.setOnClickListener(v -> getActivity().onBackPressed());
    }

    /**
     * edit Text
     */
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

                if (mSearchViewModel.mRecipes.size() == 0){
                    mBinding.rvEditSearch.setVisibility(View.GONE);
                    mBinding.ivNoResultFind.setImageResource(R.drawable.ic_local_dining_color);
                    mBinding.tvNoResultFind.setText("SORRY, NO RESULT FIND");
                    mBinding.tvNoResultFindDes.setText("Use the search bar, or click on the tags below to find the recipe. Enjoy!");
                }else{
                    mBinding.rvEditSearch.setVisibility(View.VISIBLE);
                    mBinding.ivNoResultFind.setImageResource(0);
                    mBinding.tvNoResultFind.setText("");
                    mBinding.tvNoResultFindDes.setText("");
                }
            }
        });

    }

    @Override
    public void recyclerViewListClicked(View v, Recipe recipe) {
        mSearchViewModel.getOpenRecipeEvent().setValue(recipe);
    }

    @Override
    public void favoritesCLickListener(View view, Recipe recipe) {

        if (!recipe.isLike()){
            view.setBackgroundResource(R.drawable.ic_like_hart_clik);
            recipe.setLike(true);
            mFavoritesViewModel.addFavorite(recipe.getId());
        }else{
            view.setBackgroundResource(R.drawable.ic_like_hart);
            recipe.setLike(false);
            mFavoritesViewModel.deleteFavorite(recipe.getId());
        }
    }


}
