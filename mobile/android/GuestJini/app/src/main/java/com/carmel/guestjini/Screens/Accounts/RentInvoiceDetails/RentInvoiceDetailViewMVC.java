package com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails;

import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface RentInvoiceDetailViewMVC extends ObservableViewMvc<RentInvoiceDetailViewMVC.Listener> {

    public interface Listener {

        void onBackClicked();

        void onPayNowClicked(
                String accountTicketId,
                String guestId,
                String email,
                String transactionAmount
        );
    }

    void bindAccountTicket(AccountTicket accountTicket);

    void showProgressIndication();

    void hideProgressIndication();

}
