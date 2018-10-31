package com.example.adoo.recipesaplication.main.subscribe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.example.adoo.recipesaplication.databinding.BannerMainBinding;


public class BannerViewHolder extends RecyclerView.ViewHolder {

    private BannerMainBinding mBinding;

    public BannerViewHolder(@NonNull BannerMainBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setup(String header){
        mBinding.setString(header);
    }
}
