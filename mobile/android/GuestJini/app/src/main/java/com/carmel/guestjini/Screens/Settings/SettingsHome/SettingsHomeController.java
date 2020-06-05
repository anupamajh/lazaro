package com.carmel.guestjini.Screens.Settings.SettingsHome;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Screens.Login.LoginEventBus;

import java.io.Serializable;

public class SettingsHomeController
        implements SettingsHomeViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private final SharedPreferenceHelper sharedPreferenceHelper;
    private final LoginEventBus loginEventBus;

    private SettingsHomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public SettingsHomeController(ScreensNavigator mScreensNavigator,
                                  SharedPreferenceHelper sharedPreferenceHelper,
                                  LoginEventBus loginEventBus) {
        this.mScreensNavigator = mScreensNavigator;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.loginEventBus = loginEventBus;
    }

    public void onStart() {
        viewMVC.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
    }

    public void bindView(SettingsHomeViewMVC viewMVC) {
        this.viewMVC = viewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    @Override
    public void onMyProfileClicked() {
        mScreensNavigator.toMyProfile();
    }

    @Override
    public void onChangePasswordClicked() {
        mScreensNavigator.toChangePassword();
    }

    @Override
    public void onPrivacyPolicyClicked() {
        mScreensNavigator.toPrivacyPolicy();
    }

    @Override
    public void onTermsAndConditionsClicked() {
        mScreensNavigator.toTermsAndConditions();
    }

    @Override
    public void onNotificationsClicked() {
        mScreensNavigator.toNotifications();
    }

    @Override
    public void onLogoutClicked() {
        sharedPreferenceHelper.saveBooleanValue("isLoggedIn", false);
        sharedPreferenceHelper.saveStringValue("access_token", "");
        sharedPreferenceHelper.saveStringValue("refresh_token", "");
        sharedPreferenceHelper.saveStringValue("token_type", "");
        sharedPreferenceHelper.saveLongValue("expires_in", 0);
        sharedPreferenceHelper.commit();
        mScreensNavigator.toWelcomeScreen();
        loginEventBus.postEvent(0);
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
