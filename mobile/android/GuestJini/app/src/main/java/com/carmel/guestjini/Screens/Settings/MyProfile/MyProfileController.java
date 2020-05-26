package com.carmel.guestjini.Screens.Settings.MyProfile;

import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Users.FetchMyProfileUseCase;

import java.io.Serializable;

public class MyProfileController
        implements MyProfileViewMVC.Listener,
        DialogsEventBus.Listener, FetchMyProfileUseCase.Listener {
    private enum ScreenState {
        IDLE,
        FETCHING_MY_PROFILE, MY_PROFILE_FETCHED,
        NETWORK_ERROR
    }

    private final FetchMyProfileUseCase fetchMyProfileUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private MyProfileViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;


    public MyProfileController
            (
                    FetchMyProfileUseCase fetchMyProfileUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.fetchMyProfileUseCase = fetchMyProfileUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(MyProfileViewMVC viewMvc) {
        this.viewMVC = viewMvc;
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        fetchMyProfileUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchMyProfileAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        fetchMyProfileUseCase.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
    }

    private void fetchMyProfileAndNotify() {
        viewMVC.showProgressIndication();
        mScreenState = ScreenState.FETCHING_MY_PROFILE;
        fetchMyProfileUseCase.fetchProfileAndNotify();
    }

    @Override
    public void onProfileFetched(UserInfo response) {
        viewMVC.hideProgressIndication();
        mScreenState = ScreenState.MY_PROFILE_FETCHED;
        viewMVC.bindMyProfile(response);
    }

    @Override
    public void onProfileFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("My Profile", null);
    }

    @Override
    public void onNetworkFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "My Profile");
    }


    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onUserPreferenceChange(int preferenceType, boolean isVisible) {

    }

    @Override
    public void onShowInterestsClicked() {
        screensNavigator.toMyInterests();
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchMyProfileAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }


    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
