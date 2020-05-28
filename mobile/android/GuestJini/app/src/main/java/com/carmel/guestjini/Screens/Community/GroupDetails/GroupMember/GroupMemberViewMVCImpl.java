package com.carmel.guestjini.Screens.Community.GroupDetails.GroupMember;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Common.StringUtils;
import com.carmel.guestjini.Networking.Users.AddressBook;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupMemberViewMVCImpl
        extends BaseObservableViewMvc<GroupMemberViewMVC.Listener>
        implements GroupMemberViewMVC {

    CircleImageView imgGroupMemberProfile;
    TextView txtFullName;
    TextView txtGroupJoinDate;
    Button btnInvite;
    TextView txtInvitationStatus;

    private AddressBook addressBook;
    private int isSubscribed;
    private int isInvited;
    private String groupId;

    public GroupMemberViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_group_details_member_item, parent, false));
        imgGroupMemberProfile = findViewById(R.id.imgGroupMemberProfile);
        txtFullName = findViewById(R.id.txtFullName);
        txtGroupJoinDate = findViewById(R.id.txtGroupJoinDate);
        btnInvite = findViewById(R.id.btnInvite);
        txtInvitationStatus = findViewById(R.id.txtInvitationStatus);
        btnInvite.setOnClickListener(v -> {
            for(Listener listener:getListeners()){
                listener.onInviteClicked(addressBook.getUserId(), groupId);
            }
        });
    }

    @Override
    public void bingGroupMember(
            AddressBook addressBook,
            int isSubscribed,
            int isInvited,
            String groupId,
            int groupType
    ) {
        this.addressBook = addressBook;
        this.isSubscribed = isSubscribed;
        this.groupId = groupId;
        this.isInvited = isInvited;
        Date creationDate = DateUtil.convertToDate(addressBook.getCreationTime());
        txtFullName.setText(addressBook.getDisplayName());
        txtFullName.setText(DateUtil.getFormattedDate(creationDate));
        if(groupType != 2) {
            btnInvite.setVisibility(View.GONE);
            txtInvitationStatus.setVisibility(View.GONE);
        }else {
            if (isInvited == 1) {
                btnInvite.setVisibility(View.GONE);
                if (isSubscribed == 0) {
                    txtInvitationStatus.setText("Invitation Sent");
                } else {
                    txtInvitationStatus.setText("Invitation Accepted");
                }
            } else {
                btnInvite.setVisibility(View.VISIBLE);
            }
        }

        //TODO: find a nice way to bind image
    }
}
