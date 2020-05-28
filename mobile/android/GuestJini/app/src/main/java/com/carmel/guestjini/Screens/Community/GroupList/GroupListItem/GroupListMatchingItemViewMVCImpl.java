package com.carmel.guestjini.Screens.Community.GroupList.GroupListItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class GroupListMatchingItemViewMVCImpl
        extends BaseObservableViewMvc<GroupListItemViewMVC.Listener>
        implements GroupListItemViewMVC {

    private TextView txtInterestCategoryName;
    private TextView txtGroupName;
    private TextView txtDescription;

    private Group group;

    public GroupListMatchingItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_group_list_matching_interest_group_item, parent, false));
        txtInterestCategoryName = findViewById(R.id.txtInterestCategoryName);
        txtGroupName = findViewById(R.id.txtInterestName);
        txtDescription = findViewById(R.id.txtDescription);
        ImageView btnInformation = findViewById(R.id.btnInformation);
        getRootView().setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onGroupClicked(group);
            }
        });

        btnInformation.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onGroupClicked(group);
            }
        });
    }

    @Override
    public void bindGroup(Group group) {
        this.group = group;
        txtInterestCategoryName.setText(group.getInterestCategoryName());
        txtGroupName.setText(group.getName());
        txtDescription.setText(group.getDescription());

    }
}
