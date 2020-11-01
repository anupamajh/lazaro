package com.carmel.guestjini.Screens.Support.TicketList;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TicketListViewMVCImpl
        extends BaseObservableViewMvc<TicketListViewMVC.Listener>
        implements TicketListViewMVC,
        TicketListRecyclerAdapter.Listener {

    private final EditText txtSearch;
    private final FloatingActionButton btnNewTicket;
    private final TicketListRecyclerAdapter ticketListRecyclerAdapter;
    private final ProgressBar progressBar;
    private final RelativeLayout layoutNoResult;

    public TicketListViewMVCImpl(LayoutInflater inflater,
                                 @Nullable ViewGroup parent,
                                 ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_list, parent, false));
        txtSearch = findViewById(R.id.txtSearch);
        progressBar = findViewById(R.id.progress);
        layoutNoResult = findViewById(R.id.layoutNoResult);
        RecyclerView ticketRecyclerView = findViewById(R.id.lstTickets);
        ticketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketListRecyclerAdapter = new TicketListRecyclerAdapter(this, viewMVCFactory);
        ticketRecyclerView.setAdapter(ticketListRecyclerAdapter);
        ImageView btnSearch = findViewById(R.id.btnSearch);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnNewTicket = findViewById(R.id.btnNewTicket);
        btnSearch.setOnClickListener(view -> {
            String searchText = txtSearch.getText().toString().trim();
            for (Listener listener : getListeners()) {
                listener.onSearchClicked(searchText);
            }
        });
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    String searchText = txtSearch.getText().toString().trim();
                    for (Listener listener : getListeners()) {
                        listener.onSearchClicked(searchText);
                    }
                    handled = true;
                }
                return handled;
            }
        });
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnNewTicket.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onCreateTicketClicked();
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
        if (ticketList.size() == 0) {
            layoutNoResult.setVisibility(View.VISIBLE);
        } else {
            layoutNoResult.setVisibility(View.GONE);
        }
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
