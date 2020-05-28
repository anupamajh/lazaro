package com.carmel.guestjini.Screens.Community.GroupDetails;

import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface GroupDetailsViewMVC extends ObservableViewMvc<GroupDetailsViewMVC.Listener> {
    public interface Listener {
        void onInviteClicked(String userId, String groupId);

        void onBackClicked();
    }

    void bindGroupResponse(GroupResponse groupResponse);

    void showProgressIndication();

    void hideProgressIndication();
}
