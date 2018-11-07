package com.spartanapp.recipe.chef.util.billing;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.util.AppExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BillingManager implements PurchasesUpdatedListener {


    private static final String TAG = "BillingManager";

    private final Activity mActivity;

    private final BillingUpdatesListener mListener;

    private BillingClient mBillingClient;

    private boolean mIsServiceConnected = false;

    private final List<Purchase> mPurchases = new ArrayList<>();

    private final List<Purchase> mSubscriptions = new ArrayList<>();

    //constructor
    public BillingManager(Activity activity, BillingUpdatesListener listener) {
        mActivity = activity;
        mListener = listener;

        mBillingClient = BillingClient.newBuilder(activity).setListener(this).build();
    }

    /************************************
     * SkuType.INAPP for one-time products
     * SkuType.SUBS, for subscriptions.
     ***********************************/

    /******************
     * func we call in mainActivity for start connection if connect true we send respons
     * to listener billingClientSetupFinished
     *****************/
    public void start() {
        startServiceConnection(() -> {
            mListener.onBillingClientSetupFinished();
            /*queryPurchases();
            querySubscriptions();*/

            Log.e(TAG, "startServiceConnected");
        });
    }


    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        if (responseCode == BillingClient.BillingResponse.OK) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }

            mPurchases.addAll(purchases);

            mListener.onPurchasesUpdated(mPurchases);
        }
    }

    /**************
     * func for start the connection
     **************/
    public void startServiceConnection(final Runnable executeOnSuccess) {
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    mIsServiceConnected = true;
                    if (executeOnSuccess != null) {
                        executeOnSuccess.run();
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                mIsServiceConnected = false;
            }
        });
    }

    private void executeServiceRequest(Runnable runnable) {
        if (mIsServiceConnected) {
            runnable.run();
        } else {
            startServiceConnection(runnable);
        }
    }

    /*****************
     * geting response - Purchase
     ****************/
    public void onSubscribeClick(String sku) {
        Runnable purchaseFlowRequest = () -> {
            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                    .setSku(sku)
                    .setType(BillingClient.SkuType.SUBS)
                    .build();
            mBillingClient.launchBillingFlow(mActivity, flowParams);
        };

        executeServiceRequest(purchaseFlowRequest);
    }

    /******************
     * Handles the purchase
     * @param purchase - Purchase to be handled
     *****************/
    private void handlePurchase(Purchase purchase) {
        if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
            Log.i(TAG, "Got a purchase: " + purchase + "; but signature is bad. Skipping...");
            return;
        }
        mPurchases.add(purchase);
    }

    /******************
     * Query in-apps  ??
     *****************/
    public void queryPurchases() {
        Runnable queryToExecute = () -> {
            Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);

            if (mBillingClient == null ||
                    purchasesResult.getResponseCode() != BillingClient.BillingResponse.OK) {
                return;
            }
            mPurchases.clear();
            onPurchasesUpdated(BillingClient.BillingResponse.OK, purchasesResult.getPurchasesList());
        };

        executeServiceRequest(queryToExecute);
    }

    /******************
     * Query subs  ??
     *****************/
    public void querySubscriptions() {
        Log.e(TAG, "querySub1");
        Runnable queryToExecute = () -> {
            Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.SUBS);

            Log.e(TAG, purchasesResult.getPurchasesList().size()+"   ...");

            if (mBillingClient == null ||
                    purchasesResult.getResponseCode() != BillingClient.BillingResponse.OK) {
                return;
            }

            mPurchases.clear();
            onPurchasesUpdated(BillingClient.BillingResponse.OK, purchasesResult.getPurchasesList());
        };

        executeServiceRequest(queryToExecute);
    }

    /******************
     * Query sku details
     *****************/
    public void querySkuDetails(
            @BillingClient.SkuType final String itemType,
            final List<String> skuList,
            final SkuDetailsResponseListener listener) {
        Runnable queryRequest = () -> {
            SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
            params.setSkusList(skuList).setType(itemType);
            mBillingClient.querySkuDetailsAsync(params.build(),
                    (responseCode, skuDetailsList) -> listener.onSkuDetailsResponse(responseCode, skuDetailsList));
        };

        executeServiceRequest(queryRequest);
    }

    private final AppExecutors mAppExecutors = new AppExecutors();

    /******************
     * geting price
     * @param sku - string what id we want
     * @param callback - geting callback
     *****************/
    public void getPrice(String sku, GetPriceCallback callback) {
        if (isServiceConnected()) {
            mAppExecutors.diskIO().execute(() -> {
                List<String> skuList = new ArrayList<>();
                skuList.add(sku);

                querySkuDetails(
                        BillingClient.SkuType.SUBS,
                        skuList,
                        (responseCode, skuDetailsList) -> {
                            if (responseCode == BillingClient.BillingResponse.OK
                                    && skuDetailsList != null) {
                                for (SkuDetails skuDetails : skuDetailsList) {
                                    mAppExecutors.mainThread().execute(() -> callback.onSuccess(skuDetails.getPrice()));
                                }
                            }

                        }
                );
            });
        }
    }

//    public void getPriceSub(List<String> skuList, GetPricesCallback callback) {
//        if (isServiceConnected()) {
//            mAppExecutors.diskIO().execute(() -> {
//                /*List<String> skuList = new ArrayList<>();
//                skuList.add(sku);*/
//
//                Log.e(TAG, "isSevice conneted");
//
//                querySkuDetails(
//                        BillingClient.SkuType.SUBS,
//                        skuList,
//                        (responseCode, skuDetailsList) -> {
//                            if (responseCode == BillingClient.BillingResponse.OK
//                                    && skuDetailsList != null) {
//                                mAppExecutors.mainThread().execute(() -> callback.onSuccess(skuDetailsList));
//                            }
//                        }
//                );
//            });
//        }
//    }

    /******************
     * SECURITY
     * Verifies that the purchase was
     * signed correctly for
     * this developer's public key.
     *****************/
    private boolean verifyValidSignature(String signedData, String signature) {

        try {
            return Security.verifyPurchase(
                    mActivity.getString(R.string.appBase64),
                    signedData,
                    signature
            );
        } catch (IOException e) {
            Log.e(TAG, "Got an exception trying to validate a purchase: " + e);
            return false;
        }
    }

    public boolean isServiceConnected() {

        Log.e(TAG, "is connected");

        return mIsServiceConnected;
    }

    /******************
     * Clear the resources
     *****************/
    public void destroy() {
        Log.d(TAG, "Destroying the manager.");

        if (mBillingClient != null && mBillingClient.isReady()) {
            mBillingClient.endConnection();
            mBillingClient = null;
        }
    }

    /**
     * Billing Listener
     */
    public interface BillingUpdatesListener {
        void onBillingClientSetupFinished();

        void onPurchasesUpdated(List<Purchase> purchases);
    }

    public interface GetPriceCallback {
        void onSuccess(String price);

        void onError();
    }

    public interface GetPricesCallback {
        void onSuccess(List<SkuDetails> list);

        void onError();
    }

}
