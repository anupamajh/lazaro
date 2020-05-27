package com.carmel.guestjini.Screens.Accounts.RentInvoiceList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

public class RentInvoiceListViewMVCImpl
        extends BaseObservableViewMvc<RentInvoiceListViewMVC.Listener>
        implements RentInvoiceListViewMVC,
        RentInvoiceRecycleAdapter.Listener {

    private final RentInvoiceRecycleAdapter rentInvoiceRecycleAdapter;
    private final ProgressBar progressBar;


    public RentInvoiceListViewMVCImpl(LayoutInflater inflater,
                             @Nullable ViewGroup parent,
                             ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_accounts_rent_invoice_list, parent, false));

        RecyclerView rentInvoiceRecyclerView = findViewById(R.id.lstKBItems);
        ImageView btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progress);
        rentInvoiceRecyclerView = findViewById(R.id.lstRentInvoices);
        rentInvoiceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rentInvoiceRecycleAdapter = new RentInvoiceRecycleAdapter(this, viewMVCFactory);
        rentInvoiceRecyclerView.setAdapter(rentInvoiceRecycleAdapter);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

    }

    @Override
    public void bindAccountTickets(List<AccountTicket> accountTickets) {
        rentInvoiceRecycleAdapter.bindAccountTickets(accountTickets);
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onAccountItemClicked(AccountTicket accountTicket) {
        for (Listener listener : getListeners()) {
            listener.onAccountTicketItem(accountTicket);
        }
    }
}
