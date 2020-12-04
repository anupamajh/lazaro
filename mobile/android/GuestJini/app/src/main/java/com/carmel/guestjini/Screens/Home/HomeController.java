package com.carmel.guestjini.Screens.Home;

import com.carmel.guestjini.Guest.FetchGuestDetailsByPhoneUseCase;
import com.carmel.guestjini.Networking.Guest.GuestResponse;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Users.FetchMyProfilePicUseCase;
import com.carmel.guestjini.Users.FetchMyProfileUseCase;

import java.io.Serializable;

public class HomeController
implements HomeViewMVC.Listener,
        FetchMyProfileUseCase.Listener,
        FetchMyProfilePicUseCase.Listener,
        FetchGuestDetailsByPhoneUseCase.Listener,
        DialogsEventBus.Listener
{
    private enum ScreenState {
        IDLE,
        FETCHING_MY_PROFILE, MY_PROFILE_FETCHED,
        FETCHING_MY_PROFILE_PIC, MY_PROFILE_PIC_FETCHED,
        NETWORK_ERROR
    }

    private final FetchMyProfileUseCase fetchMyProfileUseCase;
    private final FetchMyProfilePicUseCase fetchMyProfilePicUseCase;
    private final FetchGuestDetailsByPhoneUseCase fetchGuestDetailsByPhoneUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private ScreenState mScreenState = ScreenState.IDLE;

    private HomeViewMVC viewMVC;

    public HomeController
            (
                    FetchMyProfileUseCase fetchMyProfileUseCase,
                    FetchMyProfilePicUseCase fetchMyProfilePicUseCase,
                    FetchGuestDetailsByPhoneUseCase fetchGuestDetailsByPhoneUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus) {
        this.fetchMyProfileUseCase = fetchMyProfileUseCase;
        this.fetchMyProfilePicUseCase = fetchMyProfilePicUseCase;
        this.fetchGuestDetailsByPhoneUseCase = fetchGuestDetailsByPhoneUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(HomeViewMVC viewMvc) {
        this.viewMVC = viewMvc;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        fetchMyProfileUseCase.registerListener(this);
        fetchMyProfilePicUseCase.registerListener(this);
        fetchGuestDetailsByPhoneUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchMyProfileAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        fetchMyProfileUseCase.unregisterListener(this);
        fetchMyProfilePicUseCase.unregisterListener(this);
        fetchGuestDetailsByPhoneUseCase.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
    }

    public SavedState getSavedState() {
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

    private void fetchMyProfileAndNotify() {
        viewMVC.showProgressIndication();
        mScreenState = ScreenState.FETCHING_MY_PROFILE;
        fetchMyProfileUseCase.fetchProfileAndNotify();
    }

    @Override
    public void onDialogEvent(Object event) {

    }

    @Override
    public void onProfileFetched(UserInfo response) {
        viewMVC.bindUserInfo(response);
        fetchMyProfilePicUseCase.fetchProfilePicAndNotify();
        fetchGuestDetailsByPhoneUseCase.fetchGuestDetailsAndNotify(response.getPhone());
    }

    @Override
    public void onProfileFetchFailed() {
        viewMVC.hideProgressIndication();
    }

    @Override
    public void onProfilePicFetched(String response) {
        viewMVC.hideProgressIndication();
        viewMVC.bindProfilePic(response);
    }

    @Override
    public void onProfilePicFetchFailed() {
        viewMVC.hideProgressIndication();
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
    }

    @Override
    public void onGuestDetailsFetched(GuestResponse response) {
        viewMVC.hideProgressIndication();
        viewMVC.bindGuestDetails(response);
    }

    @Override
    public void onGuestDetailsFetchFailed() {
        viewMVC.hideProgressIndication();

    }
}
