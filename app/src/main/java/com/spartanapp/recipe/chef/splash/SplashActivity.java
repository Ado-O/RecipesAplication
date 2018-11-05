package com.spartanapp.recipe.chef.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.spartanapp.recipe.chef.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Intent intent = new Intent(this, MainActivity.class);

            Bundle bundle = getIntent().getExtras();
            if (bundle != null && bundle.get("type") != null) {
                intent.putExtra("isFromNotification", true);
                intent.putExtra("type", bundle.getString("type", "null"));

                if (bundle.get("type").equals("PROMO")) {
                    intent.putExtra("link", bundle.getString("link", ""));
                } else if (bundle.get("type").equals("PACKAGE")) {
                    intent.putExtra("package_id", bundle.getString("package_id", ""));
                }
            }

            startActivity(intent);
            finish();
        };
    }

