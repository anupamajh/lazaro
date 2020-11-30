package com.carmel.guestjini.Screens.Support.InboxTicketDetail;

import com.carmel.guestjini.Networking.Tickets.TaskAssigneeResponse;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface InboxTicketDetailViewMVC
        extends ObservableViewMvc<InboxTicketDetailViewMVC.Listener> {


    public interface Listener {
        void onBackClicked();
        void onAssignTicketClicked();
        void onWithdrawFromGroupClicked();
        void onAssignTicketToAgentClicked(String groupId, String ticketId);
        void onWithdrawFromAgentClicked();
        void onCloseTicketClicked();
    }

    void bindTicket(Ticket ticket);

    void bindTicketCategories(List<TicketCategory> taskTicketCategories);

    void showProgressIndication();

    void hideProgressIndication();

    void bindTicketAssignmentDetails(TaskAssigneeResponse taskAssigneeResponse);

}
