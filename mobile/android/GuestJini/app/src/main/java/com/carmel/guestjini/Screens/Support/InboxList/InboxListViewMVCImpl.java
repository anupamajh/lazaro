package com.carmel.guestjini.Screens.Support.InboxList;

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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InboxListViewMVCImpl
        extends BaseObservableViewMvc<InboxListViewMVC.Listener>
        implements InboxListViewMVC,
        InboxListRecyclerAdapter.Listener {

    private final EditText txtSearch;
    private final FloatingActionButton btnNewTicket;
    private final InboxListRecyclerAdapter inboxListRecyclerAdapter;
    private final ProgressBar progressBar;
    private final RelativeLayout layoutNoResult;
    private final DrawerLayout inboxListDrawerLayout;
    private final ImageView btnFilter;


    public InboxListViewMVCImpl(LayoutInflater inflater,
                                @Nullable ViewGroup parent,
                                ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_inbox_ticket_list, parent, false));
        txtSearch = findViewById(R.id.txtSearch);
        btnFilter = findViewById(R.id.btnFilter);
        progressBar = findViewById(R.id.progress);
        layoutNoResult = findViewById(R.id.layoutNoResult);
        inboxListDrawerLayout = findViewById(R.id.inboxListDrawerLayout);
        RecyclerView ticketRecyclerView = findViewById(R.id.lstTickets);
        ticketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        inboxListRecyclerAdapter = new InboxListRecyclerAdapter(this, viewMVCFactory);
        ticketRecyclerView.setAdapter(inboxListRecyclerAdapter);
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

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inboxListDrawerLayout.openDrawer(GravityCompat.START);
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
        inboxListRecyclerAdapter.bindTickets(ticketList);
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
