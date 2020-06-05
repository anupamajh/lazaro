package com.carmel.guestjini.Screens.Community.PersonDetail;

import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface PersonDetailViewMVC extends ObservableViewMvc<PersonDetailViewMVC.Listener> {

    public interface Listener {

        void onBackClicked();

        void onIsFavouriteClicked(String personId, Integer isFavourite);
    }

    void bindPerson(PeopleResponse peopleResponse);

    void showProgressIndication();

    void hideProgressIndication();

}
