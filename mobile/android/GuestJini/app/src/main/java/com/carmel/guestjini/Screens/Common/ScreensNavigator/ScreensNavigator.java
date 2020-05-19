package com.carmel.guestjini.Screens.Common.ScreensNavigator;

import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameHelper;
import com.carmel.guestjini.Screens.Login.LoginFragment;
import com.carmel.guestjini.Screens.Welcome.WelcomeFragment;

public class ScreensNavigator {

    private FragmentFrameHelper mFragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toWelcomeScreen() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(WelcomeFragment.createFragment());
    }


    public void toLoginScreen() {
        mFragmentFrameHelper.replaceFragment(LoginFragment.createFragment());
    }

    public void toSupportHome() {
        //TODO: Implement this method
//        throw new RuntimeException("TODO");
    }
}
