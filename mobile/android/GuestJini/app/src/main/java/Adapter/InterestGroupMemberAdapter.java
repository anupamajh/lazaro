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

public class InterestGroupMemberAdapter extends RecyclerView.Adapter<InterestGroupMemberAdapter.ViewHolder> {
    ArrayList<InterestGroupMembersModel> interestGroupMembersList;
    Context context;
    public InterestGroupMemberAdapter(Context context, ArrayList<InterestGroupMembersModel> interestGroupMemberslist) {
        this.context=context;
        this.interestGroupMembersList=interestGroupMemberslist;
    }

    @NonNull
    @Override
    public InterestGroupMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_group_members_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final InterestGroupMemberAdapter.ViewHolder holder, int position) {
        final InterestGroupMembersModel interestGroupMembersModel=this.interestGroupMembersList.get(position);
        holder.interestGroupMemberProfileName.setText(interestGroupMembersModel.getGroupMemberProfileName());
        holder.getInterestGroupMemberProfileCreatedDate.setText(interestGroupMembersModel.getGroupMemberProfileCreatedDate());
        holder.interestGroupMemberProfileImage.setImageResource(interestGroupMembersModel.getGroupProfileIcon());

    }

    @Override
    public int getItemCount() {
        return interestGroupMembersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView interestGroupMemberProfileName,getInterestGroupMemberProfileCreatedDate,profileDivider;
        ImageView interestGroupMemberProfileImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            interestGroupMemberProfileImage=itemView.findViewById(R.id.interestGroupMemberProfileImage);
            interestGroupMemberProfileName=itemView.findViewById(R.id.interestGroupMemberProfileName);
            getInterestGroupMemberProfileCreatedDate=itemView.findViewById(R.id.interestGroupMemberCreatedDate);
            profileDivider=itemView.findViewById(R.id.profileDivider);

        }
    }
}
