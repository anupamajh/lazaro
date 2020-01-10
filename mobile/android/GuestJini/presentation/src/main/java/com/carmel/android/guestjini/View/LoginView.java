package com.carmel.android.guestjini.View;

import com.carmel.android.guestjini.models.User.AuthData;

public interface LoginView {

    void attemptLogin(String userName, String password);
    void processLogin(AuthData authData);
}
