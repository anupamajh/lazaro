package com.carmel.guestjini.Screens.Settings.PrivacyPolicy;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class PrivacyPolicyController
        implements PrivacyPolicyViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private PrivacyPolicyViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public PrivacyPolicyController(ScreensNavigator mScreensNavigator) {
        this.mScreensNavigator = mScreensNavigator;
    }

    public void onStart() {
        viewMVC.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
    }

    public void bindView(PrivacyPolicyViewMVC viewMVC) {
        this.viewMVC = viewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    @Override
    public void onBackPressed() {
        mScreensNavigator.navigateUp();
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
