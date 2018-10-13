package com.example.adoo.recipesaplication.main.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.SuggestedItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SuggestedAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflater;
    private List<Recipe> mList = new ArrayList<>();

    public SuggestedAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SuggestedViewHolder(SuggestedItemBinding.inflate(mInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SuggestedViewHolder)holder).setup(mList.get(position));
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
