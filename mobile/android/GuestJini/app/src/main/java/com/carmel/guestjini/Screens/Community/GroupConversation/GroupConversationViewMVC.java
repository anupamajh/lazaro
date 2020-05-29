package com.carmel.guestjini.Screens.Community.GroupConversation;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Networking.Group.GroupConversation;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface GroupConversationViewMVC extends ObservableViewMvc<GroupConversationViewMVC.Listener> {
    public interface Listener {
        void onSendMessageClicked(String groupId, String message);

        void onInfoClicked(String groupId, int groupType);

        void onBackClicked();
    }

    void bindGroupConversations(List<GroupConversation> groupConversations);

    void bindGroup(Group group, int groupType);

    void clearMessage();

    void showProgressIndication();

    void hideProgressIndication();
}
