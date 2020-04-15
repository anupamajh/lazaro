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
import java.util.PriorityQueue;

import Model.InvitingMembersModel;

public class PendingRequestsAdapter extends RecyclerView.Adapter<PendingRequestsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<InvitingMembersModel> pendingArrayList;
    public PendingRequestsAdapter(Context context, ArrayList<InvitingMembersModel> pendingRequestsArrayList) {
        this.context=context;
        this.pendingArrayList=pendingRequestsArrayList;
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
        holder.invitationSentMessage.setText(pendingRequestsModel.getInvitationSentMessage());
        holder.invitationSentDate.setText(pendingRequestsModel.getInvitationSentDate());
    }

    @Override
    public int getItemCount() {
        return pendingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView profilePicture;
        private TextView profileName,profileGender,invitationSentMessage,invitationSentDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture=itemView.findViewById(R.id.profilePicture);
            profileName=itemView.findViewById(R.id.profileName);
            profileGender=itemView.findViewById(R.id.profileGender);
            invitationSentMessage=itemView.findViewById(R.id.invitationSentMessage);
            invitationSentDate=itemView.findViewById(R.id.invitationSentDate);
        }
    }
}
