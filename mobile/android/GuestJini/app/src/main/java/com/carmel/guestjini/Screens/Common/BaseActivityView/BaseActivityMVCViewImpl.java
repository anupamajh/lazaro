package com.carmel.guestjini.Screens.Common.BaseActivityView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivityMVCViewImpl extends BaseObservableViewMvc<BaseActivityMVCView.Listener>
implements BaseActivityMVCView
{
    private final FrameLayout baseLayout;
    private final FrameLayout frameLayout;
    private final BottomNavigationView bottomNavigationView;

    public BaseActivityMVCViewImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_base_base_activity, parent, false));
        baseLayout = findViewById(R.layout.layout_base_base_activity);
        frameLayout = findViewById(R.id.frame_content);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        /* REM: Can go with de-coupled interface based approach but looks like it will be unnecessary overhead */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                    case R.id.suppotIcon:
                    {
                        for(Listener listener:getListeners()){
                            listener.onSupportClicked();
                        }
                    }
                    break;
                    case R.id.communityIcon:
                    {
                        for(Listener listener:getListeners()){
                            listener.onCommunityClicked();
                        }
                    }
                    break;
                    case R.id.accountsIcon:
                    {
                        for(Listener listener:getListeners()){
                            listener.onAccountsClicked();
                        }
                    }
                    break;
                    case R.id.rewardsIcon:
                    {
                        for(Listener listener:getListeners()){
                            listener.onRewardsClicked();
                        }
                    }
                    break;
                    case R.id.settingsIcon:
                    {
                        for(Listener listener:getListeners()){
                            listener.onSettingsClicked();
                        }
                    }
                    break;
                }

                return true;
            }
        });
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return frameLayout;
    }

    @Override
    public void showBottomNavigationView() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBottomNavigationView() {
        bottomNavigationView.setVisibility(View.GONE);
    }
}
