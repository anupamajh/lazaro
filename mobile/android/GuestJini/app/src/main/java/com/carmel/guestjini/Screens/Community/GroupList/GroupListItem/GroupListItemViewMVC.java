package com.carmel.guestjini.Screens.Community.GroupList.GroupListItem;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface GroupListItemViewMVC extends ObservableViewMvc<GroupListItemViewMVC.Listener> {

    public interface Listener {
        void onGroupClicked(Group group);
        void onGroupDetailClicked(Group group);
    }

    void bindGroup(Group group);
}
