package com.carmel.guestjini.Screens.AppAccessRequest;

import com.carmel.guestjini.Authentication.AttemptClientLoginUseCase;
import com.carmel.guestjini.Networking.Booking.BookingResponse;
import com.carmel.guestjini.Networking.OTP.OTPResponse;
import com.carmel.guestjini.Networking.Users.AccessToken;
import com.carmel.guestjini.Networking.Users.AppAccessRequestResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Users.AppAccessRequestUseCase;
import com.carmel.guestjini.Users.CheckPhoneNumberUseCase;
import com.carmel.guestjini.Users.RequestOTPUseCase;

import java.io.Serializable;

public class AppAccessRequestController implements
        AppAccessRequestViewMVC.Listener,
        AttemptClientLoginUseCase.Listener,
        RequestOTPUseCase.Listener,
        CheckPhoneNumberUseCase.Listener {

    private enum ScreenState {
        IDLE, ATTEMPTING_APP_ACCESS, APP_ACCESS_COMPLETED, NETWORK_ERROR
    }

    private final AttemptClientLoginUseCase attemptClientLoginUseCase;
    private final RequestOTPUseCase requestOTPUseCase;
    private final SharedPreferenceHelper sharedPreferenceHelper;
    private final CheckPhoneNumberUseCase checkPhoneNumberUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager mDialogsManager;

    private AppAccessRequestViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    private String mobileNumber = "";

    public AppAccessRequestController(
            AttemptClientLoginUseCase attemptClientLoginUseCase,
            RequestOTPUseCase requestOTPUseCase,
            CheckPhoneNumberUseCase checkPhoneNumberUseCase,
            SharedPreferenceHelper sharedPreferenceHelper,
            ScreensNavigator mScreensNavigator,
            DialogsManager mDialogsManager
    ) {
        this.checkPhoneNumberUseCase = checkPhoneNumberUseCase;
        this.attemptClientLoginUseCase = attemptClientLoginUseCase;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.mScreensNavigator = mScreensNavigator;
        this.mDialogsManager = mDialogsManager;
        this.requestOTPUseCase = requestOTPUseCase;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        requestOTPUseCase.registerListener(this);
        checkPhoneNumberUseCase.registerListener(this);
        attemptClientLoginUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        requestOTPUseCase.unregisterListener(this);
        checkPhoneNumberUseCase.unregisterListener(this);
        attemptClientLoginUseCase.unregisterListener(this);
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
    public void onAppAccessRequestClicked(String mobile) {
        viewMVC.showProgressIndication();
        this.mobileNumber = mobile;
        attemptClientLoginUseCase.attemptLoginAndNotify();
    }

    @Override
    public void onBackPressed() {
        mScreensNavigator.navigateUp();
    }

    @Override
    public void onNetworkFailed() {
        viewMVC.hideProgressIndication();
        mDialogsManager.showNetworkFailedInfoDialog(null, "App access");
    }

    @Override
    public void onCheckPhoneNumberSuccess(BookingResponse bookingResponse) {
        if (bookingResponse.isSuccess()) {
            if (bookingResponse.isHasUser()) {
                viewMVC.showHasUser();
            } else {
                if (bookingResponse.isHasBooking()) {
                    requestOTPUseCase.sendOTPAndNotify(mobileNumber);
                } else {
                    viewMVC.showNoBooking();
                }
            }

        } else {
            viewMVC.showInvalidPhoneNumberError(true);
        }
    }

    @Override
    public void onCheckPhoneNumberFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showOTPFailedToast();
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
    public void onLoginFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showOTPFailedToast();
    }

    @Override
    public void onOTPRequestSuccess(OTPResponse otpResponse) {
        sharedPreferenceHelper.saveStringValue("session_id", otpResponse.getDetails());
        sharedPreferenceHelper.commit();
        viewMVC.showOTPSentToast();
        viewMVC.hideProgressIndication();
        mScreensNavigator.toOTPScreen();
    }

    @Override
    public void onOTPRequestFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showOTPFailedToast();

    }
}
