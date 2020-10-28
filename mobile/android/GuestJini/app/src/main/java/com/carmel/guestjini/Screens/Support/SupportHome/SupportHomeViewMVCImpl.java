package com.carmel.guestjini.Screens.Support.SupportHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.carmel.guestjini.Screens.Support.KBList.KBRecyclerAdapter;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryRecyclerAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SupportHomeViewMVCImpl extends BaseObservableViewMvc<SupportHomeViewMVC.Listener>
        implements SupportHomeViewMVC,
        TicketCategoryRecyclerAdapter.Listener {


    private TicketCategoryRecyclerAdapter ticketCategoryRecyclerAdapter;
    private final ProgressBar progressBar;

    private final CardView activeTicketLayout;
    private final CardView draftTicketLayout;
    private final CardView archiveTicketLayout;

    public SupportHomeViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_home, parent, false));
        FloatingActionButton btnExploreActiveTickets = findViewById(R.id.btnExploreActiveTickets);
        FloatingActionButton btnExploreDraftTickets = findViewById(R.id.btnExploreDraftTickets);
        FloatingActionButton btnExploreHelp = findViewById(R.id.btnExploreHelp);
        FloatingActionButton btnExploreArchive = findViewById(R.id.btnExploreArchive);
        RecyclerView ticketCategoriesRecyclerView = findViewById(R.id.lstTicketCategories);
        ticketCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = findViewById(R.id.progress);
        activeTicketLayout = findViewById(R.id.activeTicketCardView);
        draftTicketLayout = findViewById(R.id.draftTicketCardView);
        archiveTicketLayout = findViewById(R.id.archiveTicketCard);
        ticketCategoryRecyclerAdapter = new TicketCategoryRecyclerAdapter(this, viewMVCFactory);
        ticketCategoriesRecyclerView.setAdapter(ticketCategoryRecyclerAdapter);
        btnExploreActiveTickets.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onActiveTicketClicked();
            }
        });

        btnExploreDraftTickets.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onDraftTicketClicked();
            }
        });

        btnExploreHelp.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onKBExploreClicked();
            }
        });

        btnExploreArchive.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onArchivedTicketsClicked();
            }
        });
    }

    @Override
    public void onTicketCategoryItemClicked(TicketCategory ticketCategory) {
        for (Listener listener : getListeners()) {
            listener.onTicketCategoryItemClicked(ticketCategory);
        }
    }

    @Override
    public void bindTicketCategories(List<TicketCategory> ticketCategoryList) {
        ticketCategoryRecyclerAdapter.bindTicketCategories(ticketCategoryList);
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
    public void showActiveTicketLayout(boolean show) {
        if (show) {
            activeTicketLayout.setVisibility(View.VISIBLE);
        } else {
            activeTicketLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDraftTicketLayout(boolean show) {
        if (show) {
            draftTicketLayout.setVisibility(View.VISIBLE);
        } else {
            draftTicketLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showArchiveTicketLayout(boolean show) {
        if (show) {
            archiveTicketLayout.setVisibility(View.VISIBLE);
        } else {
            archiveTicketLayout.setVisibility(View.GONE);
        }
    }
}
