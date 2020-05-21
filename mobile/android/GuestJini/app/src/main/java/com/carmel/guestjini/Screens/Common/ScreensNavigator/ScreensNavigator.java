package com.carmel.guestjini.Screens.Common.ScreensNavigator;

import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameHelper;
import com.carmel.guestjini.Screens.Login.LoginFragment;
import com.carmel.guestjini.Screens.Support.KBDetail.KBDetailFragment;
import com.carmel.guestjini.Screens.Support.KBList.KBListFragment;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeFragment;
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
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(LoginFragment.createFragment());
    }

    public void toSupportHome() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(SupportHomeFragment.createFragment());
    }

    public void toCommunityHome() {
        //TODO: Implement this method
    }

    public void toAccountsHome() {
        //TODO: Implement this method
    }

    public void toRewardsHome() {
        //TODO: Implement this method
    }

    public void toSettingsHome() {
        //TODO: Implement this method
    }

    public void toKBList(String searchText) {
        mFragmentFrameHelper.replaceFragment(KBListFragment.createFragment(searchText));
    }

    public void toKBList() {
        mFragmentFrameHelper.replaceFragment(KBListFragment.createFragment(""));
    }

    public void toCreateTicket() {
        //TODO: Implement this method
    }

    public void toTicketList() {
        //TODO: Implement this method
    }

    public void toKBDetails(String kbId) {
        mFragmentFrameHelper.replaceFragment(KBDetailFragment.createFragment(kbId));
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }

}
