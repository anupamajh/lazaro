package com.carmel.guestjini.Screens.Support.Inbox;

import com.carmel.guestjini.Networking.Tickets.TaskCountResponse;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.Set;

public interface InboxViewMVC  extends ObservableViewMvc<InboxViewMVC.Listener> {
    public interface Listener {
        void onSharedUnassignedClicked();
        void onSharedOpenClicked();
        void onSharedClosedClicked();
        void onYourUnassignedClicked();
        void onYourOpenClicked();
        void onYourClosedClicked();
        void onTeamUnassignedClicked();
        void onTeamOpenClicked();
        void onTeamClosedClicked();
        void onSharedInboxClicked();
        void onYourInboxClicked();
        void onTeamInboxClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindRoles(Set<String> grants);

    void bindData(TaskCountResponse taskCountResponse);

}
