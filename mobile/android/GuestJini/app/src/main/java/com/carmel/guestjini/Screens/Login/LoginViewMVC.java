package com.carmel.guestjini.Screens.Login;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface LoginViewMVC extends ObservableViewMvc<LoginViewMVC.Listener> {
    public interface Listener{
        void onLoginClicked(String userName, String password);
        void onForgotPasswordClicked();
        void onSignUpClicked();
    }

    boolean isValid();

    void showProgressIndication();

    void hideProgressIndication();

    void showAuthenticationFailure();


}
