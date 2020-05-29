package com.carmel.guestjini.Components;

public interface TokenManager {
    String getToken();

    boolean hasToken();

    void clearToken();

    String refreshToken();
}
