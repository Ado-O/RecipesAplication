package com.spartanapp.recipe.chef.main.recipes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.databinding.RecipesItemBinding;
import com.spartanapp.recipe.chef.databinding.SubRecipeBinding;
import com.spartanapp.recipe.chef.databinding.SubRecipeTextBinding;
import com.spartanapp.recipe.chef.RecipeApp;
import com.spartanapp.recipe.chef.main.MainActivity;
import com.spartanapp.recipe.chef.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter {

    private final List mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;
    private Context mContext;


    public RecipesAdapter(Context context, RecyclerViewClickListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecipesViewHolder(
                    RecipesItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    ), mListener
            );
        }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((RecipesViewHolder) holder).setup((Recipe) mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List list) {
        List oldItems = this.mItems;
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new Callback(oldItems, list));
        this.mItems.clear();
        this.mItems.addAll(list);
        result.dispatchUpdatesTo(this);
    }

    static class Callback extends DiffUtil.Callback {

        private final List mOldItems;
        private final List mNewItems;

        Callback(List<Recipe> oldItems, List<Recipe> newItems) {
            mOldItems = oldItems;
            mNewItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return mOldItems.size();
        }

        @Override
        public int getNewListSize() {
            return mNewItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            if (mOldItems.get(oldItemPosition) instanceof Recipe && mNewItems.get(newItemPosition) instanceof Recipe) {
                return ((Recipe) mOldItems.get(oldItemPosition)).getId() == ((Recipe) mNewItems.get(newItemPosition)).getId();

            } else {
                return false;
            }
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
        }
    }
}

