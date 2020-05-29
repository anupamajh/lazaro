package com.carmel.guestjini.Screens.ForgotPassword;

import com.carmel.guestjini.Networking.Users.ForgotPasswordResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Users.ResetPasswordUseCase;

import java.io.Serializable;

public class ForgotPasswordController implements
        ForgotPasswordViewMVC.Listener,
        ResetPasswordUseCase.Listener {

    private enum ScreenState {
        IDLE, ATTEMPTING_RESET_PASSWORD, RESET_PASSWORD_COMPLETED, NETWORK_ERROR
    }

    private final ResetPasswordUseCase resetPasswordUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager mDialogsManager;

    private ForgotPasswordViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public ForgotPasswordController(
            ResetPasswordUseCase resetPasswordUseCase,
            ScreensNavigator mScreensNavigator,
            DialogsManager mDialogsManager
    ) {
        this.resetPasswordUseCase = resetPasswordUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mDialogsManager = mDialogsManager;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        resetPasswordUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        resetPasswordUseCase.unregisterListener(this);
    }

    public void bindView(ForgotPasswordViewMVC viewMVC) {
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
    public void onForgotPasswordClicked(String userName) {
        viewMVC.showProgressIndication();
        resetPasswordUseCase.resetPasswordAndNotify(userName);
    }

    @Override
    public void onAppAccessRequestClicked() {
        mScreensNavigator.toAppAccessRequestScreen();
    }

    @Override
    public void onBackPressed() {
        mScreensNavigator.navigateUp();
    }

    @Override
    public void onPasswordReset(ForgotPasswordResponse response) {
        viewMVC.hideProgressIndication();
        mDialogsManager.showInfoDialog(null, "App access Request", "We have sent instructions to your email!", true);

    }

    @Override
    public void onPasswordResetFailed() {
        viewMVC.hideProgressIndication();
        mDialogsManager.showUseCaseFailedDialog("Forgot Password", null);
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
        mDialogsManager.showNetworkFailedInfoDialog(null, "Forgot Password");
    }
}
