package com.carmel.guestjini.Screens.AppAccessRequest;

import com.carmel.guestjini.Networking.Users.AppAccessRequestResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Users.AppAccessRequestUseCase;

import java.io.Serializable;

public class AppAccessRequestController implements
        AppAccessRequestViewMVC.Listener,
        AppAccessRequestUseCase.Listener {

    private enum ScreenState {
        IDLE, ATTEMPTING_APP_ACCESS, APP_ACCESS_COMPLETED, NETWORK_ERROR
    }

    private final AppAccessRequestUseCase appAccessRequestUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager mDialogsManager;

    private AppAccessRequestViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public AppAccessRequestController(
            AppAccessRequestUseCase appAccessRequestUseCase,
            ScreensNavigator mScreensNavigator,
            DialogsManager mDialogsManager
    ) {
        this.appAccessRequestUseCase = appAccessRequestUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mDialogsManager = mDialogsManager;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        appAccessRequestUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        appAccessRequestUseCase.unregisterListener(this);
    }

    public void bindView(AppAccessRequestViewMVC viewMVC) {
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
    public void onAppAccessRequestClicked(String email, String mobile) {
        viewMVC.showProgressIndication();
        appAccessRequestUseCase.requestAppAccessAndNotify(mobile, email);
    }

    @Override
    public void onAppAccessRequestSuccess(AppAccessRequestResponse appAccessRequestResponse) {
        viewMVC.hideProgressIndication();
        mDialogsManager.showInfoDialog(null, "App access Request", "We have sent instructions to your email!", true);
    }

    @Override
    public void onAppAccessRequestFailed() {
        viewMVC.hideProgressIndication();
        mDialogsManager.showUseCaseFailedDialog("App access", null);
    }

    @Override
    public void onBackPressed() {
        mScreensNavigator.navigateUp();
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
        mDialogsManager.showNetworkFailedInfoDialog(null, "Forgot Password");
    }
}
