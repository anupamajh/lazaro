package com.carmel.guestjini.Screens.Common.BaseActivityView;

import android.widget.FrameLayout;

import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.Set;

public interface BaseActivityMVCView extends ObservableViewMvc<BaseActivityMVCView.Listener> {

    interface Listener {

        void onNavigationItemClicked();

        void onHomeClicked();

        void onSupportClicked();

        void onCommunityClicked();

        void onAccountsClicked();

        void onRewardsClicked();

        void onSettingsClicked();

        void onInboxClicked();
    }

    FrameLayout getFragmentFrame();

    void showBottomNavigationView();

    void setupNavigationGrants(Set<String> grants);

    void hideBottomNavigationView();

    void setSupportSelected();

    void setHomeSelected();


}
