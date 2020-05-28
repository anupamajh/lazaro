package com.carmel.guestjini.Screens.Community.GroupList;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface GroupListViewMVC extends ObservableViewMvc<GroupListViewMVC.Listener> {
    public interface Listener {
        void onGroupClicked(Group group);
        void onBackClicked();
    }

    void bindGroups(List<Group> groupList);

    void showProgressIndication();

    void hideProgressIndication();
}
