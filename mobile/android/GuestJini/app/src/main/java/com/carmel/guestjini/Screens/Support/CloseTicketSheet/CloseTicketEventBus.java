package com.carmel.guestjini.Screens.Support.CloseTicketSheet;

import com.carmel.guestjini.Common.BaseObservable;

public class CloseTicketEventBus extends BaseObservable<CloseTicketEventBus.Listener> {
    public interface Listener {
        void onCloseTicket(Object event);
    }

    public void postEvent(Object event) {
        for (Listener listener : getListeners()) {
            listener.onCloseTicket(event);
        }
    }
}
