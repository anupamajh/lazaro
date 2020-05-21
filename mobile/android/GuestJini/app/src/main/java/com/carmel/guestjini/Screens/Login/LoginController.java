package com.carmel.guestjini.Screens.Login;

import com.carmel.guestjini.Authentication.AttemptLoginUseCase;
import com.carmel.guestjini.Networking.Users.AccessToken;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;

import java.io.Serializable;

public class LoginController implements
        LoginViewMVC.Listener,
        AttemptLoginUseCase.Listener
{

    private enum ScreenState {
        IDLE, ATTEMPTING_LOGIN, LOGIN_COMPLETED, NETWORK_ERROR
    }

    private final ScreensNavigator mScreensNavigator;
    private final SharedPreferenceHelper sharedPreferenceHelper;
    private final AttemptLoginUseCase attemptLoginUseCase;
    private final DialogsManager mDialogsManager;
    private ViewMVCFactory viewMVCFactory;

    private LoginViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;

    private LoginEventBus loginEventBus;

    public LoginController(
            ScreensNavigator mScreensNavigator,
            AttemptLoginUseCase attemptLoginUseCase,
            SharedPreferenceHelper sharedPreferenceHelper,
            DialogsManager mDialogsManager,
            ViewMVCFactory viewMVCFactory,
            LoginEventBus loginEventBus
    ) {
        this.mScreensNavigator = mScreensNavigator;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.attemptLoginUseCase = attemptLoginUseCase;
        this.mDialogsManager = mDialogsManager;
        this.viewMVCFactory = viewMVCFactory;
        this.loginEventBus = loginEventBus;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        attemptLoginUseCase.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        attemptLoginUseCase.unregisterListener(this);
    }

    public void bindView(LoginViewMVC loginViewMVC) {
        viewMVC = loginViewMVC;
    }


    public Serializable getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedSate(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }


    @Override
    public void onLoginClicked(String userName, String password) {
        if (viewMVC.isValid()) {
            viewMVC.showProgressIndication();
            attemptLoginUseCase.attemptLoginAndNotify(userName, password);
        }
    }

    @Override
    public void onForgotPasswordClicked() {

    }

    @Override
    public void onSignUpClicked() {

    }

    @Override
    public void onLoginSuccess(AccessToken accessToken) {
        viewMVC.hideProgressIndication();
        boolean hasError = true;
        if (accessToken != null) {
            if (accessToken.getAccessToken() != null) {
                if (accessToken.getAccessToken().trim() != "") {
                    sharedPreferenceHelper.saveBooleanValue("isLoggedIn", true);
                    sharedPreferenceHelper.saveStringValue("access_token", accessToken.getAccessToken());
                    sharedPreferenceHelper.saveStringValue("refresh_token", accessToken.getRefreshToken());
                    sharedPreferenceHelper.saveStringValue("token_type", accessToken.getTokenType());
                    sharedPreferenceHelper.saveLongValue("expires_in", accessToken.getExpiresIn());
                    sharedPreferenceHelper.commit();
                    hasError = false;
                }
            }
        }
        if(hasError){
            sharedPreferenceHelper.saveBooleanValue("isLoggedIn", false);
            viewMVC.showAuthenticationFailure();
        }else{
            mScreensNavigator.toSupportHome();
            loginEventBus.postEvent(null);
        }

    }

    @Override
    public void onLoginFailed() {
        viewMVC.hideProgressIndication();
        viewMVC.showAuthenticationFailure();
    }

    @Override
    public void onNetworkFailed() {
        mDialogsManager.showNetworkFailedInfoDialog(null, "Login");
    }
}
