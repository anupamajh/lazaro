package com.carmel.guestjini.Screens.Settings.TermsAndConditions;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class TermsAndConditionsController
        implements TermsAndConditionsViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private TermsAndConditionsViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public TermsAndConditionsController(ScreensNavigator mScreensNavigator) {
        this.mScreensNavigator = mScreensNavigator;
    }

    public void onStart() {
        viewMVC.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
    }

    public void bindView(TermsAndConditionsViewMVC viewMVC) {
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
