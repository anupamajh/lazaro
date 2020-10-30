package com.carmel.guestjini.Screens.ForgotPassword;

import com.carmel.guestjini.Authentication.AttemptClientLoginUseCase;
import com.carmel.guestjini.Common.Constants;
import com.carmel.guestjini.Networking.Booking.BookingResponse;
import com.carmel.guestjini.Networking.OTP.OTPResponse;
import com.carmel.guestjini.Networking.Users.AccessToken;
import com.carmel.guestjini.Networking.Users.ForgotPasswordResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Users.CheckPhoneNumberUseCase;
import com.carmel.guestjini.Users.RequestOTPUseCase;
import com.carmel.guestjini.Users.ResetPasswordUseCase;

import java.io.Serializable;

public class ForgotPasswordController implements
        ForgotPasswordViewMVC.Listener,
        ResetPasswordUseCase.Listener,
        AttemptClientLoginUseCase.Listener,
        RequestOTPUseCase.Listener,
        CheckPhoneNumberUseCase.Listener {

    private enum ScreenState {
        IDLE, ATTEMPTING_RESET_PASSWORD, RESET_PASSWORD_COMPLETED, NETWORK_ERROR
    }

    private final AttemptClientLoginUseCase attemptClientLoginUseCase;
    private final RequestOTPUseCase requestOTPUseCase;
    private final CheckPhoneNumberUseCase checkPhoneNumberUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;
    private final SharedPreferenceHelper sharedPreferenceHelper;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager mDialogsManager;

    private ForgotPasswordViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    private String mobileNumber = "";

    public ForgotPasswordController(
            AttemptClientLoginUseCase attemptClientLoginUseCase,
            RequestOTPUseCase requestOTPUseCase,
            CheckPhoneNumberUseCase checkPhoneNumberUseCase,
            ResetPasswordUseCase resetPasswordUseCase,
            SharedPreferenceHelper sharedPreferenceHelper,
            ScreensNavigator mScreensNavigator,
            DialogsManager mDialogsManager
    ) {
        this.checkPhoneNumberUseCase = checkPhoneNumberUseCase;
        this.attemptClientLoginUseCase = attemptClientLoginUseCase;
        this.requestOTPUseCase = requestOTPUseCase;
        this.resetPasswordUseCase = resetPasswordUseCase;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.mScreensNavigator = mScreensNavigator;
        this.mDialogsManager = mDialogsManager;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        resetPasswordUseCase.registerListener(this);
        requestOTPUseCase.registerListener(this);
        checkPhoneNumberUseCase.registerListener(this);
        attemptClientLoginUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        resetPasswordUseCase.unregisterListener(this);
        requestOTPUseCase.unregisterListener(this);
        checkPhoneNumberUseCase.unregisterListener(this);
        attemptClientLoginUseCase.unregisterListener(this);
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
        this.mobileNumber = userName;
        attemptClientLoginUseCase.attemptLoginAndNotify();
    }

    @Override
    public void onLoginSuccess(AccessToken accessToken) {
        boolean hasError = true;
        if (accessToken != null) {
            if (accessToken.getAccessToken() != null) {
                if (accessToken.getAccessToken().trim() != "") {
                    sharedPreferenceHelper.saveStringValue("access_token", accessToken.getAccessToken());
                    sharedPreferenceHelper.saveStringValue("refresh_token", accessToken.getRefreshToken());
                    sharedPreferenceHelper.saveStringValue("token_type", accessToken.getTokenType());
                    sharedPreferenceHelper.saveLongValue("expires_in", accessToken.getExpiresIn());
                    sharedPreferenceHelper.commit();
                    hasError = false;
                    checkPhoneNumberUseCase.checkPhoneNumberAndNotify(mobileNumber);
                }
            }
        }
    }

    @Override
    public void onCheckPhoneNumberSuccess(BookingResponse bookingResponse) {
        if (bookingResponse.isHasUser()) {
            sharedPreferenceHelper.saveStringValue("mobile", mobileNumber);
            sharedPreferenceHelper.commit();
            requestOTPUseCase.sendOTPAndNotify(mobileNumber);
        } else {
            viewMVC.hideProgressIndication();
            viewMVC.showInvalidUser(true);
        }
    }

    @Override
    public void onOTPRequestSuccess(OTPResponse otpResponse) {
        sharedPreferenceHelper.saveStringValue("session_id", otpResponse.getDetails());
        sharedPreferenceHelper.saveStringValue("mobile", mobileNumber);
        sharedPreferenceHelper.commit();
        viewMVC.showOTPSentToast();
        viewMVC.hideProgressIndication();
        mScreensNavigator.toOTPScreen(Constants.SCREEN_FORGOT_PASSWORD);
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

    @Override
    public void onLoginFailed() {
        viewMVC.hideProgressIndication();
        mDialogsManager.showNetworkFailedInfoDialog(null, "Forgot Password");
    }

    @Override
    public void onCheckPhoneNumberFailed() {
        viewMVC.hideProgressIndication();
        mDialogsManager.showNetworkFailedInfoDialog(null, "Forgot Password");
    }

    @Override
    public void onOTPRequestFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showOTPFailedToast();
    }
}
