package com.carmel.guestjini.Screens.Support.TicketList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

public class TicketListViewMVCImpl
        extends BaseObservableViewMvc<TicketListViewMVC.Listener>
        implements TicketListViewMVC,
        TicketListRecyclerAdapter.Listener {

    private final EditText txtSearch;
    private final TicketListRecyclerAdapter ticketListRecyclerAdapter;
    private final ProgressBar progressBar;

    public TicketListViewMVCImpl(LayoutInflater inflater,
                                 @Nullable ViewGroup parent,
                                 ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_list, parent, false));
        txtSearch = findViewById(R.id.txtSearch);
        progressBar = findViewById(R.id.progress);
        RecyclerView ticketRecyclerView = findViewById(R.id.lstTickets);
        ticketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketListRecyclerAdapter = new TicketListRecyclerAdapter(this, viewMVCFactory);
        ticketRecyclerView.setAdapter(ticketListRecyclerAdapter);
        ImageView btnSearch = findViewById(R.id.btnSearch);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnSearch.setOnClickListener(view -> {
            String searchText = txtSearch.getText().toString().trim();
            for (Listener listener : getListeners()) {
                listener.onSearchClicked(searchText);
            }
        });

        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

    }


    @Override
    public void onTicketClicked(Ticket ticket) {
        for (Listener listener : getListeners()) {
            listener.onTicketClicked(ticket);
        }
    }

    @Override
    public void bindTickets(List<Ticket> ticketList, int totalItems) {
        ticketListRecyclerAdapter.bindTickets(ticketList);
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
