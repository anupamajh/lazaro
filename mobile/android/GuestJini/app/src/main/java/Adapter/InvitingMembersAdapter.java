package Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.InvitingMembersModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class InvitingMembersAdapter extends RecyclerView.Adapter<InvitingMembersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<InvitingMembersModel> invitingMembersList;
    private OnItemClickListener onItemClickListener;
    public InvitingMembersAdapter(Context context, ArrayList<InvitingMembersModel> invitingMembersArrayList,OnItemClickListener onItemClickListener) {
        this.context=context;
        this.invitingMembersList=invitingMembersArrayList;
        this.onItemClickListener=onItemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_members_list,parent,false);
        return new InvitingMembersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        InvitingMembersModel invitingMembersModel=invitingMembersList.get(position);
        holder.profilePicture.setImageResource(invitingMembersModel.getProfilePicture());
        holder.favouritesIndicator.setImageResource(invitingMembersModel.getFavouritesIndicator());
        holder.profileName.setText(invitingMembersModel.getProfileName());
        holder.profileGender.setText(invitingMembersModel.getProfileGender());
        holder.invitationSentMessage.setText(invitingMembersModel.getInvitationSentMessage());
        holder.invitationSentDate.setText(invitingMembersModel.getInvitationSentDate());
        holder.inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.inviteButton.setVisibility(View.GONE);
                holder.inviteSentLayout.setVisibility(View.VISIBLE);
                Toast toast = new Toast(context);
                ViewGroup viewGroup = null;
                View dialogView = LayoutInflater.from(context).inflate(R.layout.toast_layout, viewGroup, false);
                TextView text = (TextView) dialogView.findViewById(R.id.visibleToast);
                text.setText("INVITE SENT \nLorem ipsum doler sit amet.");
                toast.setView(dialogView);
                toast.setGravity(Gravity.getAbsoluteGravity(0,0),0,520);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return invitingMembersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView favouritesIndicator;
        private TextView profileName,profileGender,invitationSentDate,invitationSentMessage;
        private Button inviteButton;
        private LinearLayout inviteSentLayout;
        CircleImageView profilePicture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture=itemView.findViewById(R.id.peopleProfilePicture);
            favouritesIndicator=itemView.findViewById(R.id.favouritesIndicator);
            profileName=itemView.findViewById(R.id.profileName);
            profileGender=itemView.findViewById(R.id.profileGender);
            invitationSentDate=itemView.findViewById(R.id.invitationSentDate);
            invitationSentMessage=itemView.findViewById(R.id.invitationSentMessage);
            inviteButton=itemView.findViewById(R.id.inviteButton);
            inviteSentLayout=itemView.findViewById(R.id.inviteSentLayout);
//            inviteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);

    }
}
