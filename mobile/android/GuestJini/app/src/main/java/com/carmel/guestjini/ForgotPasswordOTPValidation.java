package com.carmel.guestjini;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordOTPValidation extends AppCompatActivity {
    TextView resendOtp,otpErrorField,getOneNow;
    MaterialButton submitButton;
    EditText firstOtp,secondOtp,thirdOtp,fourthOtp;
    final Context context=this;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_otpvalidation);
        resendOtp=findViewById(R.id.resendOtp);
        otpErrorField=findViewById(R.id.otpErrorMessage);
        submitButton=findViewById(R.id.submitButton);
        backArrow=findViewById(R.id.backArrow);
        firstOtp=findViewById(R.id.firstOTPBox);
        secondOtp=findViewById(R.id.secondOTPBox);
        thirdOtp=findViewById(R.id.thirdOTPBox);
        fourthOtp=findViewById(R.id.fourthOTPBox);
        getOneNow=findViewById(R.id.getOneNow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        getOneNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AppAccessRequest.class);
                startActivity(intent);
            }
        });

        final StringBuilder sb=new StringBuilder();

        firstOtp.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() ==1) {
                    secondOtp.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        secondOtp.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    thirdOtp.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
        thirdOtp.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    fourthOtp.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
        fourthOtp.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    firstOtp.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstOtpBox=firstOtp.getText().toString().trim();
                String secondOtpBox=secondOtp.getText().toString().trim();
                String thirdOtpBox=thirdOtp.getText().toString().trim();
                String fourthOtpBox=fourthOtp.getText().toString().trim();

                if((firstOtpBox.length()==0) && (secondOtpBox.length()==0) && (thirdOtpBox.length()==0) && (fourthOtpBox.length()==0)){
                    otpErrorField.setVisibility(View.VISIBLE);
                    firstOtp.setBackgroundResource(R.drawable.otp_error_box);
                    secondOtp.setBackgroundResource(R.drawable.otp_error_box);
                    thirdOtp.setBackgroundResource(R.drawable.otp_error_box);
                    fourthOtp.setBackgroundResource(R.drawable.otp_error_box);
                }else {
                    otpErrorField.setVisibility(View.GONE);
                    firstOtp.setBackgroundResource(R.drawable.otp_box);
                    secondOtp.setBackgroundResource(R.drawable.otp_box);
                    thirdOtp.setBackgroundResource(R.drawable.otp_box);
                    fourthOtp.setBackgroundResource(R.drawable.otp_box);
                }
                if(!(firstOtpBox.length()==1) || !(secondOtpBox.length()==1) || !(thirdOtpBox.length()==1) || !(fourthOtpBox.length()==1)){
                    otpErrorField.setVisibility(View.VISIBLE);
                }else {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.alert_dailogbox);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                    TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
                    alertDailogTitle.setText(getText(R.string.otp_success_title));

                    TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
                    alertDailogMessage.setText(getText(R.string.otp_success_description));

                    FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
                    doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#32BDD2")));
                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), ForgotPasswordOTPValidation.class);
                            startActivity(intent);
                        }
                    });

                    dialog.show();
                }

            }
        });

    }
}
