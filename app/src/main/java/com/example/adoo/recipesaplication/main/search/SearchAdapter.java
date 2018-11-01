package com.example.adoo.recipesaplication.main.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.SubRecipe;
import com.example.adoo.recipesaplication.databinding.BannerMainBinding;
import com.example.adoo.recipesaplication.databinding.RecipesItemBinding;
import com.example.adoo.recipesaplication.databinding.SearchItemBinding;
import com.example.adoo.recipesaplication.databinding.SubRecipeBinding;
import com.example.adoo.recipesaplication.databinding.SubRecipeTextBinding;
import com.example.adoo.recipesaplication.databinding.SubSearchBinding;
import com.example.adoo.recipesaplication.main.MainActivity;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewHolder;
import com.example.adoo.recipesaplication.main.recipes.SubRecipeTextViewHolder;
import com.example.adoo.recipesaplication.main.subscribe.BannerViewHolder;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {

    private final int HEADER = 1;
    private final int SEARCH = 2;
    private final int SUBRECIPES = 3;

    private List mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;


    public SearchAdapter(Context context, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    /***************
     * add position
     **************/
    @Override
    public int getItemViewType(int position) {
        if (MainActivity.IS_SUB) {
            if (mItems.get(position) instanceof String) {
                return HEADER;

            } else if (mItems.get(position) instanceof Recipe) {
                return SEARCH;

            } else if (mItems.get(position) instanceof SubRecipe) {
                return SUBRECIPES;
            } else {
                return -1;
            }
        } else {
            if (mItems.get(position) instanceof Recipe) {
                return SEARCH;
            } else {
                return -1;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            return new BannerViewHolder(
                    BannerMainBinding.inflate(
                            mInflater,
                            parent,
                            false
                    )
            );
        }
        if (viewType == SEARCH) {
            return new SearchViewHolder(
                    SearchItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    ), mListener
            );
        }
        if (viewType == SUBRECIPES) {
            return new SubSearchViewHolder(
                    SubSearchBinding.inflate(
                            mInflater,
                            parent,
                            false
                    )
            );
        } else {
            throw new RuntimeException("The type has to be ONE");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == HEADER) {
            ((BannerViewHolder) holder).setup((String) mItems.get(position));

        } else if (holder.getItemViewType() == SEARCH) {
            ((SearchViewHolder) holder).setup((Recipe) mItems.get(position));

        } else if (holder.getItemViewType() == SUBRECIPES) {
            ((SubSearchViewHolder) holder).setup((SubRecipe) mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List items) {
        List oldItems = this.mItems;
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new Callback(oldItems, items));
        this.mItems.clear();
        this.mItems.addAll(items);
        result.dispatchUpdatesTo(this);
    }

    class Callback extends DiffUtil.Callback {

        private final List mOldItems;
        private final List mNewItems;

        Callback(List oldItems, List newItems) {
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

            } else if (mOldItems.get(oldItemPosition) instanceof String && mNewItems.get(newItemPosition) instanceof String) {
                return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));

            } else if (mOldItems.get(oldItemPosition) instanceof SubRecipe && mNewItems.get(newItemPosition) instanceof SubRecipe) {
                return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
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
