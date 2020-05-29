package com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class RentInvoiceDetailViewMVCImpl
        extends BaseObservableViewMvc<RentInvoiceDetailViewMVC.Listener>
        implements RentInvoiceDetailViewMVC {

    private final TextView txtRentInvoiceDate;
    private final TextView txtRentInvoiceNo;
    private final TextView txtTotalAmount;
    private final TextView txtNarration;
    private final ProgressBar progressBar;

    private AccountTicket accountTicket;

    public RentInvoiceDetailViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_accounts_rent_invoice_details, parent, false));
        txtRentInvoiceDate = findViewById(R.id.txtRentInvoiceDate);
        txtRentInvoiceNo = findViewById(R.id.txtRentInvoiceNo);
        TextView txtPackageLabel = findViewById(R.id.txtPackageLabel);
        TextView txtPackageName = findViewById(R.id.txtPackageName);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        txtNarration = findViewById(R.id.txtNarration);
        progressBar = findViewById(R.id.progress);
        TextView txtAddress = findViewById(R.id.txtAddress);
        Button btnPayNow = findViewById(R.id.btnPayNow);
        ImageView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnPayNow.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onPayNowClicked(
                        accountTicket.getId(),
                        accountTicket.getGuestId(),
                        "prasanna.pete@gmail.com",
                        String.valueOf(accountTicket.getNetTotal())
                );
            }
        });
    }


    @Override
    public void bindAccountTicket(AccountTicket accountTicket) {
        this.accountTicket = accountTicket;
        txtRentInvoiceDate.setText(accountTicket.getCreationTime());
        txtRentInvoiceNo.setText(accountTicket.getTicketNumber());
        txtNarration.setText(accountTicket.getTicketNarration());
        txtTotalAmount.setText(String.valueOf(accountTicket.getNetTotal()));

    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);

    }
}
