package com.carmel.guestjini.Screens.Support.Inbox;

import com.carmel.guestjini.Common.Search.SearchRequest;
import com.carmel.guestjini.Networking.Tickets.InboxCount;
import com.carmel.guestjini.Networking.Tickets.TaskCountResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Tickets.FetchInboxCountUseCase;

import java.io.Serializable;
import java.util.Set;

public class InboxController
        implements InboxViewMVC.Listener,
        FetchInboxCountUseCase.Listener,
        DialogsEventBus.Listener {
    private enum ScreenState {
        IDLE, FETCHING_INBOX_COUNT, FETCHED_INBOX_COUNT, FETCHING_INBOX_COUNT_FAILED, NETWORK_FAILED
    }

    private final FetchInboxCountUseCase fetchInboxCountUseCase;
    private final SharedPreferenceHelper sharedPreferenceHelper;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private final ScreensNavigator screensNavigator;

    private InboxViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;


    public InboxController(
            FetchInboxCountUseCase fetchInboxCountUseCase,
            SharedPreferenceHelper sharedPreferenceHelper,
            ScreensNavigator screensNavigator,
            DialogsManager dialogsManager,
            DialogsEventBus dialogsEventBus
    ) {
        this.fetchInboxCountUseCase = fetchInboxCountUseCase;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        fetchInboxCountUseCase.registerListener(this);
        viewMVC.showProgressIndication();
        Set<String> grants = sharedPreferenceHelper.readStringSetValue("user_grants");
        viewMVC.bindRoles(grants);
        if (mScreenState == ScreenState.IDLE) {
            fetchInboxCountUseCase.fetchTaskCountAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        fetchInboxCountUseCase.unregisterListener(this);
    }

    public void bindView(InboxViewMVC viewMVC) {
        this.viewMVC = viewMVC;

    }

    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }

    @Override
    public void onDialogEvent(Object event) {

    }

    @Override
    public void onSharedUnassignedClicked() {

    }

    @Override
    public void onSharedOpenClicked() {

    }

    @Override
    public void onSharedClosedClicked() {

    }

    @Override
    public void onYourUnassignedClicked() {

    }

    @Override
    public void onYourOpenClicked() {

    }

    @Override
    public void onYourClosedClicked() {

    }

    @Override
    public void onTeamUnassignedClicked() {

    }

    @Override
    public void onTeamOpenClicked() {

    }

    @Override
    public void onTeamClosedClicked() {

    }

    @Override
    public void onSharedInboxClicked() {
        screensNavigator.toInboxTicketList(1);
    }

    @Override
    public void onYourInboxClicked() {
        screensNavigator.toInboxTicketList(2);
    }

    @Override
    public void onTeamInboxClicked() {
        screensNavigator.toInboxTicketList(3);
    }

    @Override
    public void onInboxCountFetched(InboxCount inboxCount) {
        viewMVC.hideProgressIndication();
        viewMVC.bindData(inboxCount);
    }

    @Override
    public void onTicketCountFetchFailed() {
        viewMVC.hideProgressIndication();
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
    }
}
