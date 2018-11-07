package com.spartanapp.recipe.chef.main.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.spartanapp.recipe.chef.data.Recipe;
import com.spartanapp.recipe.chef.data.SubRecipe;
import com.spartanapp.recipe.chef.databinding.BannerMainBinding;
import com.spartanapp.recipe.chef.databinding.SearchItemBinding;
import com.spartanapp.recipe.chef.databinding.SubSearchBinding;
import com.spartanapp.recipe.chef.RecipeApp;
import com.spartanapp.recipe.chef.main.MainActivity;
import com.spartanapp.recipe.chef.main.subscribe.BannerViewHolder;
import com.spartanapp.recipe.chef.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {

    private final int HEADER = 1;
    private final int SEARCH = 2;

    private List mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;
    private Context mContext;

    public SearchAdapter(Context context, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
        mContext = context;
    }

    /***************
     * add position
     **************/
    @Override
    public int getItemViewType(int position) {
        SharedPreferences sub = mContext.getSharedPreferences("is_sub", 0);
        boolean isSub = sub.getBoolean("free", false);

        if (!isSub) {
            if (mItems.get(position) instanceof String) {
                return HEADER;

            } else if (mItems.get(position) instanceof Recipe) {
                return SEARCH;

            }else {
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
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

//    public void setItems(List items) {
//        List oldItems = this.mItems;
//        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new Callback(oldItems, items));
//        this.mItems.clear();
//        this.mItems.addAll(items);
//        result.dispatchUpdatesTo(this);
//    }
//
//    class Callback extends DiffUtil.Callback {
//
//        private final List mOldItems;
//        private final List mNewItems;
//
//        Callback(List oldItems, List newItems) {
//            mOldItems = oldItems;
//            mNewItems = newItems;
//        }
//
//        @Override
//        public int getOldListSize() {
//            return mOldItems.size();
//        }
//
//        @Override
//        public int getNewListSize() {
//            return mNewItems.size();
//        }
//
//        @Override
//        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//
//            if (mOldItems.get(oldItemPosition) instanceof Recipe && mNewItems.get(newItemPosition) instanceof Recipe) {
//                return ((Recipe) mOldItems.get(oldItemPosition)).getId() == ((Recipe) mNewItems.get(newItemPosition)).getId();
//
//            } else if (mOldItems.get(oldItemPosition) instanceof String && mNewItems.get(newItemPosition) instanceof String) {
//                return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
//
//            } else if (mOldItems.get(oldItemPosition) instanceof SubRecipe && mNewItems.get(newItemPosition) instanceof SubRecipe) {
//                return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
//            } else {
//                return false;
//            }
//        }
//
//        @Override
//        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//            return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
//        }
//    }

}
