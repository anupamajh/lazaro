package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Screens.Login.LoginEventBus;

public class AssignTicketEventBus  extends BaseObservable<AssignTicketEventBus.Listener> {
    public interface Listener {
        void onAssignTicket(Object event);
    }

    public void postEvent(Object event) {
        for (Listener listener : getListeners()) {
            listener.onAssignTicket(event);
        }
    }
}
