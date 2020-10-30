package com.carmel.guestjini.Screens.Support.TicketDetail;

import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface TicketDetailsViewMVC
        extends ObservableViewMvc<TicketDetailsViewMVCImpl.Listener> {


    public interface Listener {
        void onBackClicked();

        void onSubmitClicked(String ticketId, String comment);
    }

    void bindTaskNotes(List<TaskNote> taskNotes);

    void bindTicket(Ticket ticket);

    void bindTicketCategories(List<TicketCategory> ticketCategoryList);

    void clearComment();

    void showProgressIndication();

    void hideProgressIndication();
}