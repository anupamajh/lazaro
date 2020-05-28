package com.carmel.guestjini.Screens.Community.CommunityHome;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class CommunityHomeController
        implements CommunityHomeViewMVC.Listener {

    private enum ScreenState {
        IDLE, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private CommunityHomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;


    public CommunityHomeController(
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

    public void bindView(CommunityHomeViewMVC supportHomeViewMVC) {
        viewMVC = supportHomeViewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    @Override
    public void onMyProfileClicked() {
        mScreensNavigator.toMyProfile();
    }

    @Override
    public void onPeopleClicked() {
        mScreensNavigator.toPeopleList();
    }

    @Override
    public void onGroupClicked() {
        mScreensNavigator.toGroupHome();
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }


}
