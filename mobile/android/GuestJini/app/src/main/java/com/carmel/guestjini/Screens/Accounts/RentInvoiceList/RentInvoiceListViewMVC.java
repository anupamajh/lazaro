package com.carmel.guestjini.Screens.Accounts.RentInvoiceList;

import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface RentInvoiceListViewMVC extends ObservableViewMvc<RentInvoiceListViewMVC.Listener> {

    public interface Listener {
        void onAccountTicketItem(AccountTicket accountTicket);

        void onBackClicked();
    }

    void bindAccountTickets(List<AccountTicket> accountTickets);

    void showProgressIndication();

    void hideProgressIndication();


}
