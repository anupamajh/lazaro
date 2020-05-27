package com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class RentInvoiceItemMVCImpl
        extends BaseObservableViewMvc<RentInvoiceItemMVC.Listener>
        implements RentInvoiceItemMVC {

    private final TextView txtRentInvoiceDate;
    private final TextView txtRentInvoiceNo;
    private final TextView txtRentAmount;
    private final ImageView btnShowRentInvoiceDetails;

    private AccountTicket accountTicket;

    public RentInvoiceItemMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_accounts_rent_invoice_list_item, parent, false));
        txtRentInvoiceDate = findViewById(R.id.txtRentInvoiceDate);
        txtRentInvoiceNo = findViewById(R.id.txtRentInvoiceNo);
        txtRentAmount = findViewById(R.id.txtRentAmount);
        btnShowRentInvoiceDetails = findViewById(R.id.btnShowRentInvoiceDetails);
        btnShowRentInvoiceDetails.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onRentItemClicked(accountTicket);
            }
        });

    }

    @Override
    public void bindAccountTicket(AccountTicket accountTicket) {
        this.accountTicket = accountTicket;
        txtRentInvoiceDate.setText(accountTicket.getCreationTime());
        txtRentInvoiceNo.setText(accountTicket.getTicketNumber());
        txtRentAmount.setText(String.valueOf(accountTicket.getNetTotal()));
    }
}
