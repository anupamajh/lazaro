package com.carmel.guestjini.Screens.Settings.ChangePassword;

import com.carmel.guestjini.Networking.GenericResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Users.ChangePasswordUseCase;

import java.io.Serializable;

public class ChangePasswordController
        implements ChangePasswordViewMVC.Listener,
        DialogsEventBus.Listener,
        ChangePasswordUseCase.Listener {
    private enum ScreenState {
        IDLE,
        CHANGING_PASSWORD, PASSWORD_CHANGED,
        NETWORK_ERROR
    }

    private final ChangePasswordUseCase changePasswordUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private ChangePasswordViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordController
            (
                    ChangePasswordUseCase changePasswordUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.changePasswordUseCase = changePasswordUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(ChangePasswordViewMVC viewMvc) {
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
        changePasswordUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        changePasswordUseCase.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    changePasswordAndNotify(oldPassword, newPassword);
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    private void changePasswordAndNotify(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        viewMVC.showProgressIndication();
        mScreenState = ScreenState.CHANGING_PASSWORD;
        changePasswordUseCase.changePasswordAndNotify(oldPassword, newPassword);
    }

    @Override
    public void onChangePasswordClicked(String oldPassword, String newPassword) {
        changePasswordAndNotify(oldPassword, newPassword);
    }

    @Override
    public void onBackPressed() {
        screensNavigator.navigateUp();
    }


    @Override
    public void onChangePasswordSuccess(GenericResponse response) {
        mScreenState = ScreenState.PASSWORD_CHANGED;
        viewMVC.hideProgressIndication();
        viewMVC.clearFields();
        dialogsManager.showInfoDialog(null, "Change Password", "Password changed successfully!", true);
    }

    @Override
    public void onChangePasswordFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Change Password", null);
    }

    @Override
    public void onNetworkFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "Change Password");
    }


    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
