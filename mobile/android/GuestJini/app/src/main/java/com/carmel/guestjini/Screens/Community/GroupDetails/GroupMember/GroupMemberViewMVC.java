package com.carmel.guestjini.Screens.Community.GroupDetails.GroupMember;

import com.carmel.guestjini.Networking.Users.AddressBook;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface GroupMemberViewMVC extends ObservableViewMvc<GroupMemberViewMVC.Listener> {
    public interface Listener{
        void onInviteClicked(String userId, String groupId);
    }

    void bingGroupMember(AddressBook addressBook,
                         int isSubscribed,
                         int isInvited,
                         String groupId,
                         int groupType
    );
}
