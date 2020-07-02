package com.base.utils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.base.user.R;
import com.base.user.databinding.ActivityWebViewBinding;

import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding binding;
    private String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.app_name));

        Bundle intent = getIntent().getExtras();
        if (intent != null) {
            url = intent.getString("url", "");
        } else {
            Alerts.with(this).showToast(getString(R.string.err_server_error));
        }

        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        binding.webview.addJavascriptInterface(new WebAppInterface(this), "Android");
        binding.webview.setWebViewClient(new Callback() {
            public void onPageFinished(WebView view, String url) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        binding.webview.loadUrl(url);


        binding.imgRefresh.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.webview.reload();
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && binding.webview.canGoBack()) {
            binding.webview.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    private static class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return (false);
        }
    }
}