package com.carmel.guestjini.Services.Authentication;

import androidx.annotation.Nullable;

public class AuthServiceHolder {

    AuthService authService = null;

    @Nullable
    public AuthService get() {
        return authService;
    }

    public void set(AuthService authService) {
        this.authService = authService;
    }
}
