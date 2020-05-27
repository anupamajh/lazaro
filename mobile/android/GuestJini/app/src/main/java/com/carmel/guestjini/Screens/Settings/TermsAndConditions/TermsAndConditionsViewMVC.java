package com.carmel.guestjini.Screens.Settings.TermsAndConditions;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface TermsAndConditionsViewMVC  extends ObservableViewMvc<TermsAndConditionsViewMVC.Listener> {

    public interface Listener {
        void onBackPressed();
    }
}
