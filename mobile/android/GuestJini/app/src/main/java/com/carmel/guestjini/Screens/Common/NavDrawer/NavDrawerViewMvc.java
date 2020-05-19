package com.carmel.guestjini.Screens.Common.NavDrawer;

import android.widget.FrameLayout;

public interface NavDrawerViewMvc {
    interface Listener {
    }

    FrameLayout getFragmentFrame();
    boolean isDrawerOpen();
    void openDrawer();
    void closeDrawer();
}
