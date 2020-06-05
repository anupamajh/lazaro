package com.carmel.guestjini.Screens.Community.GroupHome;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class GroupHomeController
        implements GroupHomeViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private GroupHomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;


    public GroupHomeController(
            ScreensNavigator mScreensNavigator
    ) {
        this.mScreensNavigator = mScreensNavigator;
    }

    public void onStart() {
        viewMVC.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
    }

    public void bindView(GroupHomeViewMVC viewMVC) {
        this.viewMVC = viewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    @Override
    public void onInterestGroupClicked() {
        mScreensNavigator.toInterestGroupsScreen();
    }

    @Override
    public void onCommunityGroupClicked() {
        mScreensNavigator.toCommunityGroupsScreen();
    }

    @Override
    public void onMyGroupClicked() {
        mScreensNavigator.toMyGroupsScreen();
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
