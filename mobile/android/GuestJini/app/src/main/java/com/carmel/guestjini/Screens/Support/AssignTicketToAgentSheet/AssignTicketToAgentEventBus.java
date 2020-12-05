package com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet;

import com.carmel.guestjini.Common.BaseObservable;

public class AssignTicketToAgentEventBus extends BaseObservable<AssignTicketToAgentEventBus.Listener> {
    public interface Listener {
        void onAssignToAgentTicket(Object event);
    }

    public void postEvent(Object event) {
        for (Listener listener : getListeners()) {
            listener.onAssignToAgentTicket(event);
        }
    }
}
