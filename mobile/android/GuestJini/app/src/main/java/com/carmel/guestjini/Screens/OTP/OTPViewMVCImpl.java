package com.carmel.guestjini.Screens.OTP;

import android.text.Editable;
import android.text.TextWatcher;
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

public class OTPViewMVCImpl
        extends BaseObservableViewMvc<OTPViewMVC.Listener>
        implements OTPViewMVC {
    private final ProgressBar progressBar;
    private final EditText txtOTP1;
    private final EditText txtOTP2;
    private final EditText txtOTP3;
    private final EditText txtOTP4;
    private final TextView txtOTPError;
    private final TextView txtResendOTP;

    private final TextView txtInvalidMobileNumberError;

    public OTPViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_base_otp, parent, false));
        progressBar = findViewById(R.id.progress);

        txtOTPError = findViewById(R.id.txtOTPError);
        txtInvalidMobileNumberError = findViewById(R.id.txtInvalidMobileNumberError);

        txtOTP1 = findViewById(R.id.txtOTP1);
        txtOTP2 = findViewById(R.id.txtOTP2);
        txtOTP3 = findViewById(R.id.txtOTP3);
        txtOTP4 = findViewById(R.id.txtOTP4);
        txtResendOTP = findViewById(R.id.txtResendOTP);
        txtResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onResendOTPClicked();
                }
            }
        });

        txtOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() ==1){
                    txtOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() ==1){
                    txtOTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() ==1){
                    txtOTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ImageView btnBack = findViewById(R.id.btnBack);

        MaterialButton btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOTPError.setVisibility(View.GONE);
                String otp1 = txtOTP1.getText().toString().trim();
                String otp2 = txtOTP2.getText().toString().trim();
                String otp3 = txtOTP3.getText().toString().trim();
                String otp4 = txtOTP4.getText().toString().trim();
                if((otp1 + otp2 + otp3 + otp4).length() == 4){
                    for(Listener listener:getListeners()){
                        listener.onVerifyOTPClicked((otp1 + otp2 + otp3 + otp4));
                    }
                }else{
                    txtOTPError.setVisibility(View.VISIBLE);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onBackPressed();
                }
            }
        });
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showInvalidOTP(boolean show) {
        if(show){
            txtInvalidMobileNumberError.setVisibility(View.VISIBLE);
        }else{
            txtInvalidMobileNumberError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getContext(), "There was an error in verifying OTP, Please try after sometime", Toast.LENGTH_LONG).show();
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
