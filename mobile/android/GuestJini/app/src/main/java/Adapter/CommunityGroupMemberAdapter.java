package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.InterestGroupMembersModel;

public class CommunityGroupMemberAdapter extends RecyclerView.Adapter<CommunityGroupMemberAdapter.ViewHolder> {
    ArrayList<InterestGroupMembersModel> groupMembersModels;
    Context context;
    public CommunityGroupMemberAdapter(Context context, ArrayList<InterestGroupMembersModel> communityGroupMemberslist) {
        this.context=context;
        this.groupMembersModels =communityGroupMemberslist;
    }

    @NonNull
    @Override
    public CommunityGroupMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_group_members_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CommunityGroupMemberAdapter.ViewHolder holder, int position) {
        final InterestGroupMembersModel interestGroupMembersModel=this.groupMembersModels.get(position);
        holder.interestGroupMemberProfileName.setText(interestGroupMembersModel.getGroupMemberProfileName());
        holder.interestGroupMemberProfileCreatedDate.setText(interestGroupMembersModel.getGroupMemberProfileCreatedDate());
        holder.interestGroupMemberProfileImage.setImageResource(interestGroupMembersModel.getGroupProfileIcon());


    }

    @Override
    public int getItemCount() {
        return groupMembersModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView interestGroupMemberProfileName,interestGroupMemberProfileCreatedDate,profileDivider;
        ImageView interestGroupMemberProfileImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            interestGroupMemberProfileImage=itemView.findViewById(R.id.interestGroupMemberProfileImage);
            interestGroupMemberProfileName=itemView.findViewById(R.id.interestGroupMemberProfileName);
            interestGroupMemberProfileCreatedDate=itemView.findViewById(R.id.interestGroupMemberCreatedDate);
            profileDivider=itemView.findViewById(R.id.profileDivider);
            interestGroupMemberProfileImage.setImageResource(R.drawable.profile_image);
        }
    }
}
