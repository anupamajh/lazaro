package com.carmel.guestjini.Screens.Support.SupportHome;

import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.io.Serializable;

public class SupportHomeController implements
        SupportHomeViewMVC.Listener {

    private enum ScreenState {
        IDLE, FETCHING_QUESTIONS, QUESTIONS_LIST_SHOWN, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;

    private SupportHomeViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;


    public SupportHomeController(
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

    public void bindView(SupportHomeViewMVC supportHomeViewMVC) {
        viewMVC = supportHomeViewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedSate(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }


    @Override
    public void onKBSearchClicked(String searchText) {
        mScreensNavigator.toKBList(searchText);
    }

    @Override
    public void onKBExploreClicked() {
        mScreensNavigator.toKBList();
    }

    @Override
    public void onCreateTicketClicked() {
        mScreensNavigator.toCreateTicket();
    }

    @Override
    public void onGotoTicketsClicked() {
        mScreensNavigator.toTicketList();
    }
}
