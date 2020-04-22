package com.carmel.guestjini.Accounts;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.carmel.guestjini.R;

import android.webkit.WebView;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.PaymentOptionsAdapter;
import Model.PaymentOptionsModel;
import kotlin.text.Charsets;

public class PaymentOptionsFragment extends Fragment {
    private ImageView backArrow;
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_payment_options, container, false);
        backArrow = rootView.findViewById(R.id.backArrow);
        mWebView = (WebView) rootView.findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        String url = "http://139.59.50.103:8012/pgredirect";
        final Bundle bundle = getArguments();
        String CUST_ID = "";
        String EMAIL = "";
        String TXN_AMOUNT = "";

        if (bundle != null) {
            CUST_ID = bundle.getString("CUST_ID");
            EMAIL = bundle.getString("EMAIL");
            TXN_AMOUNT = bundle.getString("TXN_AMOUNT");
        }
//        Map<String, String> postData = new HashMap<>();
//        postData.put("CUST_ID", CUST_ID);
//        postData.put("EMAIL", EMAIL);
//        postData.put("TXN_AMOUNT", TXN_AMOUNT);
        try {
            String postData = "CUST_ID=" + CUST_ID + "&EMAIL=" + EMAIL + "&TXN_AMOUNT=" + TXN_AMOUNT;
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.postUrl(url,
                    postData.getBytes());
        } catch (Exception ex) {

        }

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentInvoiceDetailsFragment rentInvoiceDetailsFragment = new RentInvoiceDetailsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.AccountsPlaceHolder, rentInvoiceDetailsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return rootView;
    }

}
