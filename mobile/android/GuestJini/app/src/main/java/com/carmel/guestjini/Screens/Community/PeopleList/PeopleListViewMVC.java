package com.carmel.guestjini.Screens.Community.PeopleList;

import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Networking.Users.Person;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;


public interface PeopleListViewMVC
        extends ObservableViewMvc<PeopleListViewMVC.Listener> {
    public interface Listener {
        void onPersonClicked(Person person);
        void onFavouriteClicked(Person person, int isFavourite);
        void onBackClicked();
    }

    void bindPeopleResponse(PeopleResponse peopleResponse);

    void showProgressIndication();

    void hideProgressIndication();
}
