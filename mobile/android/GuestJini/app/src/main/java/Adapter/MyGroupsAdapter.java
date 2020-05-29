package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Models.Group.Group;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import static Model.CommunityGroupsModel.JoinedGroupsCell;
import static Model.CommunityGroupsModel.RemovedCell;


public class MyGroupsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Group> groupArrayList;
    private OnItemClickListener onItemClickListener;

    public MyGroupsAdapter(Context context, ArrayList<Group> groupArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.groupArrayList = groupArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        Group group = groupArrayList.get(position);
        return getViewType(group);
    }


    private int getViewType(Group group) {
        int viewType = JoinedGroupsCell;
        if (group.getIsSubscribed() == 1) {
            viewType = JoinedGroupsCell;
        }
        return viewType;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case JoinedGroupsCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_joined_cell, parent, false);
                return new MyGroupsAdapter.JoinedViewHolder(view);
            case RemovedCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_removed_cell, parent, false);
                return new MyGroupsAdapter.GroupContainerHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Group group = groupArrayList.get(position);
        int viewType = getViewType(group);
        switch (viewType) {
            case JoinedGroupsCell:
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupTitle.setText(group.getName());
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((MyGroupsAdapter.JoinedViewHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;
            case RemovedCell:
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupTitle.setText(group.getName());
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((MyGroupsAdapter.GroupContainerHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }

    public void update(ArrayList<Group> groupArrayList) {
        this.groupArrayList = groupArrayList;
        notifyDataSetChanged();
    }

    class JoinedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime;
        ImageView adminProfileIcon;

        public JoinedViewHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClikJoinedGroup(getAdapterPosition());
        }
    }

    class GroupContainerHolder extends RecyclerView.ViewHolder {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime;
        ImageView adminProfileIcon;

        public GroupContainerHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);


        }
    }

    public interface OnItemClickListener {
        void onClikJoinedGroup(int position);

        void onClikRemovedGroup(int position);
    }
}
