package com.carmel.guestjini.Screens.Community.GroupConversation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Networking.Group.GroupConversation;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

public class GroupConversationViewMVCImpl
        extends BaseObservableViewMvc<GroupConversationViewMVC.Listener>
        implements GroupConversationViewMVC,
        GroupConversationRecycleAdapter.Listener {

    private final GroupConversationRecycleAdapter groupConversationRecycleAdapter;
    private final RecyclerView groupConversationListRecyclerView;
    private final ProgressBar progressBar;
    private final EditText txtMessage;
    private final TextView txtGroupName;
    private final TextView txtGroupDescription;
    private String groupId;
    private int groupType;

    public GroupConversationViewMVCImpl(LayoutInflater inflater,
                                        @Nullable ViewGroup parent,
                                        ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_group_conversation, parent, false));
        groupConversationListRecyclerView = findViewById(R.id.lstGroupConversation);
        ImageView btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progress);
        txtMessage = findViewById(R.id.txtMessage);
        txtGroupName = findViewById(R.id.txtGroupName);
        txtGroupDescription = findViewById(R.id.txtGroupDescription);
        Button btnSend = findViewById(R.id.btnSend);
        Button btnGroupInformation = findViewById(R.id.btnGroupInformation);
        groupConversationListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        groupConversationRecycleAdapter = new GroupConversationRecycleAdapter(this, viewMVCFactory);
        groupConversationListRecyclerView.setAdapter(groupConversationRecycleAdapter);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });
        btnGroupInformation.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onInfoClicked(groupId, groupType);
            }
        });
        btnSend.setOnClickListener(view -> {
            String message = txtMessage.getText().toString().trim();
            if (!message.equals("")) {
                for (Listener listener : getListeners()) {
                    listener.onSendMessageClicked(groupId, message);
                }
            }
        });

    }

    @Override
    public void clearMessage() {
        txtMessage.setText("");
    }

    @Override
    public void bindGroupConversations(List<GroupConversation> groupConversations) {
        groupConversationRecycleAdapter.bindGroupConversations(groupConversations);
        groupConversationListRecyclerView.scrollToPosition(groupConversationListRecyclerView.getAdapter().getItemCount() - 1);
    }

    @Override
    public void bindGroup(Group group, int groupType) {
        this.groupId = group.getId();
        this.groupType = groupType;
        txtGroupName.setText(group.getName());
        txtGroupDescription.setText(group.getDescription());
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