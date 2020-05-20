package com.carmel.guestjini.Screens.Login;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;

public class LoginEventBus  extends BaseObservable<LoginEventBus.Listener> {
    public interface Listener {
        void onLoginSuccess(Object event);
    }

    public void postEvent(Object event) {
        for (Listener listener : getListeners()) {
            listener.onLoginSuccess(event);
        }
    }
}
