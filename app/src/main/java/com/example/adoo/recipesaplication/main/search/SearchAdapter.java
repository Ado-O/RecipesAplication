package com.example.adoo.recipesaplication.main.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.RecipesItemBinding;
import com.example.adoo.recipesaplication.databinding.SearchItemBinding;
import com.example.adoo.recipesaplication.main.recipes.RecipesViewHolder;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {

    private List<Recipe> mList=new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;


    public SearchAdapter(Context context, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(
                SearchItemBinding.inflate(
                        mInflater,
                        parent,
                        false
                ), mListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SearchViewHolder) holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItems(List list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
