package com.carmel.guestjini.Screens.Community.CreateGroup;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface CreateGroupViewMVC extends ObservableViewMvc<CreateGroupViewMVC.Listener> {
    public interface Listener {
        void onCreateGroupClicked(String name, String description);

        void onBackClicked();
    }


    void showProgressIndication();

    void hideProgressIndication();


}