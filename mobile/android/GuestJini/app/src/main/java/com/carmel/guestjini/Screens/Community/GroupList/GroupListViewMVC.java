package com.carmel.guestjini.Screens.Community.GroupList;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface GroupListViewMVC extends ObservableViewMvc<GroupListViewMVC.Listener> {

    public interface Listener {
        void onGroupClicked(Group group);

        void onBackClicked();

        void onNewGroupClicked();

        void onGroupDetailClicked(Group group);
    }

    void bindGroups(List<Group> groupList);

    void hideCreate();

    void showCreate();

    void showProgressIndication();

    void hideProgressIndication();
}
