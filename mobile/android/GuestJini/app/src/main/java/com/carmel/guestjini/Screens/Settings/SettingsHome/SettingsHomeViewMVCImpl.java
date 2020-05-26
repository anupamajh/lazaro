package com.carmel.guestjini.Screens.Settings.SettingsHome;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class SettingsHomeViewMVCImpl
        extends BaseObservableViewMvc<SettingsHomeViewMVC.Listener>
        implements SettingsHomeViewMVC {

    public SettingsHomeViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_home, parent, false));
        RelativeLayout layoutMyProfile = findViewById(R.id.layoutMyProfile);
        RelativeLayout layoutChangePassword = findViewById(R.id.layoutChangePassword);
        RelativeLayout layoutPrivacyPolicy = findViewById(R.id.layoutPrivacyPolicy);
        RelativeLayout layoutTermsAndConditions = findViewById(R.id.layoutTermsAndConditions);
        RelativeLayout layoutNotifications = findViewById(R.id.layoutNotifications);
        RelativeLayout layoutLogout = findViewById(R.id.layoutLogout);

        layoutMyProfile.setOnClickListener(view -> {
            for(Listener listener:getListeners()){
                listener.onMyProfileClicked();
            }
        });

        layoutChangePassword.setOnClickListener(view -> {
            for(Listener listener:getListeners()){
                listener.onChangePasswordClicked();
            }
        });

        layoutPrivacyPolicy.setOnClickListener(view -> {
            for(Listener listener:getListeners()){
                listener.onPrivacyPolicyClicked();
            }
        });

        layoutTermsAndConditions.setOnClickListener(view -> {
            for(Listener listener:getListeners()){
                listener.onTermsAndConditionsClicked();
            }
        });

        layoutNotifications.setOnClickListener(view -> {
            for(Listener listener:getListeners()){
                listener.onNotificationsClicked();
            }
        });

        layoutLogout.setOnClickListener(view -> {
            for(Listener listener:getListeners()){
                listener.onLogoutClicked();
            }
        });

    }
}
