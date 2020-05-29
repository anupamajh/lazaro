package com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Group.GroupConversation;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class GroupConversationItemOtherViewMVC
        extends BaseObservableViewMvc<GroupConversationItemViewMVC.Listener>
        implements GroupConversationItemViewMVC {

    private final TextView txtDateAndTime;
    private final TextView txtMemberName;
    private final TextView txtMessage;

    private GroupConversation groupConversation;

    public GroupConversationItemOtherViewMVC(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_group_conversation_item_others, parent, false));
        txtDateAndTime = findViewById(R.id.txtDateAndTime);
        txtMemberName = findViewById(R.id.txtMemberName);
        txtMessage = findViewById(R.id.txtMessage);
    }

    @Override
    public void bindGroupConversation(GroupConversation groupConversation) {
        this.groupConversation = groupConversation;
        if (groupConversation.getCreationTime() != null)
            txtDateAndTime.setText(groupConversation.getCreationTime());
        txtMemberName.setText(groupConversation.getDisplayName());
        txtMessage.setText(groupConversation.getMessage());

    }
}