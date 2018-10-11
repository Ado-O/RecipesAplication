package com.example.adoo.recipesaplication.main.description;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.adoo.recipesaplication.databinding.DescriptionHeaderItemBinding;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private DescriptionHeaderItemBinding mBinding;

    public HeaderViewHolder(@NonNull DescriptionHeaderItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setup(String header){
        mBinding.setHeader(header);
    }
}
