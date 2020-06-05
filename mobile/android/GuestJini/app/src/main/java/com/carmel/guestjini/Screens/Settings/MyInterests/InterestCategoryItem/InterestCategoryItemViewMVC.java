package com.carmel.guestjini.Screens.Settings.MyInterests.InterestCategoryItem;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface InterestCategoryItemViewMVC extends ObservableViewMvc<InterestCategoryItemViewMVC.Listener> {

    public interface Listener {
        void onInterestCategoryClicked(InterestCategory interestCategory);
    }

    void bindInterestCategory(InterestCategory interestCategory);

    void bindInterests(List<Interest> interestList);
}
