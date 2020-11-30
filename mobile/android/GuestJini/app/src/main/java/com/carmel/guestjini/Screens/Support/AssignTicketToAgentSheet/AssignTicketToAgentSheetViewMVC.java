package com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet;

import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface AssignTicketToAgentSheetViewMVC
        extends ObservableViewMvc<AssignTicketToAgentSheetViewMVC.Listener> {
    public interface Listener {
        void onAssignTicketClicked(TaskAssignee taskAssignee);
    }


    void showProgressIndication();

    void hideProgressIndication();

    void bindTaskAssignees(List<TaskAssignee> taskAssigneeList);

    void showTicketAssigned();

    void showTicketAssignFailed();

}
