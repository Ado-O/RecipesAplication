package com.example.adoo.recipesaplication.main.description;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Ingredients;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.DescriptionDetailsItemBinding;
import com.example.adoo.recipesaplication.databinding.DescriptionDirectionsItemBinding;
import com.example.adoo.recipesaplication.databinding.DescriptionHeaderItemBinding;
import com.example.adoo.recipesaplication.databinding.DescriptionIngredientsItemBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class DescriptionAdapter extends RecyclerView.Adapter {

    private final int DETAILS = 1;
    private final int HEADER = 2;
    private final int INGREDIENTS = 3;
    private final int DIRECTIONS = 4;

    private LayoutInflater mInflater;
    public List mItems = new ArrayList();

    public DescriptionAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /***************
     * add position
     **************/
    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof Recipe) {
            return DETAILS;
        } else if (mItems.get(position) instanceof String) {
            return HEADER;
        } else if (mItems.get(position) instanceof Ingredients) {
            return INGREDIENTS;
        } else if (mItems.get(position) instanceof Directions) {
            return DIRECTIONS;
        } else {
            return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DETAILS) {
            return new DetailsViewHolder(
                    DescriptionDetailsItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    )
            );
        } else if (viewType == HEADER) {
            return new HeaderViewHolder(
                    DescriptionHeaderItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    )
            );
        } else if (viewType == INGREDIENTS) {
            return new IngredientsViewHolder(
                    DescriptionIngredientsItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    ));
        } else if (viewType == DIRECTIONS) {
            return new DirectionsViewHolder(
                    DescriptionDirectionsItemBinding.inflate(
                            mInflater,
                            parent,
                            false
                    ));
        } else {
            throw new RuntimeException("The type has to be ONE");
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == DETAILS) {
            ((DetailsViewHolder) holder).setup((Recipe) mItems.get(position));
        } else if (holder.getItemViewType() == HEADER) {
            ((HeaderViewHolder) holder).setup((String) mItems.get(position));
        } else if (holder.getItemViewType() == INGREDIENTS) {
            ((IngredientsViewHolder) holder).setup((Ingredients) mItems.get(position));
        } else if (holder.getItemViewType() == DIRECTIONS) {
            ((DirectionsViewHolder) holder).setup((Directions) mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List list) {
        mItems.clear();
        mItems.addAll(list);
        notifyDataSetChanged();
    }
}

