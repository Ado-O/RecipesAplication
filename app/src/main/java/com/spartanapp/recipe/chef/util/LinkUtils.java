package com.spartanapp.recipe.chef.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class LinkUtils {

    public static void openLink(Context context, String link){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        context.startActivity(intent);
    }
}
