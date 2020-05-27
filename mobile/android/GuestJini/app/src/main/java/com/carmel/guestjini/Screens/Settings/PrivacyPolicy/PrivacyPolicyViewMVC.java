package com.carmel.guestjini.Screens.Settings.PrivacyPolicy;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface PrivacyPolicyViewMVC  extends ObservableViewMvc<PrivacyPolicyViewMVC.Listener> {

    public interface Listener {
        void onBackPressed();
    }
}
