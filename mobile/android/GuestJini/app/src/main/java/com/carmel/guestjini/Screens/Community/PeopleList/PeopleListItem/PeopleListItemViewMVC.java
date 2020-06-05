package com.carmel.guestjini.Screens.Community.PeopleList.PeopleListItem;

import com.carmel.guestjini.Networking.Users.Person;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface PeopleListItemViewMVC extends ObservableViewMvc<PeopleListItemViewMVC.Listener> {
    public interface Listener {
        void onPersonClicked(Person person);

        void onFavouriteClicked(Person person, int isFavourite);
    }

    void bindPerson(Person person, int totalInterestCount);
}
