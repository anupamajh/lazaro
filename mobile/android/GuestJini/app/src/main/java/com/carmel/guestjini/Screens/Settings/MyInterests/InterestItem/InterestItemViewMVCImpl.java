package com.carmel.guestjini.Screens.Settings.MyInterests.InterestItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class InterestItemViewMVCImpl
        extends BaseObservableViewMvc<InterestItemViewMVC.Listener>
        implements InterestItemViewMVC {

    private final TextView txtInterestTitle;
    private final ImageView btnInterested;

    private Interest interest;

    public InterestItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_my_interest_item, parent, false));
        txtInterestTitle = findViewById(R.id.txtInterestTitle);
        btnInterested = findViewById(R.id.btnInterested);
        btnInterested.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            private boolean isInitiating = true;
            private int isInterested = 0;

            @Override
            public void onClick(View view) {
                if (flag) {
                    flag = false;
                    btnInterested.setImageResource(R.drawable.checked_icon_xhdpi);
                    isInterested = 1;
                } else {
                    flag = true;
                    btnInterested.setImageResource(R.drawable.unchecked_icon_xxhdpi);
                    isInterested = 0;
                }
                if (!isInitiating) {
                    for (Listener listener : getListeners()) {
                        listener.onInterestClicked(interest, isInterested);
                    }
                }
                isInitiating = false;
            }
        });
    }

    @Override
    public void bindInterest(Interest interest) {
        this.interest = interest;
        txtInterestTitle.setText(interest.getName());
    }

    @Override
    public void setInterest(int isInterested) {
        btnInterested.callOnClick();
    }
}
