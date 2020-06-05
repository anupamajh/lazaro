package com.carmel.guestjini.Screens.Community.GroupDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class GroupDetailsViewMVCImpl
        extends BaseObservableViewMvc<GroupDetailsViewMVC.Listener>
        implements GroupDetailsViewMVC,
        GroupMemberRecycleAdapter.Listener {

    private final GroupMemberRecycleAdapter groupMemberRecycleAdapter;
    private final ProgressBar progressBar;
    private final TextView txtGroupCategoryName;
    private final TextView txtGroupName;
    private final TextView txtGroupDescription;
    private final TextView txtCreatedBy;
    private final TextView txtCreationTime;
    private final TextView memberText;
    private final Button btnExit;
    private final Button btnMessage;
    private final Button btnSubscribe;

    private GroupResponse groupResponse;
    private int groupType;

    public GroupDetailsViewMVCImpl(LayoutInflater inflater,
                                   @Nullable ViewGroup parent,
                                   ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_group_details, parent, false));
        RecyclerView groupMemberRecyclerView = findViewById(R.id.lstGroupMembers);
        txtGroupCategoryName = findViewById(R.id.txtGroupCategoryName);
        txtGroupName = findViewById(R.id.txtGroupName);
        txtGroupDescription = findViewById(R.id.txtGroupDescription);
        txtCreatedBy = findViewById(R.id.txtCreatedBy);
        txtCreationTime = findViewById(R.id.txtCreationTime);
        memberText = findViewById(R.id.memberText);
        btnExit = findViewById(R.id.btnExit);
        btnMessage = findViewById(R.id.btnMessage);
        btnSubscribe = findViewById(R.id.btnSubscribe);
        ImageView btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progress);
        groupMemberRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        groupMemberRecycleAdapter = new GroupMemberRecycleAdapter(this, viewMVCFactory);
        groupMemberRecyclerView.setAdapter(groupMemberRecycleAdapter);
        btnMessage.setVisibility(View.GONE);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnMessage.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onMessageClicked(groupResponse.getGroup().getId());
            }
        });

    }

    @Override
    public void bindGroupResponse(GroupResponse groupResponse, int groupType) {
        this.groupResponse = groupResponse;
        this.groupType = groupType;
        memberText.setText(this.groupResponse.getGroupPeople().size() + " Members");
        groupMemberRecycleAdapter.bindGroupResponse(groupResponse, groupType);
        txtGroupCategoryName.setText(groupResponse.getGroup().getInterestCategoryName());
        txtGroupName.setText(groupResponse.getGroup().getName());
        txtGroupDescription.setText(groupResponse.getGroup().getDescription());
        if (groupResponse.getGroup().getIsSubscribed() == 1) {
            btnExit.setVisibility(View.VISIBLE);
            btnSubscribe.setVisibility(View.GONE);
        } else {
            btnExit.setVisibility(View.GONE);
            btnSubscribe.setVisibility(View.VISIBLE);
        }
        if (groupResponse.getGroup().getGroupType() == 2) {
            btnExit.setVisibility(View.GONE);
            btnMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onInviteClicked(String userId, String groupId) {
        for (Listener listener : getListeners()) {
            listener.onInviteClicked(userId, groupId);
        }
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }
}
