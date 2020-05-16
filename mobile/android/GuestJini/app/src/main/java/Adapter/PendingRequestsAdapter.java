package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.InvitingMembersModel;

public class PendingRequestsAdapter extends RecyclerView.Adapter<PendingRequestsAdapter.ViewHolder> {
    private Context context;
    private PendingRequestDelegate pendingRequestDelegate;
    private ArrayList<InvitingMembersModel> pendingArrayList;
    public PendingRequestsAdapter(Context context, ArrayList<InvitingMembersModel> pendingRequestsArrayList) {
        this.context=context;
        this.pendingArrayList=pendingRequestsArrayList;
    }

    public void setPendingRequestDelegate(PendingRequestDelegate pendingRequestDelegate) {
        this.pendingRequestDelegate = pendingRequestDelegate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_requests_members_list,parent,false);
        return new PendingRequestsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InvitingMembersModel pendingRequestsModel=pendingArrayList.get(position);
        holder.profilePicture.setImageResource(pendingRequestsModel.getProfilePicture());
        holder.profileName.setText(pendingRequestsModel.getProfileName());
        holder.profileGender.setText(pendingRequestsModel.getProfileGender());
        holder.invitationSentDate.setVisibility(View.GONE);
        if(pendingRequestsModel.getIsInvited() == 0){
            holder.btnInvite.setVisibility(View.VISIBLE);
            holder.invitationSentMessage.setVisibility(View.GONE);
            holder.btnInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = pendingRequestsModel.getPersonId();
                    pendingRequestDelegate.onInviteClicked(
                            pendingRequestsModel.getUserId(),
                            pendingRequestsModel.getGroupId()
                    );
                }
            });
        }else {
            holder.btnInvite.setVisibility(View.GONE);
            if(pendingRequestsModel.getHasAcceptedInvitation() == 0){
                holder.invitationSentMessage.setText("Invitation Sent");
            }else {
                holder.invitationSentMessage.setText("Accepted");
            }
        }
    }

    @Override
    public int getItemCount() {
        return pendingArrayList.size();
    }

    public void update(ArrayList<InvitingMembersModel> pendingRequestsArrayList) {
        this.pendingArrayList = pendingRequestsArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView profilePicture;
        private TextView profileName,profileGender,invitationSentMessage,invitationSentDate;
        Button btnInvite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture=itemView.findViewById(R.id.profilePicture);
            profileName=itemView.findViewById(R.id.profileName);
            profileGender=itemView.findViewById(R.id.profileGender);
            invitationSentMessage=itemView.findViewById(R.id.invitationSentMessage);
            invitationSentDate=itemView.findViewById(R.id.invitationSentDate);
            btnInvite = itemView.findViewById(R.id.inviteButton);
        }
    }

    public interface PendingRequestDelegate{
        void onInviteClicked(String userId, String groupId);
    }
}
