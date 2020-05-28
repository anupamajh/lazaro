package com.carmel.guestjini.Screens.Community.GroupHome;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.card.MaterialCardView;

public class GroupHomeViewMVCImpl extends BaseObservableViewMvc<GroupHomeViewMVC.Listener>
        implements GroupHomeViewMVC {

    public GroupHomeViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_group_home, parent, false));
        MaterialCardView cardInterestGroups = findViewById(R.id.cardInterestGroups);
        MaterialCardView cardCommunityGroups = findViewById(R.id.cardCommunityGroups);
        MaterialCardView cardMyGroups = findViewById(R.id.cardMyGroups);
        cardInterestGroups.setOnClickListener(v -> {
            for (Listener listener:getListeners()){
                listener.onInterestGroupClicked();
            }
        });
        cardCommunityGroups.setOnClickListener(v -> {
            for (Listener listener:getListeners()){
                listener.onCommunityGroupClicked();
            }
        });
        cardMyGroups.setOnClickListener(v -> {
            for (Listener listener:getListeners()){
                listener.onMyGroupClicked();
            }
        });

    }


}