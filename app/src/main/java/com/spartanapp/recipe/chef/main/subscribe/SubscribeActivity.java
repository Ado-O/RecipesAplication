package com.spartanapp.recipe.chef.main.subscribe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.billingclient.api.Purchase;
import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.databinding.SubscribeActBinding;
import com.spartanapp.recipe.chef.main.MainActivity;
import com.spartanapp.recipe.chef.util.billing.BillingManager;

import java.util.ArrayList;
import java.util.List;

import static com.spartanapp.recipe.chef.RecipeApp.SUB_1_MONTH;

public class SubscribeActivity extends AppCompatActivity implements
        BillingManager.BillingUpdatesListener {

    public static final String TAG = SubscribeActivity.class.getSimpleName();

    SubscribeActBinding mSubscribeActBinding;
    private SubscribeFragmentAdapter mFragmentAdapter;
    private BillingManager mBillingManager;
    private boolean onSub = false;

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

        mBillingManager = new BillingManager(this, this);
        mBillingManager.start();

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
        arrayList.add(SubscribeEndFragment.newInstance());

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

    /**
     * dialog for billingManager
     */
    public void SubEndClick(View v) {
        mBillingManager.onSubscribeClick("1month");
        onSub = true;
    }

    @Override
    public void onBillingClientSetupFinished() {
        mBillingManager.querySubscriptions();


        mBillingManager.getPrice(SUB_1_MONTH, new BillingManager.GetPriceCallback() {
            @Override
            public void onSuccess(String price) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SubscribeActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Price", price);
                editor.apply();
            }

            @Override
            public void onError() {
                Log.e(TAG, "onError: ");
            }
        });
    }

    @Override
    public void onPurchasesUpdated(List<Purchase> purchases) {

//        Log.e(TAG, "subActiv: "+purchases.size());
//        Log.e(TAG, "boolean: "+onSub);

        for (Purchase p : purchases) {


            if (p.getSku().equals("1month") && !purchases.isEmpty() && onSub == true) {

                //substraction
                SharedPreferences preferences = getSharedPreferences("is_sub", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("free", true);
                editor.commit();

              // onBackPressed();
              MainActivity.startActivity(this);
            }
        }
    }
}

