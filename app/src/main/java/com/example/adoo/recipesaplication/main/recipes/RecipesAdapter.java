package com.example.adoo.recipesaplication.main.recipes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.RecipesItemBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter {

    private final List<Recipe> mList=new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;


    public RecipesAdapter(Context context, RecyclerViewClickListener listener) {
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
        ((RecipesViewHolder) holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItems(List list) {
            List<Recipe> oldItems = this.mList;
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new Callback(oldItems, list));
            this.mList.clear();
            this.mList.addAll(list);
            result.dispatchUpdatesTo(this);
        }
    }

    class Callback extends DiffUtil.Callback {

        private final List<Recipe> mOldItems;
        private final List<Recipe> mNewItems;

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
            return mOldItems.get(oldItemPosition).getId() == mNewItems.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
        }
    }
