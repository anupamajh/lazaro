package com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationItem;


import com.carmel.guestjini.Networking.Group.GroupConversation;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface GroupConversationItemViewMVC extends ObservableViewMvc<GroupConversationItemViewMVC.Listener> {

    public interface Listener {
    }

    void bindGroupConversation(GroupConversation groupConversation);
}
