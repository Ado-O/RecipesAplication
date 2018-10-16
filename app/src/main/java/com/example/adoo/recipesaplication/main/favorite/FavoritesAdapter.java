package com.example.adoo.recipesaplication.main.favorite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.FavoritesItemBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter {

    private static final String TAG = FavoritesAdapter.class.getSimpleName();

    private final List<Recipe> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;

    public FavoritesAdapter(Context context, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoritesViewHolder(
                FavoritesItemBinding.inflate(
                        mInflater,
                        parent,
                        false
                ), mListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FavoritesViewHolder) holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void items(List list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

}