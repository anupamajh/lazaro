package com.carmel.guestjini.Screens.Community.GroupList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GroupListViewMVCImpl
        extends BaseObservableViewMvc<GroupListViewMVC.Listener>
        implements GroupListViewMVC,
        GroupListRecycleAdapter.Listener {

    private final GroupListRecycleAdapter groupListRecycleAdapter;
    private final ProgressBar progressBar;
    private final FloatingActionButton btnNewGroup;

    public GroupListViewMVCImpl(LayoutInflater inflater,
                                @Nullable ViewGroup parent,
                                ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_group_list, parent, false));
        RecyclerView groupListRecyclerView = findViewById(R.id.lstGroups);
        ImageView btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progress);
        btnNewGroup = findViewById(R.id.btnNewGroup);
        groupListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        groupListRecycleAdapter = new GroupListRecycleAdapter(this, viewMVCFactory);
        groupListRecyclerView.setAdapter(groupListRecycleAdapter);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });
        btnNewGroup.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onNewGroupClicked();
            }
        });

    }

    @Override
    public void onGroupClicked(Group group) {
        for (Listener listener : getListeners()) {
            listener.onGroupClicked(group);
        }
    }

    @Override
    public void bindGroups(List<Group> groupList) {
        groupListRecycleAdapter.bindGroups(groupList);
    }

    @Override
    public void hideCreate() {
        btnNewGroup.setVisibility(View.GONE);
    }

    @Override
    public void showCreate() {
        btnNewGroup.setVisibility(View.VISIBLE);
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
