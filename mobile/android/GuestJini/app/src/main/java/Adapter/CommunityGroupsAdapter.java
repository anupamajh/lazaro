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

import static Model.CommunityGroupsModel.InvitedReadCell;
import static Model.CommunityGroupsModel.InvitedUnreadUserCell;
import static Model.CommunityGroupsModel.JoinedGroupsCell;
import static Model.CommunityGroupsModel.RemovedCell;
import static Model.CommunityGroupsModel.RequestAcceptedCell;
import static Model.CommunityGroupsModel.RequestedCell;
import static Model.CommunityGroupsModel.UnsubscribedCell;


public class CommunityGroupsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Group> groupArrayList;
    private OnItemClickListener onItemClickListener;

    public CommunityGroupsAdapter(Context context, ArrayList<Group> groupArrayList, OnItemClickListener onItemClickListener) {
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
        int viewType = InvitedUnreadUserCell;
        if (group.getIsSubscribed() == 1) {
            viewType = RequestAcceptedCell;
        } else {
            if (group.getIsInvited() == 0) {
                viewType = UnsubscribedCell;
            } else {
                viewType = InvitedUnreadUserCell;
            }
        }
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case InvitedUnreadUserCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_groups_container, parent, false);
                return new CommunityGroupsAdapter.InvitedUnreadUserHolder(view);

            case RequestAcceptedCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_request_accepted_cell, parent, false);
                return new CommunityGroupsAdapter.RequestAcceptedHolder(view);

            case JoinedGroupsCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_joined_cell, parent, false);
                return new JoinedGroupsHolder(view);

            case RemovedCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_removed_cell, parent, false);
                return new RemovedViewHoler(view);

            case RequestedCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_requested_cell, parent, false);
                return new RequestedViewHolder(view);

            case UnsubscribedCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_unsubscribed_cell, parent, false);
                return new UnsubscribedViewHolder(view);

            case InvitedReadCell:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_invited_cell, parent, false);
                return new InvitedReadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Group group = groupArrayList.get(position);
        int viewType = getViewType(group);
        switch (viewType) {
            case InvitedUnreadUserCell:
                ((InvitedUnreadUserHolder) holder).communityGroupTitle.setText(group.getName());
                ((InvitedUnreadUserHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((InvitedUnreadUserHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((InvitedUnreadUserHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((InvitedUnreadUserHolder) holder).communityGroupInformationMessage.setVisibility(View.GONE);
                ((InvitedUnreadUserHolder) holder).informationIcon.setImageResource(R.drawable.information_icon);
                ((InvitedUnreadUserHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;
            case RequestAcceptedCell:
                ((RequestAcceptedHolder) holder).communityGroupTitle.setText(group.getName());
                ((RequestAcceptedHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((RequestAcceptedHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((RequestAcceptedHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((RequestAcceptedHolder) holder).communityGroupInformationMessage.setVisibility(View.GONE);
                ((RequestAcceptedHolder) holder).informationIcon.setImageResource(R.drawable.information_icon);
                ((RequestAcceptedHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;
            case JoinedGroupsCell:
                ((JoinedGroupsHolder) holder).communityGroupTitle.setText(group.getName());
                ((JoinedGroupsHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((JoinedGroupsHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((JoinedGroupsHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((JoinedGroupsHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;
            case RemovedCell:
                ((RemovedViewHoler) holder).communityGroupTitle.setText(group.getName());
                ((RemovedViewHoler) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((RemovedViewHoler) holder).communityGroupDescription.setText(group.getDescription());
                ((RemovedViewHoler) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((RemovedViewHoler) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;

            case RequestedCell:
                ((RequestedViewHolder) holder).communityGroupTitle.setText(group.getName());
                ((RequestedViewHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((RequestedViewHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((RequestedViewHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((RequestedViewHolder) holder).communityGroupInformationMessage.setVisibility(View.GONE);
                ((RequestedViewHolder) holder).informationIcon.setImageResource(R.drawable.information_icon);
                ((RequestedViewHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;
            case UnsubscribedCell:
                ((UnsubscribedViewHolder) holder).communityGroupTitle.setText(group.getName());
                ((UnsubscribedViewHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((UnsubscribedViewHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((UnsubscribedViewHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((UnsubscribedViewHolder) holder).communityGroupInformationMessage.setVisibility(View.GONE);
                ((UnsubscribedViewHolder) holder).informationIcon.setImageResource(R.drawable.information_icon);
                ((UnsubscribedViewHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
                break;
            case InvitedReadCell:
                ((InvitedReadViewHolder) holder).communityGroupTitle.setText(group.getName());
                ((InvitedReadViewHolder) holder).communityGroupAdmin.setVisibility(View.GONE);
                ((InvitedReadViewHolder) holder).communityGroupDescription.setText(group.getDescription());
                ((InvitedReadViewHolder) holder).communityGroupCreationDateAndTime.setText(group.getCreationTime());
                ((InvitedReadViewHolder) holder).communityGroupInformationMessage.setVisibility(View.GONE);
                ((InvitedReadViewHolder) holder).informationIcon.setImageResource(R.drawable.information_icon);
                ((InvitedReadViewHolder) holder).adminProfileIcon.setImageResource(R.drawable.profile_image);
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

    class InvitedUnreadUserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime, communityGroupInformationMessage;
        ImageView informationIcon, adminProfileIcon;

        public InvitedUnreadUserHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage = itemView.findViewById(R.id.informationMessage);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon = itemView.findViewById(R.id.informationIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            informationIcon.setImageResource(R.drawable.information_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClikInvitedUnreadGroup(getAdapterPosition());
        }
    }

    class RequestAcceptedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime, communityGroupInformationMessage;
        ImageView informationIcon, adminProfileIcon;

        public RequestAcceptedHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage = itemView.findViewById(R.id.invitedDescription);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon = itemView.findViewById(R.id.invitedInformationIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            informationIcon.setImageResource(R.drawable.information_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClikRequestAcceptedGroup(getAdapterPosition());
        }
    }

    class JoinedGroupsHolder extends RecyclerView.ViewHolder {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime, communityGroupInformationMessage;
        ImageView informationIcon, adminProfileIcon;

        public JoinedGroupsHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);


        }
    }

    class RemovedViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime, communityGroupInformationMessage;
        ImageView informationIcon, adminProfileIcon;

        public RemovedViewHoler(@NonNull View itemView) {
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
            onItemClickListener.onClikRemovedGroup(getAdapterPosition());

        }
    }

    class RequestedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime, communityGroupInformationMessage;
        ImageView informationIcon, adminProfileIcon;

        public RequestedViewHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage = itemView.findViewById(R.id.invitedDescription);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon = itemView.findViewById(R.id.invitedInformationIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            informationIcon.setImageResource(R.drawable.information_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClikRequestedGroup(getAdapterPosition());
        }
    }

    class UnsubscribedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime, communityGroupInformationMessage;
        ImageView informationIcon, adminProfileIcon;

        public UnsubscribedViewHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage = itemView.findViewById(R.id.invitedDescription);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon = itemView.findViewById(R.id.invitedInformationIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            informationIcon.setImageResource(R.drawable.information_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClikUnsubscribedGroup(getAdapterPosition());
        }
    }

    class InvitedReadViewHolder extends RecyclerView.ViewHolder {
        TextView communityGroupTitle, communityGroupAdmin, communityGroupDescription;
        TextView communityGroupCreationDateAndTime, communityGroupInformationMessage;
        ImageView informationIcon, adminProfileIcon;

        public InvitedReadViewHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle = itemView.findViewById(R.id.groupsName);
            communityGroupAdmin = itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime = itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription = itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage = itemView.findViewById(R.id.informationMessage);
            adminProfileIcon = itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon = itemView.findViewById(R.id.informationIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            informationIcon.setImageResource(R.drawable.information_icon);
        }
    }

    public interface OnItemClickListener {
        void onClikInvitedUnreadGroup(int position);

        void onClikRequestAcceptedGroup(int position);

        void onClikRemovedGroup(int position);

        void onClikRequestedGroup(int position);

        void onClikUnsubscribedGroup(int position);

    }

}