package com.carmel.guestjini.Screens.ForgotPassword;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class ForgotPasswordViewMVCImpl
        extends BaseObservableViewMvc<ForgotPasswordViewMVC.Listener>
        implements ForgotPasswordViewMVC {

    private final EditText txtLoginId;
    private final TextView txtEmailErrorField;
    private final ProgressBar mProgressBar;


    public ForgotPasswordViewMVCImpl(LayoutInflater inflater,
                                     @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_forgot_password, parent, false));
        txtLoginId = findViewById(R.id.txtLoginId);
        txtEmailErrorField = findViewById(R.id.txtEmailErrorField);
        mProgressBar = findViewById(R.id.progress);

        Button btnResetPassword = findViewById(R.id.btnResetPassword);
        TextView txtSignUp = findViewById(R.id.txtSignUp);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnResetPassword.setOnClickListener(view -> {
            String strLoginId = txtLoginId.getText().toString().trim();
            if (!strLoginId.equals("")) {
                txtEmailErrorField.setVisibility(View.GONE);
                for (Listener listener : getListeners()) {
                    listener.onForgotPasswordClicked(strLoginId);
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

        txtSignUp.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onAppAccessRequestClicked();
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