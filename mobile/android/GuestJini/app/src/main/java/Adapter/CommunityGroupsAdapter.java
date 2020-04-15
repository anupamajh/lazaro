package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.CommunityGroupsModel;


import static Model.CommunityGroupsModel.InvitedReadCell;
import static Model.CommunityGroupsModel.InvitedUnreadUserCell;
import static Model.CommunityGroupsModel.JoinedGroupsCell;
import static Model.CommunityGroupsModel.RemovedCell;
import static Model.CommunityGroupsModel.RequestAcceptedCell;
import static Model.CommunityGroupsModel.RequestedCell;
import static Model.CommunityGroupsModel.UnsubscribedCell;


public class CommunityGroupsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<CommunityGroupsModel> communityGroupsArrayList;
    private OnItemClickListener onItemClickListener;
    public CommunityGroupsAdapter(Context context, ArrayList<CommunityGroupsModel> communityGroupsList,OnItemClickListener onItemClickListener) {
        this.context=context;
        this.communityGroupsArrayList=communityGroupsList;
        this.onItemClickListener=onItemClickListener;
    }
        @Override
    public int getItemViewType(int position){
        CommunityGroupsModel communityGroupsModel=communityGroupsArrayList.get(position);
        if(communityGroupsModel!=null){
        return communityGroupsModel.getViewType();
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case InvitedUnreadUserCell:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_groups_container,parent,false);
                return new CommunityGroupsAdapter.InvitedUnreadUserHolder(view);

            case RequestAcceptedCell:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_request_accepted_cell,parent,false);
                return new CommunityGroupsAdapter.RequestAcceptedHolder(view);

            case JoinedGroupsCell:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_joined_cell,parent,false);
                return new JoinedGroupsHolder(view);

            case RemovedCell:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_removed_cell,parent,false);
                return new RemovedViewHoler(view);

            case RequestedCell:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_requested_cell,parent,false);
                return new RequestedViewHolder(view);

            case UnsubscribedCell:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_unsubscribed_cell,parent,false);
                return new UnsubscribedViewHolder(view);

            case InvitedReadCell:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_invited_cell,parent,false);
                return new InvitedReadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        CommunityGroupsModel communityGroupsModel=communityGroupsArrayList.get(position);
        switch (communityGroupsModel.getViewType()){
            case InvitedUnreadUserCell:
                ((InvitedUnreadUserHolder)holder).communityGroupTitle.setText(communityGroupsModel.getCommunityGroupTitle());
                ((InvitedUnreadUserHolder)holder).communityGroupAdmin.setText(communityGroupsModel.getCommunityGroupAdmin());
                ((InvitedUnreadUserHolder)holder).communityGroupDescription.setText(communityGroupsModel.getCommunityGroupDescription());
                ((InvitedUnreadUserHolder)holder).communityGroupCreationDateAndTime.setText(communityGroupsModel.getCommunityGroupCreationDateAndTime());
                ((InvitedUnreadUserHolder)holder).communityGroupInformationMessage.setText(communityGroupsModel.getCommunityGroupInformationMessage());
                ((InvitedUnreadUserHolder)holder).informationIcon.setImageResource(communityGroupsModel.getInformationIcon());
                ((InvitedUnreadUserHolder)holder).adminProfileIcon.setImageResource(communityGroupsModel.getAdminProfileIcon());
                break;
            case RequestAcceptedCell:
                ((RequestAcceptedHolder)holder).communityGroupTitle.setText(communityGroupsModel.getCommunityGroupTitle());
                ((RequestAcceptedHolder)holder).communityGroupAdmin.setText(communityGroupsModel.getCommunityGroupAdmin());
                ((RequestAcceptedHolder)holder).communityGroupDescription.setText(communityGroupsModel.getCommunityGroupDescription());
                ((RequestAcceptedHolder)holder).communityGroupCreationDateAndTime.setText(communityGroupsModel.getCommunityGroupCreationDateAndTime());
                ((RequestAcceptedHolder)holder).communityGroupInformationMessage.setText(communityGroupsModel.getCommunityGroupInformationMessage());
                ((RequestAcceptedHolder)holder).informationIcon.setImageResource(communityGroupsModel.getInformationIcon());
                ((RequestAcceptedHolder)holder).adminProfileIcon.setImageResource(communityGroupsModel.getAdminProfileIcon());
                break;
            case JoinedGroupsCell:
                ((JoinedGroupsHolder)holder).communityGroupTitle.setText(communityGroupsModel.getCommunityGroupTitle());
                ((JoinedGroupsHolder)holder).communityGroupAdmin.setText(communityGroupsModel.getCommunityGroupAdmin());
                ((JoinedGroupsHolder)holder).communityGroupDescription.setText(communityGroupsModel.getCommunityGroupDescription());
                ((JoinedGroupsHolder)holder).communityGroupCreationDateAndTime.setText(communityGroupsModel.getCommunityGroupCreationDateAndTime());
                ((JoinedGroupsHolder)holder).adminProfileIcon.setImageResource(communityGroupsModel.getAdminProfileIcon());
                break;
            case RemovedCell:
                ((RemovedViewHoler)holder).communityGroupTitle.setText(communityGroupsModel.getCommunityGroupTitle());
                ((RemovedViewHoler)holder).communityGroupAdmin.setText(communityGroupsModel.getCommunityGroupAdmin());
                ((RemovedViewHoler)holder).communityGroupDescription.setText(communityGroupsModel.getCommunityGroupDescription());
                ((RemovedViewHoler)holder).communityGroupCreationDateAndTime.setText(communityGroupsModel.getCommunityGroupCreationDateAndTime());
                ((RemovedViewHoler)holder).adminProfileIcon.setImageResource(communityGroupsModel.getAdminProfileIcon());
                break;

            case RequestedCell:
                ((RequestedViewHolder)holder).communityGroupTitle.setText(communityGroupsModel.getCommunityGroupTitle());
                ((RequestedViewHolder)holder).communityGroupAdmin.setText(communityGroupsModel.getCommunityGroupAdmin());
                ((RequestedViewHolder)holder).communityGroupDescription.setText(communityGroupsModel.getCommunityGroupDescription());
                ((RequestedViewHolder)holder).communityGroupCreationDateAndTime.setText(communityGroupsModel.getCommunityGroupCreationDateAndTime());
                ((RequestedViewHolder)holder).communityGroupInformationMessage.setText(communityGroupsModel.getCommunityGroupInformationMessage());
                ((RequestedViewHolder)holder).informationIcon.setImageResource(communityGroupsModel.getInformationIcon());
                ((RequestedViewHolder)holder).adminProfileIcon.setImageResource(communityGroupsModel.getAdminProfileIcon());
                break;
            case UnsubscribedCell:
                ((UnsubscribedViewHolder)holder).communityGroupTitle.setText(communityGroupsModel.getCommunityGroupTitle());
                ((UnsubscribedViewHolder)holder).communityGroupAdmin.setText(communityGroupsModel.getCommunityGroupAdmin());
                ((UnsubscribedViewHolder)holder).communityGroupDescription.setText(communityGroupsModel.getCommunityGroupDescription());
                ((UnsubscribedViewHolder)holder).communityGroupCreationDateAndTime.setText(communityGroupsModel.getCommunityGroupCreationDateAndTime());
                ((UnsubscribedViewHolder)holder).communityGroupInformationMessage.setText(communityGroupsModel.getCommunityGroupInformationMessage());
                ((UnsubscribedViewHolder)holder).informationIcon.setImageResource(communityGroupsModel.getInformationIcon());
                ((UnsubscribedViewHolder)holder).adminProfileIcon.setImageResource(communityGroupsModel.getAdminProfileIcon());
                break;
            case InvitedReadCell:
                ((InvitedReadViewHolder)holder).communityGroupTitle.setText(communityGroupsModel.getCommunityGroupTitle());
                ((InvitedReadViewHolder)holder).communityGroupAdmin.setText(communityGroupsModel.getCommunityGroupAdmin());
                ((InvitedReadViewHolder)holder).communityGroupDescription.setText(communityGroupsModel.getCommunityGroupDescription());
                ((InvitedReadViewHolder)holder).communityGroupCreationDateAndTime.setText(communityGroupsModel.getCommunityGroupCreationDateAndTime());
                ((InvitedReadViewHolder)holder).communityGroupInformationMessage.setText(communityGroupsModel.getCommunityGroupInformationMessage());
                ((InvitedReadViewHolder)holder).informationIcon.setImageResource(communityGroupsModel.getInformationIcon());
                ((InvitedReadViewHolder)holder).adminProfileIcon.setImageResource(communityGroupsModel.getAdminProfileIcon());
                break;

        }

    }

    @Override
    public int getItemCount() {
        return communityGroupsArrayList.size();
    }

    class InvitedUnreadUserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime,communityGroupInformationMessage;
        ImageView informationIcon,adminProfileIcon;
        public InvitedUnreadUserHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage=itemView.findViewById(R.id.informationMessage);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon=itemView.findViewById(R.id.informationIcon);
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
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime,communityGroupInformationMessage;
        ImageView informationIcon,adminProfileIcon;
        public RequestAcceptedHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage=itemView.findViewById(R.id.invitedDescription);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon=itemView.findViewById(R.id.invitedInformationIcon);
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
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime,communityGroupInformationMessage;
        ImageView informationIcon,adminProfileIcon;
        public JoinedGroupsHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);


        }
    }

    class RemovedViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime,communityGroupInformationMessage;
        ImageView informationIcon,adminProfileIcon;
        public RemovedViewHoler(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClikRemovedGroup(getAdapterPosition());

        }
    }

    class RequestedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime,communityGroupInformationMessage;
        ImageView informationIcon,adminProfileIcon;
        public RequestedViewHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage=itemView.findViewById(R.id.invitedDescription);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon=itemView.findViewById(R.id.invitedInformationIcon);
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
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime,communityGroupInformationMessage;
        ImageView informationIcon,adminProfileIcon;
        public UnsubscribedViewHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage=itemView.findViewById(R.id.invitedDescription);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon=itemView.findViewById(R.id.invitedInformationIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            informationIcon.setImageResource(R.drawable.information_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClikUnsubscribedGroup(getAdapterPosition());
        }
    }

    class InvitedReadViewHolder extends RecyclerView.ViewHolder  {
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime,communityGroupInformationMessage;
        ImageView informationIcon,adminProfileIcon;
        public InvitedReadViewHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            communityGroupInformationMessage=itemView.findViewById(R.id.informationMessage);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            informationIcon=itemView.findViewById(R.id.informationIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);
            informationIcon.setImageResource(R.drawable.information_icon);
        }
    }

    public interface OnItemClickListener{
        void onClikInvitedUnreadGroup(int position);
        void onClikRequestAcceptedGroup(int position);
        void onClikRemovedGroup(int position);
        void onClikRequestedGroup(int position);
        void onClikUnsubscribedGroup(int position);

    }

}





















//public class CommunityGroupsAdapter     extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    ArrayList<CommunityGroupsModel> communityGroupsList;
//    Context context;
//    public CommunityGroupsAdapter(Context context, ArrayList<CommunityGroupsModel> communityGroupsList) {
//        this.context=context;
//        this.communityGroupsList=communityGroupsList;
//
//    }
//    @Override
//    public int getItemViewType(int position){
//        CommunityGroupsModel communityGroupsModel=communityGroupsList.get(position);
//        if(communityGroupsModel!=null){
//            communityGroupsModel.getViewType();
//        }
//        return 0;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        switch (viewType){
//            case InvitedUnreadUserCell:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_groups_container,parent,false);
//                return new CommunityGroupsAdapter.InvitedUnreadUserHolder(view);
//            case RequestAcceptedCell:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_request_accepted_cell,parent,false);
//                return new RequestAcceptedHolder(view);
//
//            case JoinedGroupsCell:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_joined_cell,parent,false);
//                return new JoinedGroupsHolder(view);
//
//            case RemovedCell:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_removed_cell,parent,false);
//                return new RemovedViewHoler(view);
//
//            case RequestedCell:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_requested_cell,parent,false);
//                return new RequestedViewHolder(view);
//
//            case UnsubscribedCell:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_unsubscribed_cell,parent,false);
//                return new UnsubscribedViewHolder(view);
//
//            case InvitedReadCell:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_invited_cell,parent,false);
//                return new InvitedReadViewHolder(view);
//
//        }
//       return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
//        final CommunityGroupsModel communityGroupsModel=this.communityGroupsList.get(position);
//        switch (communityGroupsModel.getViewType()){
//            case InvitedUnreadUserCell:
//                ((InvitedUnreadUserHolder)holder).addCommunityGroupTitle.setText(communityGroupsModel.getAddCommunityGroupTitle());
//                ((InvitedUnreadUserHolder)holder).addCommunityGroupAdminName.setText(communityGroupsModel.getAddCommunityGroupAdminName());
//                ((InvitedUnreadUserHolder)holder).addCommunityGroupDescription.setText((communityGroupsModel.getAddCommunityGroupDescription()));
//                ((InvitedUnreadUserHolder)holder).addCommunityGroupCreationDateAndTime.setText((communityGroupsModel.getAddCommunityGroupCreationDateAndTime()));
//                ((InvitedUnreadUserHolder)holder).addCommunityGroupInformationMessage.setText((communityGroupsModel.getAddCommunityGroupInformationMessage()));
//                break;
//            case RequestAcceptedCell:
//                ((RequestAcceptedHolder)holder).addCommunityGroupTitle.setText(communityGroupsModel.getAddCommunityGroupTitle());
//                ((RequestAcceptedHolder)holder).addCommunityGroupAdminName.setText(communityGroupsModel.getAddCommunityGroupAdminName());
//                ((RequestAcceptedHolder)holder).addCommunityGroupDescription.setText((communityGroupsModel.getAddCommunityGroupDescription()));
//                ((RequestAcceptedHolder)holder).addCommunityGroupCreationDateAndTime.setText((communityGroupsModel.getAddCommunityGroupCreationDateAndTime()));
//                ((RequestAcceptedHolder)holder).addCommunityGroupInformationMessage.setText((communityGroupsModel.getAddCommunityGroupInformationMessage()));
//                break;
//            case JoinedGroupsCell:
//                ((JoinedGroupsHolder)holder).addCommunityGroupTitle.setText(communityGroupsModel.getAddCommunityGroupTitle());
//                ((JoinedGroupsHolder)holder).addCommunityGroupAdminName.setText(communityGroupsModel.getAddCommunityGroupAdminName());
//                ((JoinedGroupsHolder)holder).addCommunityGroupDescription.setText((communityGroupsModel.getAddCommunityGroupDescription()));
//                ((JoinedGroupsHolder)holder).addCommunityGroupCreationDateAndTime.setText((communityGroupsModel.getAddCommunityGroupCreationDateAndTime()));
//                ((JoinedGroupsHolder)holder).addCommunityGroupInformationMessage.setText((communityGroupsModel.getAddCommunityGroupInformationMessage()));
//                break;
//            case RemovedCell:
//                ((RemovedViewHoler)holder).addCommunityGroupTitle.setText(communityGroupsModel.getAddCommunityGroupTitle());
//                ((RemovedViewHoler)holder).addCommunityGroupAdminName.setText(communityGroupsModel.getAddCommunityGroupAdminName());
//                ((RemovedViewHoler)holder).addCommunityGroupDescription.setText((communityGroupsModel.getAddCommunityGroupDescription()));
//                ((RemovedViewHoler)holder).addCommunityGroupCreationDateAndTime.setText((communityGroupsModel.getAddCommunityGroupCreationDateAndTime()));
//                ((RemovedViewHoler)holder).addCommunityGroupInformationMessage.setText((communityGroupsModel.getAddCommunityGroupInformationMessage()));
//                break;
//
//            case RequestedCell:
//                ((RequestedViewHolder)holder).addCommunityGroupTitle.setText(communityGroupsModel.getAddCommunityGroupTitle());
//                ((RequestedViewHolder)holder).addCommunityGroupAdminName.setText(communityGroupsModel.getAddCommunityGroupAdminName());
//                ((RequestedViewHolder)holder).addCommunityGroupDescription.setText((communityGroupsModel.getAddCommunityGroupDescription()));
//                ((RequestedViewHolder)holder).addCommunityGroupCreationDateAndTime.setText((communityGroupsModel.getAddCommunityGroupCreationDateAndTime()));
//                ((RequestedViewHolder)holder).addCommunityGroupInformationMessage.setText((communityGroupsModel.getAddCommunityGroupInformationMessage()));
//                break;
//            case UnsubscribedCell:
//                ((UnsubscribedViewHolder)holder).addCommunityGroupTitle.setText(communityGroupsModel.getAddCommunityGroupTitle());
//                ((UnsubscribedViewHolder)holder).addCommunityGroupAdminName.setText(communityGroupsModel.getAddCommunityGroupAdminName());
//                ((UnsubscribedViewHolder)holder).addCommunityGroupDescription.setText((communityGroupsModel.getAddCommunityGroupDescription()));
//                ((UnsubscribedViewHolder)holder).addCommunityGroupCreationDateAndTime.setText((communityGroupsModel.getAddCommunityGroupCreationDateAndTime()));
//                ((UnsubscribedViewHolder)holder).addCommunityGroupInformationMessage.setText((communityGroupsModel.getAddCommunityGroupInformationMessage()));
//                break;
//            case InvitedReadCell:
//                ((InvitedReadViewHolder)holder).addCommunityGroupTitle.setText(communityGroupsModel.getAddCommunityGroupTitle());
//                ((InvitedReadViewHolder)holder).addCommunityGroupAdminName.setText(communityGroupsModel.getAddCommunityGroupAdminName());
//                ((InvitedReadViewHolder)holder).addCommunityGroupDescription.setText((communityGroupsModel.getAddCommunityGroupDescription()));
//                ((InvitedReadViewHolder)holder).addCommunityGroupCreationDateAndTime.setText((communityGroupsModel.getAddCommunityGroupCreationDateAndTime()));
//                ((InvitedReadViewHolder)holder).addCommunityGroupInformationMessage.setText((communityGroupsModel.getAddCommunityGroupInformationMessage()));
//                break;
//
//
//        }
//    }
//
//
//
//
//    @Override
//    public int getItemCount() {
//        return communityGroupsList.size();
//    }
//    class InvitedUnreadUserHolder extends RecyclerView.ViewHolder {
//        TextView addCommunityGroupTitle,addCommunityGroupAdminName,addCommunityGroupDescription;
//        TextView addCommunityGroupCreationDateAndTime,addCommunityGroupInformationMessage;
//        ImageView addInformationIcon,addAdminProfileIcon;
//        public InvitedUnreadUserHolder(@NonNull View itemView) {
//            super(itemView);
////            addNotificationIndicator=itemView.findViewById(R.id.communityGroupNotificationIndicator);
//            addCommunityGroupTitle=itemView.findViewById(R.id.groupsName);
//            addCommunityGroupAdminName=itemView.findViewById(R.id.groupAdmin);
//            addCommunityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
//            addCommunityGroupDescription=itemView.findViewById(R.id.groupsDescription);
//            addCommunityGroupInformationMessage=itemView.findViewById(R.id.informationMessage);
//            addAdminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
//            addInformationIcon=itemView.findViewById(R.id.informationIcon);
//            addAdminProfileIcon.setImageResource(R.drawable.profile_image);
//            addInformationIcon.setImageResource(R.drawable.information_icon);
//
//        }
//    }
//
//    class RequestAcceptedHolder extends RecyclerView.ViewHolder {
//        TextView addCommunityGroupTitle,addCommunityGroupAdminName,addCommunityGroupDescription;
//        TextView addCommunityGroupCreationDateAndTime,addCommunityGroupInformationMessage;
//        ImageView addInformationIcon,addAdminProfileIcon;
//        public RequestAcceptedHolder(@NonNull View itemView) {
//            super(itemView);
////            addNotificationIndicator=itemView.findViewById(R.id.communityGroupNotificationIndicator);
//            addCommunityGroupTitle=itemView.findViewById(R.id.groupsName);
//            addCommunityGroupAdminName=itemView.findViewById(R.id.groupAdmin);
//            addCommunityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
//            addCommunityGroupDescription=itemView.findViewById(R.id.groupsDescription);
//            addCommunityGroupInformationMessage=itemView.findViewById(R.id.invitedDescription);
//            addAdminProfileIcon=itemView.findViewById(R.id.invitedInformationIcon);
//            addInformationIcon=itemView.findViewById(R.id.informationIcon);
//            addAdminProfileIcon.setImageResource(R.drawable.profile_image);
//            addInformationIcon.setImageResource(R.drawable.information_icon);
//
//        }
//    }
//
//     class JoinedGroupsHolder extends RecyclerView.ViewHolder {
//        TextView addCommunityGroupTitle,addCommunityGroupAdminName,addCommunityGroupDescription;
//        TextView addCommunityGroupCreationDateAndTime,addCommunityGroupInformationMessage;
//        ImageView addInformationIcon,addAdminProfileIcon;
//        public JoinedGroupsHolder(@NonNull View itemView) {
//            super(itemView);
////            addNotificationIndicator=itemView.findViewById(R.id.communityGroupNotificationIndicator);
//            addCommunityGroupTitle=itemView.findViewById(R.id.groupsName);
//            addCommunityGroupAdminName=itemView.findViewById(R.id.groupAdmin);
//            addCommunityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
//            addCommunityGroupDescription=itemView.findViewById(R.id.groupsDescription);
//
//            addAdminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
//
//            addAdminProfileIcon.setImageResource(R.drawable.profile_image);
//
//
//        }
//    }
//
//     class RemovedViewHoler extends RecyclerView.ViewHolder {
//        TextView addCommunityGroupTitle,addCommunityGroupAdminName,addCommunityGroupDescription;
//        TextView addCommunityGroupCreationDateAndTime,addCommunityGroupInformationMessage;
//        ImageView addInformationIcon,addAdminProfileIcon;
//        public RemovedViewHoler(@NonNull View itemView) {
//            super(itemView);
////            addNotificationIndicator=itemView.findViewById(R.id.communityGroupNotificationIndicator);
//            addCommunityGroupTitle=itemView.findViewById(R.id.groupsName);
//            addCommunityGroupAdminName=itemView.findViewById(R.id.groupAdmin);
//            addCommunityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
//            addCommunityGroupDescription=itemView.findViewById(R.id.groupsDescription);
//
//            addAdminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
//
//            addAdminProfileIcon.setImageResource(R.drawable.profile_image);
//
//
//        }
//    }
//    class RequestedViewHolder extends RecyclerView.ViewHolder {
//        TextView addCommunityGroupTitle,addCommunityGroupAdminName,addCommunityGroupDescription;
//        TextView addCommunityGroupCreationDateAndTime,addCommunityGroupInformationMessage;
//        ImageView addInformationIcon,addAdminProfileIcon;
//        public RequestedViewHolder(@NonNull View itemView) {
//            super(itemView);
////            addNotificationIndicator=itemView.findViewById(R.id.communityGroupNotificationIndicator);
//            addCommunityGroupTitle=itemView.findViewById(R.id.groupsName);
//            addCommunityGroupAdminName=itemView.findViewById(R.id.groupAdmin);
//            addCommunityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
//            addCommunityGroupDescription=itemView.findViewById(R.id.groupsDescription);
//            addCommunityGroupInformationMessage=itemView.findViewById(R.id.invitedDescription);
//            addAdminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
//            addInformationIcon=itemView.findViewById(R.id.invitedInformationIcon);
//            addAdminProfileIcon.setImageResource(R.drawable.profile_image);
//            addInformationIcon.setImageResource(R.drawable.information_icon);
//
//        }
//    }
//     class UnsubscribedViewHolder extends RecyclerView.ViewHolder {
//        TextView addCommunityGroupTitle,addCommunityGroupAdminName,addCommunityGroupDescription;
//        TextView addCommunityGroupCreationDateAndTime,addCommunityGroupInformationMessage;
//        ImageView addInformationIcon,addAdminProfileIcon;
//        public UnsubscribedViewHolder(@NonNull View itemView) {
//            super(itemView);
////            addNotificationIndicator=itemView.findViewById(R.id.communityGroupNotificationIndicator);
//            addCommunityGroupTitle=itemView.findViewById(R.id.groupsName);
//            addCommunityGroupAdminName=itemView.findViewById(R.id.groupAdmin);
//            addCommunityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
//            addCommunityGroupDescription=itemView.findViewById(R.id.groupsDescription);
//            addCommunityGroupInformationMessage=itemView.findViewById(R.id.invitedDescription);
//            addAdminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
//            addInformationIcon=itemView.findViewById(R.id.invitedInformationIcon);
//            addAdminProfileIcon.setImageResource(R.drawable.profile_image);
//            addInformationIcon.setImageResource(R.drawable.information_icon);
//
//        }
//    }
//
//
//    class InvitedReadViewHolder extends RecyclerView.ViewHolder {
//        TextView addCommunityGroupTitle,addCommunityGroupAdminName,addCommunityGroupDescription;
//     TextView addCommunityGroupCreationDateAndTime,addCommunityGroupInformationMessage;
//        ImageView addInformationIcon,addAdminProfileIcon;
//        public InvitedReadViewHolder(@NonNull View itemView) {
//            super(itemView);
////            addNotificationIndicator=itemView.findViewById(R.id.communityGroupNotificationIndicator);
//            addCommunityGroupTitle=itemView.findViewById(R.id.groupsName);
//            addCommunityGroupAdminName=itemView.findViewById(R.id.groupAdmin);
//            addCommunityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
//            addCommunityGroupDescription=itemView.findViewById(R.id.groupsDescription);
//            addCommunityGroupInformationMessage=itemView.findViewById(R.id.informationMessage);
//            addAdminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
//            addInformationIcon=itemView.findViewById(R.id.informationIcon);
//            addAdminProfileIcon.setImageResource(R.drawable.profile_image);
//            addInformationIcon.setImageResource(R.drawable.information_icon);
//
//        }
//    }
//}
