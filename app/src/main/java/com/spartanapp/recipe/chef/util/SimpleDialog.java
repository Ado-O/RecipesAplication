package com.spartanapp.recipe.chef.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.spartanapp.recipe.chef.R;

public class SimpleDialog {

    public static void areYouSure(
            Context context,
            int title,
            int des,
            Callback callback
    ) {
        areYouSure(context, context.getString(title), context.getString(des), callback);
    }

    public static void areYouSure(
            Context context,
            String title,
            String des,
            Callback callback
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title)
                .setMessage(des)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> callback.onPositive())
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                })
                .show();
    }

    public static void message(
            Context context,
            int title,
            int des
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title)
                .setMessage(des)
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {

                })
                .show();
    }

    public static void likeOnFb(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

//        builder.setTitle(R.string.fbLike_title)
//                .setMessage(R.string.fbLike_des)
//                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
//                    LinkUtils.openLink(context, context.getString(R.string.facebookPage));
//                })
//                .setNegativeButton(android.R.string.no, (dialog, which) -> {
//                })
//                .show();
    }

    public static void rateUs(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.rate_us)
                .setMessage(R.string.rateUs_des)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    LinkUtils.openLink(context, context.getString(R.string.appLink));
                })
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                })
                .show();
    }

    public interface Callback {
        void onPositive();
    }
}
