package com.carmel.guestjini.Screens.Settings.MyInterests;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface MyInterestViewMVC
        extends ObservableViewMvc<MyInterestViewMVC.Listener> {

    void bindUserInterests(List<UserInterests> response);

    void showUserInterestSaved();

    public interface Listener {
        void onBackClicked();

        void onInterestClicked(Interest interest, int isInterested);
    }

    void bindInterestCategories(List<InterestCategory> interestCategoryList, List<UserInterests> userInterests);

    void showProgressIndication();

    void hideProgressIndication();

}
