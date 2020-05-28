package com.carmel.guestjini.Screens.Community.CommunityHome;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.card.MaterialCardView;

public class CommunityHomeViewMVCImpl
        extends BaseObservableViewMvc<CommunityHomeViewMVC.Listener>
        implements CommunityHomeViewMVC {

    public CommunityHomeViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_home, parent, false));
        MaterialCardView cardMyProfile = findViewById(R.id.cardMyProfile);
        MaterialCardView cardPeople = findViewById(R.id.cardPeople);
        MaterialCardView cardGroups = findViewById(R.id.cardGroups);
        cardMyProfile.setOnClickListener(v -> {
            for (Listener listener:getListeners()){
                listener.onMyProfileClicked();
            }
        });
        cardPeople.setOnClickListener(v -> {
            for (Listener listener:getListeners()){
                listener.onPeopleClicked();
            }
        });
        cardGroups.setOnClickListener(v -> {
            for (Listener listener:getListeners()){
                listener.onGroupClicked();
            }
        });

    }


}
