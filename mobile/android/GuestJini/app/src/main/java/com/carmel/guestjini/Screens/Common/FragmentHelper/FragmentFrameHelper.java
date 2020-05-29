package com.carmel.guestjini.Screens.Common.FragmentHelper;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentFrameHelper {

    private final Activity mActivity;
    private final FragmentFrameWrapper mFragmentFrameWrapper;
    private final FragmentManager mFragmentManager;

    public FragmentFrameHelper(
            Activity activity,
            FragmentFrameWrapper fragmentFrameWrapper,
            FragmentManager fragmentManager
    ) {
        mActivity = activity;
        mFragmentFrameWrapper = fragmentFrameWrapper;
        mFragmentManager = fragmentManager;
    }

    public void replaceFragment(Fragment newFragment) {
        replaceFragment(newFragment, true, false);
    }


    public void replaceFragmentDontAddToBackstack(Fragment newFragment) {
        replaceFragment(newFragment, false, false);
    }

    public void replaceFragmentAndClearBackstack(Fragment newFragment) {
        replaceFragment(newFragment, false, true);
    }

    public void navigateUp() {

        // Some navigateUp calls can be "lost" if they happen after the state has been saved
        if (mFragmentManager.isStateSaved()) {
            return;
        }

        Fragment currentFragment = getCurrentFragment();

        if (mFragmentManager.getBackStackEntryCount() > 0) {
            removeCurrentFragment();
            if (mFragmentManager.popBackStackImmediate()) {
                return;
            }
        }

        if (HierarchicalFragment.class.isInstance(currentFragment)) {
            Fragment parentFragment =
                    ((HierarchicalFragment) currentFragment).getHierarchicalParentFragment();
            if (parentFragment != null) {
                replaceFragment(parentFragment, false, true);
                return;
            }
        }

        if (mActivity.onNavigateUp()) {
            return;
        }

        mActivity.onBackPressed();
    }

    private Fragment getCurrentFragment() {
        return mFragmentManager.findFragmentById(getFragmentFrameId());
    }

    private void replaceFragment(Fragment newFragment, boolean addToBackStack, boolean clearBackStack) {
        if (clearBackStack) {
            if (mFragmentManager.isStateSaved()) {
                return;
            }
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction ft = mFragmentManager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(null);
        }

        ft.replace(getFragmentFrameId(), newFragment, null);

        if (mFragmentManager.isStateSaved()) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }
    }

    private void removeCurrentFragment() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.remove(getCurrentFragment());
        ft.commit();
    }

    private int getFragmentFrameId() {
        return mFragmentFrameWrapper.getFragmentFrame().getId();
    }

}
