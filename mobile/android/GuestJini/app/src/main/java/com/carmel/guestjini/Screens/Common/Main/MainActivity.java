package com.carmel.guestjini.Screens.Common.Main;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCView;
import com.carmel.guestjini.Screens.Common.Controllers.BackPressDispatcher;
import com.carmel.guestjini.Screens.Common.Controllers.BackPressedListener;
import com.carmel.guestjini.Screens.Common.Controllers.BaseActivity;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameWrapper;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements
        BackPressDispatcher,
        FragmentFrameWrapper {

    private final Set<BackPressedListener> mBackPressedListeners = new HashSet<>();
    private ScreensNavigator mScreensNavigator;
    private BaseActivityMVCView viewMVC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        viewMVC = getCompositionRoot().getViewMVCFactory().getBaseActivityView(null);
        setContentView(viewMVC.getRootView());
        if(savedInstanceState == null){
            mScreensNavigator.toWelcomeScreen();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void registerListener(BackPressedListener listener) {

    }

    @Override
    public void unregisterListener(BackPressedListener listener) {

    }

    @Override
    public FrameLayout getFragmentFrame() {
        return viewMVC.getFragmentFrame();
    }
}
