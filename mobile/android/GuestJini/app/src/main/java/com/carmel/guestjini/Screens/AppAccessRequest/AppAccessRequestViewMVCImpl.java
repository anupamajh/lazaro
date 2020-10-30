package com.carmel.guestjini.Screens.AppAccessRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;

public class AppAccessRequestViewMVCImpl
        extends BaseObservableViewMvc<AppAccessRequestViewMVC.Listener>
        implements AppAccessRequestViewMVC {

    private final EditText txtMobile;
    private final TextView mobileNumberErrorField;
    private final TextView txtInvalidMobileNumberError;
    private final ProgressBar mProgressBar;
    private final MaterialButton btnAppAccessRequest;

    public AppAccessRequestViewMVCImpl(LayoutInflater inflater,
                                       @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_app_access_request, parent, false));
        txtMobile = findViewById(R.id.txtMobile);
        mobileNumberErrorField = findViewById(R.id.mobileNumberErrorField);
        txtInvalidMobileNumberError = findViewById(R.id.txtInvalidMobileNumberError);
        mProgressBar = findViewById(R.id.progress);

        ImageView btnBack = findViewById(R.id.btnBack);
        TextView txtSignUp = findViewById(R.id.txtSignUp);
        btnAppAccessRequest = findViewById(R.id.btnAppAccessRequest);
        btnAppAccessRequest.setOnClickListener(view -> {
            String mobile = txtMobile.getText().toString().trim();
            boolean hasError = false;
            if (mobile.equals("")) {
                hasError = true;
                mobileNumberErrorField.setVisibility(View.VISIBLE);
            } else {
                mobileNumberErrorField.setVisibility(View.GONE);
            }
            if (!hasError) {
                for (Listener listener : getListeners()) {
                    listener.onAppAccessRequestClicked(mobile);
                }
            } else {
                mobileNumberErrorField.setVisibility(View.VISIBLE);
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

    @Override
    public void showInvalidPhoneNumberError(boolean showError) {
        if (showError) {
            txtInvalidMobileNumberError.setVisibility(View.VISIBLE);
        } else {
            txtInvalidMobileNumberError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showHasUser() {
        txtMobile.setVisibility(View.GONE);
        btnAppAccessRequest.setVisibility(View.GONE);
        txtInvalidMobileNumberError.setText("Your account is already activated. Please login with your credentials");
        txtInvalidMobileNumberError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoBooking() {
        txtInvalidMobileNumberError.setText("This phone number is not registered");
        txtInvalidMobileNumberError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOTPSentToast() {
        Toast.makeText(getContext(), "OTP has been sent to your registered mobile number", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showOTPFailedToast() {
        Toast.makeText(getContext(), "There was a problem sending OTP, Kindly try after sometime", Toast.LENGTH_LONG).show();
    }
}