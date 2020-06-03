package com.carmel.guestjini.Screens.Community.GroupList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListItem.GroupListItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class GroupListRecycleAdapter
        extends RecyclerView.Adapter<GroupListRecycleAdapter.GroupViewHolder>
        implements GroupListItemViewMVC.Listener {
    public interface Listener {
        void onGroupClicked(Group group);
        void onGroupDetailClicked(Group group);
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        private final GroupListItemViewMVC viewMVC;

        public GroupViewHolder(@NonNull GroupListItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<Group> groupArrayList = new ArrayList<>();

    public GroupListRecycleAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindGroups(List<Group> groupList) {
        this.groupArrayList = new ArrayList<>(groupList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupListItemViewMVC viewMvc;
        if (viewType == 2) {
            viewMvc = viewMVCFactory.getGroupListMatchingItemViewMVC(parent);
        } else if (viewType == 3) {
            viewMvc = viewMVCFactory.getGroupListUnSubScribedItemViewMVCImpl(parent);
        } else {
            viewMvc = viewMVCFactory.getGroupListItemViewMVC(parent);
        }
        viewMvc.registerListener(this);
        return new GroupViewHolder(viewMvc);
    }

    @Override
    public int getItemViewType(int position) {
        Group group = this.groupArrayList.get(position);
        if (group != null) {
            return getViewType(group);
        }
        return 1;
    }

    private int getViewType(Group group) {
        int viewType = 0;
        if (group.getIsSubscribed() == 1) {
            viewType = 1;
        } else {
            if (group.getIsMatchingInterest() == 0) {
                viewType = 2;
            } else {
                viewType = 3;
            }
        }
        return viewType;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.viewMVC.bindGroup(this.groupArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.groupArrayList.size();
    }

    @Override
    public void onGroupClicked(Group group) {
        listener.onGroupClicked(group);
    }

    @Override
    public void onGroupDetailClicked(Group group) {
        listener.onGroupDetailClicked(group);
    }
}
