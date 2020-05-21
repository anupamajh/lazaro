package com.carmel.guestjini.Screens.Welcome;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class WelcomeController  implements WelcomeViewMVC.Listener {


    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;
    private WelcomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public WelcomeController(ScreensNavigator mScreensNavigator) {
        this.mScreensNavigator = mScreensNavigator;
    }

    public void onStart() {
        viewMVC.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
    }

    public void bindView(WelcomeViewMVC welcomeViewMVC) {
        viewMVC = welcomeViewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedSate(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }

    @Override
    public void onNextClicked() {
        mScreensNavigator.toLoginScreen();
    }
}
