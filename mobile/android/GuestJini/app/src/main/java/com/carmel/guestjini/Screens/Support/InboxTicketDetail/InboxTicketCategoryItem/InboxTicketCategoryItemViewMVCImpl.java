package com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketCategoryItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class InboxTicketCategoryItemViewMVCImpl
        extends BaseObservableViewMvc<InboxTicketCategoryItemViewMVC.Listener>
        implements InboxTicketCategoryItemViewMVC {

    private final RelativeLayout layoutCategorySeparator;
    private final TextView txtCategoryDescription;

    public InboxTicketCategoryItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_task_ticket_category_item, parent, false));
        txtCategoryDescription = findViewById(R.id.txtCategoryDescription);
        layoutCategorySeparator = findViewById(R.id.layoutCategorySeparator);
    }

    @Override
    public void bindTicketCategory(TicketCategory ticketCategory) {
        txtCategoryDescription.setText(ticketCategory.getCategoryDescription());
    }

    @Override
    public void hideSeparator(boolean hide) {
        if (hide) {
            layoutCategorySeparator.setVisibility(View.GONE);
        } else {
            layoutCategorySeparator.setVisibility(View.VISIBLE);
        }
    }
}
