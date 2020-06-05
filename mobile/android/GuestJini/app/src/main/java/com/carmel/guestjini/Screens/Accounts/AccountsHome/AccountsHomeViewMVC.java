package com.carmel.guestjini.Screens.Accounts.AccountsHome;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface AccountsHomeViewMVC extends ObservableViewMvc<AccountsHomeViewMVC.Listener> {
    public interface Listener {
        void onRentInvoiceClicked();
    }
}
