package com.carmel.guestjini.Screens.Support.TicketCategory;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface TicketCategoryListViewMVC
        extends ObservableViewMvc<TicketCategoryListViewMVC.Listener> {
    public interface Listener {
        void onTicketCategoryItemClicked(TicketCategory ticketCategory);

        void onBackClicked();
    }

    void bindTicketCategoryTitle(TicketCategory ticketCategory);

    void bindTicketCategories(List<TicketCategory> ticketCategoryList);

    void showProgressIndication();

    void hideProgressIndication();
}