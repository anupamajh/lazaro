package com.carmel.guestjini.Screens.Settings.ChangePassword;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;

public class ChangePasswordViewMVCImpl
        extends BaseObservableViewMvc<ChangePasswordViewMVC.Listener>
        implements ChangePasswordViewMVC {

    private final TextView txtCurrentPassword;
    private final TextView txtNewPassword;
    private final TextView txtConfirmPassword;
    private final TextView txtCurrentPasswordError;
    private final TextView txtNewPasswordError;
    private final TextView txtConfirmPasswordError;
    private final ProgressBar progressBar;

    public ChangePasswordViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_change_password, parent, false));
        ImageView btnBack = findViewById(R.id.btnBack);
        txtCurrentPassword = findViewById(R.id.txtCurrentPassword);
        ImageView txtCurrentPasswordMaskingIcon = findViewById(R.id.txtCurrentPasswordMaskingIcon);
        txtNewPassword = findViewById(R.id.txtNewPassword);
        ImageView txtNewPasswordMaskingIcon = findViewById(R.id.txtNewPasswordMaskingIcon);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        ImageView txtConfirmPasswordMaskingIcon = findViewById(R.id.txtConfirmPasswordMaskingIcon);
        MaterialButton btnChangePassword = findViewById(R.id.btnChangePassword);
        txtCurrentPasswordError = findViewById(R.id.txtCurrentPasswordError);
        txtNewPasswordError = findViewById(R.id.txtNewPasswordError);
        txtConfirmPasswordError = findViewById(R.id.txtConfirmPasswordError);
        progressBar = findViewById(R.id.progress);

        txtCurrentPasswordMaskingIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    txtCurrentPasswordMaskingIcon.setImageResource(R.drawable.show_pwd_icon);
                    txtCurrentPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    txtCurrentPasswordMaskingIcon.setImageResource(R.drawable.hide_pwd_icon);
                    txtCurrentPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        txtNewPasswordMaskingIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    txtNewPasswordMaskingIcon.setImageResource(R.drawable.show_pwd_icon);
                    txtNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    txtNewPasswordMaskingIcon.setImageResource(R.drawable.hide_pwd_icon);
                    txtNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        txtConfirmPasswordMaskingIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    txtConfirmPasswordMaskingIcon.setImageResource(R.drawable.show_pwd_icon);
                    txtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    txtConfirmPasswordMaskingIcon.setImageResource(R.drawable.hide_pwd_icon);
                    txtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackPressed();
            }
        });

        btnChangePassword.setOnClickListener(view -> {
            String currentPassword = txtCurrentPassword.getText().toString();
            String newPassword = txtNewPassword.getText().toString();
            String confirmPassword = txtConfirmPassword.getText().toString();
            boolean isValid = true;

            if (currentPassword.trim().equals("")) {
                txtCurrentPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                txtCurrentPasswordError.setVisibility(View.GONE);
            }
            if (newPassword.trim().equals("")) {
                txtNewPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                txtNewPasswordError.setVisibility(View.GONE);
            }
            if (confirmPassword.trim().equals("")) {
                txtConfirmPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                txtConfirmPasswordError.setVisibility(View.GONE);
            }
            if (!confirmPassword.trim().equals(newPassword.trim())) {
                txtConfirmPasswordError.setVisibility(View.VISIBLE);
                isValid = false;
            } else {
                txtConfirmPasswordError.setVisibility(View.GONE);
            }
            if (isValid) {
                for (Listener listener : getListeners()) {
                    listener.onChangePasswordClicked(currentPassword.trim(), newPassword.trim());
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
    public void clearFields() {
        txtConfirmPassword.setText("");
        txtNewPassword.setText("");
        txtCurrentPassword.setText("");
    }
}
