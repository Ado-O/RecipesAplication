package com.spartanapp.recipe.chef.main.description;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.spartanapp.recipe.chef.data.Directions;
import com.spartanapp.recipe.chef.databinding.DescriptionDirectionsItemBinding;

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
