package com.carmel.guestjini.Screens.Support.TicketCategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeViewMVC;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TicketCategoryListViewMVCImpl
        extends BaseObservableViewMvc<TicketCategoryListViewMVC.Listener>
        implements TicketCategoryListViewMVC,
        TicketCategoryRecyclerAdapter.Listener {

    private TicketCategoryRecyclerAdapter ticketCategoryRecyclerAdapter;
    private TextView txtParentCategoryTitle;
    private TicketCategory ticketCategory;
    private ProgressBar progressBar;

    public TicketCategoryListViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_category_list, parent, false));
        RecyclerView ticketCategoriesRecyclerView = findViewById(R.id.lstTicketCategories);
        ticketCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketCategoryRecyclerAdapter = new TicketCategoryRecyclerAdapter(this, viewMVCFactory);
        FloatingActionButton btnBackToSupport = findViewById(R.id.btnBackToSupport);
        txtParentCategoryTitle = findViewById(R.id.txtParentCategoryTitle);
        progressBar = findViewById(R.id.progress);
        ticketCategoriesRecyclerView.setAdapter(ticketCategoryRecyclerAdapter);
        btnBackToSupport.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });
    }

    @Override
    public void bindTicketCategoryTitle(TicketCategory ticketCategory) {
        this.txtParentCategoryTitle.setText(ticketCategory.getCategoryDescription());
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
    public void onTicketCategoryItemClicked(TicketCategory ticketCategory) {
        for (Listener listener : getListeners()) {
            listener.onTicketCategoryItemClicked(ticketCategory);
        }
    }
}
