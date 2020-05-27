package com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceItem;

import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface RentInvoiceItemMVC  extends ObservableViewMvc<RentInvoiceItemMVC.Listener> {


    public interface Listener {
        void onRentItemClicked(AccountTicket accountTicket);
    }

    void bindAccountTicket(AccountTicket accountTicket);


}