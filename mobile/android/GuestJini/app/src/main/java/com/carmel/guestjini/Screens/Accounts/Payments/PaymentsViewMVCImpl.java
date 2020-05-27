package com.carmel.guestjini.Screens.Accounts.Payments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class PaymentsViewMVCImpl
        extends BaseObservableViewMvc<PaymentsViewMVC.Listener>
        implements PaymentsViewMVC {

    private final WebView webView;

    public PaymentsViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_accounts_payments, parent, false));
        ImageView btnBack = findViewById(R.id.btnBack);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackPressed();
            }
        });

    }

    @Override
    public void loadPaymentView(String accountTicketId, String guestId, String email, String transactionAmount) {
        String url = "http://139.59.50.103:8012/pgredirect";
        String CUST_ID = guestId;
        String EMAIL = email;
        String TXN_AMOUNT = transactionAmount;
        try {
            String postData = "CUST_ID=" + CUST_ID + "&EMAIL=" + EMAIL + "&TXN_AMOUNT=" + TXN_AMOUNT;
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.postUrl(url,
                    postData.getBytes());
        } catch (Exception ex) {

        }

    }
}