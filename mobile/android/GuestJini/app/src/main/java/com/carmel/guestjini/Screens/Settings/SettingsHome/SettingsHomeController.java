package com.carmel.guestjini.Screens.Settings.SettingsHome;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class SettingsHomeController
        implements SettingsHomeViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private SettingsHomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public SettingsHomeController(ScreensNavigator mScreensNavigator) {
        this.mScreensNavigator = mScreensNavigator;
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

    public void restoreSavedSate(SavedState savedState) {
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
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
