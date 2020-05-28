package com.carmel.guestjini.Screens.Community.GroupDetails;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Networking.Users.AddressBook;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Community.GroupDetails.GroupMember.GroupMemberViewMVC;

import java.util.ArrayList;
import java.util.List;

public class GroupMemberRecycleAdapter
        extends RecyclerView.Adapter<GroupMemberRecycleAdapter.MemberHolder>
        implements GroupMemberViewMVC.Listener {


    public interface Listener {
        void onInviteClicked(String userId, String groupId);
    }

    static class MemberHolder extends RecyclerView.ViewHolder {
        private final GroupMemberViewMVC viewMVC;

        public MemberHolder(@NonNull GroupMemberViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<AddressBook> addressBookArrayList = new ArrayList<>();
    private GroupResponse groupResponse;

    public GroupMemberRecycleAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindGroupResponse(GroupResponse groupResponse) {
        this.groupResponse = groupResponse;
        this.addressBookArrayList = new ArrayList<>(groupResponse.getGroupPeople());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupMemberViewMVC viewMvc = viewMVCFactory.getGroupMemberViewMVC(parent);
        viewMvc.registerListener(this);
        return new MemberHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        holder.viewMVC.bingGroupMember(
                this.addressBookArrayList.get(position),
                groupResponse.getGroup().getIsSubscribed(),
                groupResponse.getGroup().getIsInvited(),
                groupResponse.getGroup().getId(),
                groupResponse.getGroup().getGroupType()

        );
    }

    @Override
    public int getItemCount() {
        return this.addressBookArrayList.size();
    }

    @Override
    public void onInviteClicked(String userId, String groupId) {
        listener.onInviteClicked(userId, groupId);
    }
}