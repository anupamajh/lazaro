package com.carmel.android.guestjini.domain.User;

import com.carmel.android.guestjini.models.User.AuthData;

public interface AuthenticationBean {
    AuthData attemptLogin(String userName, String password);
}
