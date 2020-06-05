package com.carmel.guestjini.Screens.AppAccessRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AppAccessRequestViewMVCImpl
        extends BaseObservableViewMvc<AppAccessRequestViewMVC.Listener>
        implements AppAccessRequestViewMVC {

    private final EditText txtEmail;
    private final TextView txtEmailErrorField;
    private final EditText txtMobile;
    private final TextView mobileNumberErrorField;
    private final ProgressBar mProgressBar;


    public AppAccessRequestViewMVCImpl(LayoutInflater inflater,
                                       @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_app_access_request, parent, false));
        txtEmail = findViewById(R.id.txtEmail);
        txtEmailErrorField = findViewById(R.id.txtEmailErrorField);
        txtMobile = findViewById(R.id.txtMobile);
        mobileNumberErrorField = findViewById(R.id.mobileNumberErrorField);
        mProgressBar = findViewById(R.id.progress);

        ImageView btnBack = findViewById(R.id.btnBack);
        TextView txtSignUp = findViewById(R.id.txtSignUp);
        FloatingActionButton btnAppAccessRequest = findViewById(R.id.btnAppAccessRequest);
        btnAppAccessRequest.setOnClickListener(view -> {
            String email = txtEmail.getText().toString().trim();
            String mobile = txtMobile.getText().toString().trim();
            boolean hasError = false;
            if (email.equals("")) {
                hasError = true;
                txtEmailErrorField.setVisibility(View.VISIBLE);
            } else {
                txtEmailErrorField.setVisibility(View.GONE);
            }
            if (mobile.equals("")) {
                hasError = true;
                mobileNumberErrorField.setVisibility(View.VISIBLE);
            } else {
                mobileNumberErrorField.setVisibility(View.GONE);
            }
            if (!hasError) {
                txtEmailErrorField.setVisibility(View.GONE);
                for (Listener listener : getListeners()) {
                    listener.onAppAccessRequestClicked(email, mobile);
                }
            } else {
                txtEmailErrorField.setVisibility(View.VISIBLE);
            }
        });

        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackPressed();
            }
        });
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }


}