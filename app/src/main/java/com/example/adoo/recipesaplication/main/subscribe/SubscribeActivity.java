package com.example.adoo.recipesaplication.main.subscribe;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.adoo.recipesaplication.R;
import com.example.adoo.recipesaplication.data.Recipe;
import com.example.adoo.recipesaplication.data.SubRecipe;
import com.example.adoo.recipesaplication.databinding.SubscribeActBinding;
import com.example.adoo.recipesaplication.util.RecyclerViewClickListener;

import java.util.ArrayList;

public class SubscribeActivity extends AppCompatActivity  {

    public static final String TAG = SubscribeActivity.class.getSimpleName();

    SubscribeActBinding mSubscribeActBinding;
    private SubscribeFragmentAdapter mFragmentAdapter;

    public static void startActivity(Activity activity, boolean sub) {

        Intent intent = new Intent(activity, SubscribeActivity.class);
        intent.putExtra("subscribe", sub);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe_act);

        mSubscribeActBinding = DataBindingUtil.setContentView(this, R.layout.subscribe_act);

        setupArrow();
        setupListener();
        setupFragment();
    }

    private void setupArrow() {
        mSubscribeActBinding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    public void setupListener() {
        mSubscribeActBinding.pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 3) {
                    mSubscribeActBinding.btSubscribeNow.setVisibility(View.GONE);
                } else {
                    mSubscribeActBinding.btSubscribeNow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }

    /**
     * Fragment
     */
    private void setupFragment() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(SubscribeMainFragment.newInstance());
        arrayList.add(SubscribeWeightFragment.newInstance());
        arrayList.add(SubscribeDesFragment.newInstance());
        arrayList.add(SubscribeEndFragment.newInstance(getIntent().getIntExtra("id", 0)));

        mFragmentAdapter = new SubscribeFragmentAdapter(getSupportFragmentManager(), arrayList);
        mSubscribeActBinding.pager.setAdapter(mFragmentAdapter);

        //button in subscribeActivity
        mSubscribeActBinding.btSubscribeNow.setOnClickListener(v -> {
            mSubscribeActBinding.pager.setCurrentItem(3);
        });

        //button in banner
        if (getIntent().getBooleanExtra("subscribe", false)) {
            mSubscribeActBinding.pager.setCurrentItem(3);
            mSubscribeActBinding.btSubscribeNow.setVisibility(View.GONE);
        }
    }

}

