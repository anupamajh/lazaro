package com.carmel.guestjini.Screens.Welcome;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface WelcomeViewMVC extends ObservableViewMvc<WelcomeViewMVC.Listener> {
    public interface Listener {
        void onNextClicked();
    }
}
