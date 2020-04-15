package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.InvitingMembersModel;
import Model.JoinedMemberModel;

public class JoinedMembersAdapter extends RecyclerView.Adapter<JoinedMembersAdapter.ViewHolder> {
    private Context context;
    private  ArrayList<JoinedMemberModel> joinedMembersList;
    private OnItemClickListener onItemClickListener;
    public JoinedMembersAdapter(Context context, ArrayList<JoinedMemberModel> joinedMembersArrayList,OnItemClickListener onItemClickListener) {
        this.context=context;
        this.joinedMembersList=joinedMembersArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.joined_members_itemlist,parent,false);
        return new JoinedMembersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final JoinedMemberModel joinedMembersModel=joinedMembersList.get(position);
        holder.joinedProfilePicture.setImageResource(joinedMembersModel.getJoinedProfilePicture());
        holder.joinedMemberName.setText(joinedMembersModel.getJoinedMembersName());
        holder.joinedDate.setText(joinedMembersModel.getJoinedDate());
        holder.removeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(joinedMembersModel);
            }
        });
    }

    private void removeItem(JoinedMemberModel joinedMembersModel) {
        int position =joinedMembersList.indexOf(joinedMembersModel);
        joinedMembersList.remove(position);
        notifyItemRemoved(position);
    }
    
    @Override
    public int getItemCount() {
        return joinedMembersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView joinedProfilePicture,removeIcon;
        private TextView joinedMemberName,joinedDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            joinedProfilePicture=itemView.findViewById(R.id.joinedProfilePicture);
            joinedMemberName=itemView.findViewById(R.id.joinedMemberName);
            joinedDate=itemView.findViewById(R.id.joinedDate);
            removeIcon=itemView.findViewById(R.id.removeIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener{
        void onClick(int position);
    }
}
