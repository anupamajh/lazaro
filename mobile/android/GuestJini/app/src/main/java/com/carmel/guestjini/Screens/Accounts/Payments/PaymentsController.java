package com.carmel.guestjini.Screens.Accounts.Payments;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class PaymentsController
        implements PaymentsViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private PaymentsViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public PaymentsController(ScreensNavigator mScreensNavigator) {
        this.mScreensNavigator = mScreensNavigator;
    }

    public void onStart(String accountTicketId,
                        String guestId,
                        String email,
                        String transactionAmount) {
        viewMVC.registerListener(this);
        viewMVC.loadPaymentView(
                accountTicketId,
                guestId,
                email,
                transactionAmount
        );
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
    }

    public void bindView(PaymentsViewMVC viewMVC) {
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
