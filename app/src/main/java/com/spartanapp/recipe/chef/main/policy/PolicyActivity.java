package com.spartanapp.recipe.chef.main.policy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.databinding.PolicyActBinding;

public class PolicyActivity extends AppCompatActivity {

    private PolicyActBinding mPolicyActBinding;

    public static void startActivity(Activity activity) {

        Intent intent = new Intent(activity, PolicyActivity.class);
        activity.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policy_act);

        SharedPreferences settings = getSharedPreferences("my_prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", "Tom");
        editor.commit();


        mPolicyActBinding = DataBindingUtil.setContentView(this, R.layout.policy_act);

        mPolicyActBinding.webView.getSettings().setJavaScriptEnabled(true);

        mPolicyActBinding.webView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(PolicyActivity.this, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mPolicyActBinding.webView.loadUrl("https://policy.diamondappgroup.com/");

        mPolicyActBinding.webView.setOnClickListener(view -> onBackPressed());

        setupToolbar();

        findViewById(R.id.fab).setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Policy");
    }

}
