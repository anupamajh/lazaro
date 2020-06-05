package com.carmel.guestjini.Screens.Accounts.AccountsHome;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class AccountsHomeController
        implements AccountsHomeViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private AccountsHomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public AccountsHomeController(ScreensNavigator mScreensNavigator) {
        this.mScreensNavigator = mScreensNavigator;
    }

    public void onStart() {
        viewMVC.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
    }

    public void bindView(AccountsHomeViewMVC viewMVC) {
        this.viewMVC = viewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    @Override
    public void onRentInvoiceClicked() {
        mScreensNavigator.toRentInvoiceList();
    }


    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}