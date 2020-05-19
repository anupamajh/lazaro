package com.carmel.guestjini.Screens.Common.BaseActivityView;

import android.widget.FrameLayout;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface BaseActivityMVCView extends ObservableViewMvc<BaseActivityMVCView.Listener> {
    interface Listener {
    }

    FrameLayout getFragmentFrame();
}
