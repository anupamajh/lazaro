package com.carmel.guestjini.Screens.Community.GroupConversation;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Group.GroupConversation;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationItem.GroupConversationItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class GroupConversationRecycleAdapter
        extends RecyclerView.Adapter<GroupConversationRecycleAdapter.MessageHolder>
        implements GroupConversationItemViewMVC.Listener {
    public interface Listener {
    }

    static class MessageHolder extends RecyclerView.ViewHolder {
        private final GroupConversationItemViewMVC viewMVC;

        public MessageHolder(@NonNull GroupConversationItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<GroupConversation> groupConversations = new ArrayList<>();

    public GroupConversationRecycleAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindGroupConversations(List<GroupConversation> groupConversations) {
        this.groupConversations = new ArrayList<>(groupConversations);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupConversationItemViewMVC viewMvc;
        if (viewType == 1) {
            viewMvc = viewMVCFactory.getGroupConversationItemMineViewMVC(parent);
        } else {
            viewMvc = viewMVCFactory.getGroupConversationItemOtherViewMVC(parent);
        }
        viewMvc.registerListener(this);
        return new MessageHolder(viewMvc);
    }

    @Override
    public int getItemViewType(int position) {
        GroupConversation groupConversation = this.groupConversations.get(position);
        if (groupConversation != null) {
            return groupConversation.getIsItMe() == 1 ? 1 : 2;
        }
        return 1;
    }


    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.viewMVC.bindGroupConversation(this.groupConversations.get(position));
    }

    @Override
    public int getItemCount() {
        return this.groupConversations.size();
    }


}
