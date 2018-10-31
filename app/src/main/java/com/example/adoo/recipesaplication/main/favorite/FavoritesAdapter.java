package com.example.adoo.recipesaplication.main.favorite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.BannerMainBinding;
import com.example.adoo.recipesaplication.databinding.FavoritesItemBinding;
import com.example.adoo.recipesaplication.main.MainActivity;
import com.example.adoo.recipesaplication.main.subscribe.BannerViewHolder;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter {

    private static final String TAG = FavoritesAdapter.class.getSimpleName();

    private final int HEADER = 1;
    private final int FAVORITE = 2;

    private final List mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;

    public FavoritesAdapter(Context context, RecyclerViewClickListener listener) {
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
                return FAVORITE;
            } else {
                return -1;
            }
        } else {
            if (mItems.get(position) instanceof Recipe) {
                return FAVORITE;
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
        if (viewType == FAVORITE) {
            return new FavoritesViewHolder(
                    FavoritesItemBinding.inflate(
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
        } else if (holder.getItemViewType() == FAVORITE) {
            ((FavoritesViewHolder) holder).setup((Recipe) mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

//    public void items(List list){
//        mItems.clear();
//        mItems.addAll(list);
//        notifyDataSetChanged();
//    }

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