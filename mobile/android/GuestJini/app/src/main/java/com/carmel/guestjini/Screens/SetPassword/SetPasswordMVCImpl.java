package com.carmel.guestjini.Screens.SetPassword;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;
import com.carmel.guestjini.Screens.OTP.OTPViewMVC;
import com.google.android.material.button.MaterialButton;

public class SetPasswordMVCImpl
        extends BaseObservableViewMvc<SetPasswordMVC.Listener>
        implements SetPasswordMVC {

    private final ProgressBar progressBar;
    private final EditText txtPassword;
    private final EditText txtConfirmPassword;
    private final TextView txtUserFullName;
    private final TextView txtPasswordError;
    private final TextView txtConfirmPasswordError;

    public SetPasswordMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_base_set_password, parent, false));
        progressBar = findViewById(R.id.progress);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtUserFullName = findViewById(R.id.txtUserFullName);
        txtPasswordError = findViewById(R.id.txtPasswordError);
        txtConfirmPasswordError = findViewById(R.id.txtConfirmPasswordError);
        ImageView passwordMaskingIcon = findViewById(R.id.passwordMaskingIcon);
        ImageView confirmPasswordMaskingIcon = findViewById(R.id.confirmPasswordMaskingIcon);
        MaterialButton btnSubmit = findViewById(R.id.btnSubmit);
        passwordMaskingIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    passwordMaskingIcon.setImageResource(R.drawable.show_pwd_icon);
                    txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    passwordMaskingIcon.setImageResource(R.drawable.hide_pwd_icon);
                    txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        confirmPasswordMaskingIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    confirmPasswordMaskingIcon.setImageResource(R.drawable.show_pwd_icon);
                    txtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    confirmPasswordMaskingIcon.setImageResource(R.drawable.hide_pwd_icon);
                    txtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = txtPassword.getText().toString().trim();
                String confirmPassword = txtConfirmPassword.getText().toString().trim();
                boolean hasError = false;
                if(password.length() == 0){
                    txtPasswordError.setVisibility(View.VISIBLE);
                    hasError = true;
                }else {
                    if(password.length()<6){
                        txtPasswordError.setText("Password should be at least 6 characters long.");
                        txtPasswordError.setVisibility(View.VISIBLE);
                        hasError = true;
                    }else {
                        txtPasswordError.setVisibility(View.GONE);
                    }
                }
                if(confirmPassword.length() == 0){
                    txtConfirmPasswordError.setVisibility(View.VISIBLE);
                    hasError = true;
                }else {
                    if(!password.equals(confirmPassword)){
                        txtConfirmPasswordError.setText("Passwords do not match.");
                        txtConfirmPasswordError.setVisibility(View.VISIBLE);
                        hasError = true;
                    }else {
                        txtConfirmPasswordError.setVisibility(View.GONE);
                    }
                }
                if(!hasError){
                    for(Listener listener:getListeners()){
                        listener.onSubmitClicked(password);
                    }
                }
            }
        });
    }

    @Override
    public void bindFullName(String full_name) {
        txtUserFullName.setText(full_name);
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
    public void showPasswordSetMessage() {
        Toast.makeText(getContext(), "Password has been set, Please Login using mobile number and password", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPasswordSetFailedMessage() {
        Toast.makeText(getContext(), "Setting password failed, Please try after sometime", Toast.LENGTH_LONG).show();
    }
}
