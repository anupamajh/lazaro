package com.carmel.guestjini.Screens.Settings.TermsAndConditions;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class TermsAndConditionsViewMVCImpl
        extends BaseObservableViewMvc<TermsAndConditionsViewMVC.Listener>
        implements TermsAndConditionsViewMVC {
    public TermsAndConditionsViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_privacy_policy, parent, false));
        ImageView btnBack = findViewById(R.id.btnBack);
        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        String url = "https://guesture.in/terms-and-conditions";
        webView.loadUrl(url);
        btnBack.setOnClickListener(view -> {
            for(Listener listener:getListeners()){
                listener.onBackPressed();
            }
        });

    }
}