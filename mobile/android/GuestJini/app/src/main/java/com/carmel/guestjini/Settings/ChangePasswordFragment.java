package com.carmel.guestjini.Settings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.ForgotPasswordOTPValidation;
import com.carmel.guestjini.Models.GenericResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.User.UserService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChangePasswordFragment extends Fragment {
    ConstraintLayout currentPasswordLayout, newPasswordLayout, confirmPasswordLayout;
    EditText currentPasswordEditText, newPasswordEditText, confirmPasswordEditText;
    TextView currentPaswordErrorField, newPaswordErrorField, confirmPaswordErrorField;
    MaterialButton changePasswordButton;
    ImageView currentPassword, newPassword, confirmPassword, backArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_change_password, container, false);
        currentPasswordLayout = rootview.findViewById(R.id.currentPasswordLayout);
        currentPasswordEditText = rootview.findViewById(R.id.currentPasswordEdit);
        currentPaswordErrorField = rootview.findViewById(R.id.currentPasswordErrorField);

        newPasswordLayout = rootview.findViewById(R.id.newPasswordLayout);
        newPasswordEditText = rootview.findViewById(R.id.newPasswordEdit);
        newPaswordErrorField = rootview.findViewById(R.id.newPasswordErrorField);
        confirmPasswordLayout = rootview.findViewById(R.id.confirmPasswordLayout);
        confirmPasswordEditText = rootview.findViewById(R.id.confirmPasswordEdit);
        confirmPaswordErrorField = rootview.findViewById(R.id.confirmPasswordErrorField);
        changePasswordButton = rootview.findViewById(R.id.changePasswordButton);

        currentPassword = rootview.findViewById(R.id.currentPasswordMaskingIcon);
        newPassword = rootview.findViewById(R.id.newPasswordMaskingIcon);
        confirmPassword = rootview.findViewById(R.id.confirmPasswordMaskingIcon);

        backArrow = rootview.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsLandingFragment settingsLandingFragment=new SettingsLandingFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SettingsPlaceHolder,settingsLandingFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        currentPassword.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    currentPassword.setImageResource(R.drawable.password_unmasking_icon);
                    currentPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    currentPassword.setImageResource(R.drawable.password_masking_icon);
                    currentPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        newPassword.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    newPassword.setImageResource(R.drawable.password_unmasking_icon);
                    newPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    newPassword.setImageResource(R.drawable.password_masking_icon);
                    newPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        confirmPassword.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    confirmPassword.setImageResource(R.drawable.password_unmasking_icon);
                    confirmPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    flag = true;
                    confirmPassword.setImageResource(R.drawable.password_masking_icon);
                    confirmPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasError = false;
                currentPaswordErrorField.setVisibility(View.GONE);
                newPaswordErrorField.setVisibility(View.GONE);
                confirmPaswordErrorField.setVisibility(View.GONE);
                if (currentPasswordEditText.getText().toString().trim().length() == 0
                        && newPasswordEditText.getText().toString().trim().length() == 0
                        && confirmPasswordEditText.getText().toString().trim().length() == 0) {
                    currentPaswordErrorField.setVisibility(View.VISIBLE);
                    newPaswordErrorField.setVisibility(View.VISIBLE);
                    confirmPaswordErrorField.setVisibility(View.VISIBLE);
                    currentPasswordLayout.setBackgroundResource(R.drawable.edit_red_textbox);
                    newPasswordLayout.setBackgroundResource(R.drawable.edit_red_textbox);
                    confirmPasswordLayout.setBackgroundResource(R.drawable.edit_red_textbox);
                    hasError = true;
                }
                if (!newPasswordEditText.getText().toString().equals(
                        confirmPasswordEditText.getText().toString()
                )) {
                    hasError = true;
                    confirmPaswordErrorField.setVisibility(View.VISIBLE);
                    currentPasswordLayout.setBackgroundResource(R.drawable.edit_red_textbox);
                }
                if (!hasError) {
                    changePassword();
                }
            }
        });
        return rootview;
    }

    private void changePassword() {
        String currentPassword = currentPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();

        Map<String, String> postData = new HashMap<>();
        postData.put("currentPassword", currentPassword);
        postData.put("newPassword", newPassword);

        AuthServiceHolder authServiceHolder = new AuthServiceHolder();
        SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        String accessToken = preferences.getString("access_token", "");
        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                .addHeader("Authorization", accessToken)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        AuthService authService = retrofit.create(AuthService.class);
        authServiceHolder.set(authService);

        UserService userService = retrofit.create(UserService.class);
        Call<GenericResponse> changePasswordCall = userService.changePassword(postData);
        changePasswordCall.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                try {
                    GenericResponse genericResponse = response.body();
                    if(genericResponse.getSuccess()){
                        showDialog(true, "Password changed successfully");
                    }else{
                        showDialog(false, "There was an error while changing password, Please try after sometime");
                    }
                } catch (Exception ex) {
                    showDialog(false, "There was an error while changing password, Please try after sometime");
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                showDialog(false, "There was an error while changing password, Please try after sometime");
            }
        });


    }


    private void showDialog(boolean isSuccess, String message) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.alert_dailogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
        if (isSuccess) {
            alertDailogTitle.setText(getText(R.string.success));
        } else {
            alertDailogTitle.setText(getText(R.string.failed));
            alertDailogTitle.setTextColor(Color.parseColor("#E65959"));
        }
        TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
        alertDailogMessage.setText(message);
        FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
        if (isSuccess) {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#32BDD2")));
        } else {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#E65959")));
        }


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
