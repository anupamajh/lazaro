package com.carmel.guestjini.Screens.OTP;

import com.carmel.guestjini.Networking.OTP.OTPResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Users.RequestOTPUseCase;
import com.carmel.guestjini.Users.VerifyOTPUseCase;

import java.io.Serializable;

public class OTPController
        implements OTPViewMVC.Listener,
        VerifyOTPUseCase.Listener,
        RequestOTPUseCase.Listener {
    private enum ScreenState {
        IDLE, VERIFY_OTP, VERIFY_OTP_COMPLETED, NETWORK_ERROR
    }

    private final VerifyOTPUseCase verifyOTPUseCase;
    private final RequestOTPUseCase requestOTPUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager mDialogsManager;
    private final SharedPreferenceHelper sharedPreferenceHelper;

    private OTPViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    public OTPController(
            VerifyOTPUseCase verifyOTPUseCase,
            RequestOTPUseCase requestOTPUseCase,
            SharedPreferenceHelper sharedPreferenceHelper,
            ScreensNavigator mScreensNavigator,
            DialogsManager mDialogsManager
    ) {
        this.verifyOTPUseCase = verifyOTPUseCase;
        this.requestOTPUseCase = requestOTPUseCase;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.mScreensNavigator = mScreensNavigator;
        this.mDialogsManager = mDialogsManager;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        verifyOTPUseCase.registerListener(this);
        requestOTPUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        verifyOTPUseCase.unregisterListener(this);
        requestOTPUseCase.unregisterListener(this);
    }

    public void bindView(OTPViewMVC viewMVC) {
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
    public void onVerifyOTPClicked(String otp) {
        viewMVC.showInvalidOTP(false);
        viewMVC.showProgressIndication();
        verifyOTPUseCase.verifyOTPAndNotify(
                sharedPreferenceHelper.readStringValue("session_id"),
                otp
        );
    }

    @Override
    public void onResendOTPClicked() {
        viewMVC.showProgressIndication();
        requestOTPUseCase.sendOTPAndNotify(
                sharedPreferenceHelper.readStringValue("mobile")
        );
    }

    @Override
    public void onBackPressed() {
        mScreensNavigator.navigateUp();
    }

    @Override
    public void onOTPVerificationSuccess(OTPResponse otpResponse) {

        viewMVC.hideProgressIndication();
//ToDo: Show set password screen
    }

    @Override
    public void onOTPVerificationFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showInvalidOTP(true);
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showNetworkError();
    }

    @Override
    public void onOTPRequestSuccess(OTPResponse otpResponse) {
        sharedPreferenceHelper.saveStringValue("session_id", otpResponse.getDetails());
        sharedPreferenceHelper.commit();
        viewMVC.showOTPSentToast();
        viewMVC.hideProgressIndication();

    }

    @Override
    public void onOTPRequestFailed() {
        viewMVC.showOTPFailedToast();
    }
}
