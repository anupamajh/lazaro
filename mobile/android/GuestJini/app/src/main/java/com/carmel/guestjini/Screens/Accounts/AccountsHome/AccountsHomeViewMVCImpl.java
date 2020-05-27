package com.carmel.guestjini.Screens.Accounts.AccountsHome;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.card.MaterialCardView;

public class AccountsHomeViewMVCImpl
        extends BaseObservableViewMvc<AccountsHomeViewMVC.Listener>
        implements AccountsHomeViewMVC {

    public AccountsHomeViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_accounts_home, parent, false));
        MaterialCardView cardRentInvoice = findViewById(R.id.cardRentInvoice);
        cardRentInvoice.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onRentInvoiceClicked();
            }
        });
    }
}