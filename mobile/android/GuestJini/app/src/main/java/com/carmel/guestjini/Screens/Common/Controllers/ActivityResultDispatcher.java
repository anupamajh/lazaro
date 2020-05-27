package com.carmel.guestjini.Screens.Common.Controllers;

public interface ActivityResultDispatcher {
    void registerListener(ActivityResultListener listener);
    void unregisterListener(ActivityResultListener listener);
}
