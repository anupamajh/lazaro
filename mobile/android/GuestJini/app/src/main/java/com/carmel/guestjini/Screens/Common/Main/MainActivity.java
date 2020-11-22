package com.carmel.guestjini.Screens.Common.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCView;
import com.carmel.guestjini.Screens.Common.Controllers.ActivityResultDispatcher;
import com.carmel.guestjini.Screens.Common.Controllers.ActivityResultListener;
import com.carmel.guestjini.Screens.Common.Controllers.BackPressDispatcher;
import com.carmel.guestjini.Screens.Common.Controllers.BackPressedListener;
import com.carmel.guestjini.Screens.Common.Controllers.BaseActivity;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameWrapper;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Screens.Login.LoginEventBus;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements
        BackPressDispatcher,
        ActivityResultDispatcher,
        FragmentFrameWrapper,
        BaseActivityMVCView.Listener,
        LoginEventBus.Listener {

    private final Set<BackPressedListener> mBackPressedListeners = new HashSet<>();
    private final Set<ActivityResultListener> activityResultListeners = new HashSet<>();
    private ScreensNavigator mScreensNavigator;
    private BaseActivityMVCView viewMVC;
    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        getCompositionRoot().getLoginEventBus().registerListener(this);
        viewMVC = getCompositionRoot().getViewMVCFactory().getBaseActivityView(null);
        viewMVC.registerListener(this);
        sharedPreferenceHelper = getCompositionRoot().getSharedPreferenceHelper();
        setContentView(viewMVC.getRootView());
        if (savedInstanceState == null) {
            if (sharedPreferenceHelper.readBooleanValue("isLoggedIn")) {
                mScreensNavigator.toHome();
                viewMVC.showBottomNavigationView();
                setupGrants();
            } else {
                mScreensNavigator.toLoginScreen();
                viewMVC.hideBottomNavigationView();
            }
        }

    }

    @Override
    public void onHomeClicked() {
        mScreensNavigator.toHome();
    }

    @Override
    public void onNavigationItemClicked() {
        setupGrants();
    }

    @Override
    public void onSupportClicked() {
        mScreensNavigator.toSupportHome();
    }

    @Override
    public void onCommunityClicked() {
        mScreensNavigator.toPeopleList();
    }

    @Override
    public void onAccountsClicked() {
        mScreensNavigator.toAccountsHome();
    }

    @Override
    public void onRewardsClicked() {
        mScreensNavigator.toRewardsHome();
    }

    @Override
    public void onSettingsClicked() {
        mScreensNavigator.toSettingsHome();
    }

    @Override
    public void onInboxClicked() {
        mScreensNavigator.toInboxScreen();
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
        mBackPressedListeners.add(listener);
    }

    @Override
    public void unregisterListener(BackPressedListener listener) {
        mBackPressedListeners.add(listener);
    }

    @Override
    public void registerListener(ActivityResultListener listener) {
        activityResultListeners.add(listener);
    }

    @Override
    public void unregisterListener(ActivityResultListener listener) {
        activityResultListeners.remove(listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        boolean isActivityListenerConsumed = false;
        for (ActivityResultListener listener : activityResultListeners) {
            listener.onActivityResult(requestCode, resultCode, data);
            isActivityListenerConsumed = true;
        }
        if (isActivityListenerConsumed) {
            return;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onLoginSuccess(Object event) {
        if (event instanceof Integer) {
            switch (((Integer) event)) {
                case 1:
                    viewMVC.showBottomNavigationView();
                    viewMVC.setHomeSelected();
                    setupGrants();
                    break;
                case 0:
                    viewMVC.hideBottomNavigationView();
                    break;
            }
        }

    }



    private void setupGrants() {
        hideAllBottomMenus();
        Set<String> grants = sharedPreferenceHelper.readStringSetValue("user_grants");
        findViewById(R.id.homeIcon).setVisibility(View.VISIBLE);
        findViewById(R.id.supportIcon).setVisibility(View.VISIBLE);
        findViewById(R.id.settingsIcon).setVisibility(View.GONE);
        if (grants.contains("ROLE_GUEST")) {
            findViewById(R.id.communityIcon).setVisibility(View.VISIBLE);
        }
        if (grants.contains("ROLE_GUEST_SUPPORT")) {
            findViewById(R.id.inboxIcon).setVisibility(View.VISIBLE);
//            findViewById(R.id.cConnectIcon).setVisibility(View.VISIBLE);
//            findViewById(R.id.teamIcon).setVisibility(View.VISIBLE);
        }
    }

    private void hideAllBottomMenus() {
//        findViewById(R.id.homeIcon).setVisibility(View.GONE);
//        findViewById(R.id.supportIcon).setVisibility(View.GONE);
//        findViewById(R.id.communityIcon).setVisibility(View.GONE);
//        findViewById(R.id.inboxIcon).setVisibility(View.GONE);
////        findViewById(R.id.cConnectIcon).setVisibility(View.GONE);
////        findViewById(R.id.teamIcon).setVisibility(View.GONE);
//        findViewById(R.id.settingsIcon).setVisibility(View.GONE);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return viewMVC.getFragmentFrame();
    }

    @Override
    public void onBackPressed() {
        boolean isBackPressConsumedByAnyListener = false;
        for (BackPressedListener listener : mBackPressedListeners) {
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true;
            }
        }
        if (isBackPressConsumedByAnyListener) {
            return;
        }
        super.onBackPressed();
    }
}
