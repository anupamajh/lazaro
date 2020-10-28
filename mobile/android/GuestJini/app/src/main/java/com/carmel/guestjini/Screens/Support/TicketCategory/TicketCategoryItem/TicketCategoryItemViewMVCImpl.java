package com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TicketCategoryItemViewMVCImpl
        extends BaseObservableViewMvc<TicketCategoryItemViewMVC.Listener>
        implements TicketCategoryItemViewMVC {

    TextView txtTicketCategoryName;
    FloatingActionButton btnExploreTicketCategories;

    private TicketCategory ticketCategory;

    public TicketCategoryItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_category_list_item, parent, false));
        txtTicketCategoryName = findViewById(R.id.txtTicketCategoryName);
        btnExploreTicketCategories = findViewById(R.id.btnExploreTicketCategories);

        btnExploreTicketCategories.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onTicketCategoryItemClicked(ticketCategory);
            }
        });
    }

    @Override
    public void bindTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
        this.txtTicketCategoryName.setText(ticketCategory.getCategoryDescription().trim());
    }
}
