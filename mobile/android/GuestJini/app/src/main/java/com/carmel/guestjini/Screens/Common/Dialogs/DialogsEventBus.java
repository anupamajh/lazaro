package com.carmel.guestjini.Screens.Common.Dialogs;


import com.carmel.guestjini.Common.BaseObservable;

public class DialogsEventBus extends BaseObservable<DialogsEventBus.Listener> {

    public interface Listener {
        void onDialogEvent(Object event);
    }

    public void postEvent(Object event) {
        for (Listener listener : getListeners()) {
            listener.onDialogEvent(event);
        }
    }

}
