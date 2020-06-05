package com.carmel.guestjini.Networking.Common;

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
