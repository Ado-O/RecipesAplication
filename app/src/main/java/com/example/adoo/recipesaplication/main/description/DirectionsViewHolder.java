package com.example.adoo.recipesaplication.main.description;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.adoo.recipesaplication.data.Directions;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.databinding.DescriptionDirectionsItemBinding;

import java.util.ArrayList;
import java.util.List;

public class DirectionsViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = DirectionsViewHolder.class.getSimpleName();

    private DescriptionDirectionsItemBinding mBinding;

    public DirectionsViewHolder(@NonNull DescriptionDirectionsItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;

    }

    public void setup(Directions directions) {
        mBinding.setDirections(directions);

    }

}
