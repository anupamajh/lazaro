package com.carmel.guestjini.Screens.SetPassword;

import com.carmel.guestjini.Networking.GenericResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Screens.OTP.OTPController;
import com.carmel.guestjini.Screens.OTP.OTPViewMVC;
import com.carmel.guestjini.Users.CreateAccountUseCase;
import com.carmel.guestjini.Users.RequestOTPUseCase;
import com.carmel.guestjini.Users.SetPasswordUseCase;
import com.carmel.guestjini.Users.VerifyOTPUseCase;

import java.io.Serializable;

public class SetPasswordController
        implements SetPasswordMVC.Listener,
        SetPasswordUseCase.Listener {
    private enum ScreenState {
        IDLE, SET_PASSWORD, SET_PASSWORD_COMPLETED, NETWORK_ERROR
    }

    private final SetPasswordUseCase setPasswordUseCase;
    private final SharedPreferenceHelper sharedPreferenceHelper;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager mDialogsManager;

    private SetPasswordMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public SetPasswordController(
            SetPasswordUseCase setPasswordUseCase,
            SharedPreferenceHelper sharedPreferenceHelper,
            ScreensNavigator mScreensNavigator,
            DialogsManager mDialogsManager
    ) {
        this.setPasswordUseCase = setPasswordUseCase;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.mScreensNavigator = mScreensNavigator;
        this.mDialogsManager = mDialogsManager;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        viewMVC.bindFullName(sharedPreferenceHelper.readStringValue("full_name"));
        setPasswordUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        setPasswordUseCase.unregisterListener(this);
    }

    public void bindView(SetPasswordMVC viewMVC) {
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
    public void onSubmitClicked(String password) {
        viewMVC.showProgressIndication();
        setPasswordUseCase.setPasswordAndNotify(
                sharedPreferenceHelper.readStringValue("mobile"),
                password
        );
    }

    @Override
    public void onBackPressed() {
        mScreensNavigator.navigateUp();
    }

    @Override
    public void onPasswordSet(GenericResponse genericResponse) {
        viewMVC.hideProgressIndication();
        if (genericResponse.getSuccess()) {
            viewMVC.showPasswordSetMessage();
            mScreensNavigator.toLoginScreen();
        }else{
            viewMVC.showPasswordSetFailedMessage();
        }
    }

    @Override
    public void onPasswordSetFailed() {
        viewMVC.showPasswordSetFailedMessage();
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.showPasswordSetFailedMessage();
    }
}
