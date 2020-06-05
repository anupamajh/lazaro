package com.carmel.guestjini.Screens.Login;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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

public class LoginViewMVCImpl extends BaseObservableViewMvc<LoginViewMVC.Listener> implements LoginViewMVC {

    private final EditText txtLoginId;
    private final EditText txtPassword;

    private final TextView txtInvalidCredentialsError;
    private final TextView txtEmailErrorField;
    private final TextView txtPasswordErrorField;

    private final ImageView passwordMaskingIcon;

    private final ProgressBar mProgressBar;


    public LoginViewMVCImpl(LayoutInflater inflater,
                            @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_login, parent, false));
        txtLoginId = findViewById(R.id.txtLoginId);
        txtPassword = findViewById(R.id.txtPassword);
        txtInvalidCredentialsError = findViewById(R.id.txtInvalidCredentialsError);
        txtEmailErrorField = findViewById(R.id.txtEmailErrorField);
        txtPasswordErrorField = findViewById(R.id.txtPasswordErrorField);
        passwordMaskingIcon = findViewById(R.id.passwordMaskingIcon);
        mProgressBar = findViewById(R.id.progress);

        TextView txtForgotPassword = findViewById(R.id.txtForgotPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView txtSignUp = findViewById(R.id.txtSignUp);
        btnLogin.setOnClickListener(view -> {
            String strLoginId = txtLoginId.getText().toString().trim();
            String strPassword = txtPassword.getText().toString().trim();
            for (Listener listener : getListeners()) {
                listener.onLoginClicked(strLoginId, strPassword);
            }
        });

        txtForgotPassword.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onForgotPasswordClicked();
            }
        });

        txtSignUp.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onSignUpClicked();
            }
        });

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
    }

    @Override
    public boolean isValid() {
        boolean hasError = true;
        txtInvalidCredentialsError.setVisibility(View.GONE);
        String strLoginId = txtLoginId.getText().toString().trim();
        String strPassword = txtPassword.getText().toString().trim();
        if (strLoginId.length() == 0) {
            hasError = false;
            txtEmailErrorField.setVisibility(View.VISIBLE);
        } else {
            txtEmailErrorField.setVisibility(View.GONE);
        }

        if (strPassword.length() == 0) {
            hasError = false;
            txtPasswordErrorField.setVisibility(View.VISIBLE);
        } else {
            txtPasswordErrorField.setVisibility(View.GONE);
        }
        return hasError;
    }

    @Override
    public void showAuthenticationFailure() {
        txtInvalidCredentialsError.setVisibility(View.VISIBLE);
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
