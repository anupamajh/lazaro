package com.carmel.guestjini.Screens.Accounts.Payments;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface PaymentsViewMVC extends ObservableViewMvc<PaymentsViewMVC.Listener> {

    public interface Listener {
        void onBackPressed();
    }

    void loadPaymentView(String accountTicketId,
                         String guestId,
                         String email,
                         String transactionAmount);
}