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

import Model.CommunityGroupsModel;

import static Model.CommunityGroupsModel.InvitedUnreadUserCell;
import static Model.CommunityGroupsModel.JoinedGroupsCell;
import static Model.CommunityGroupsModel.RemovedCell;


public class MyGroupsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<CommunityGroupsModel> myGroupsList;
    private OnItemClickListener onItemClickListener;
    public MyGroupsAdapter(Context context, ArrayList<CommunityGroupsModel> myGroupsArrayList,OnItemClickListener onItemClickListener) {
        this.context=context;
        this.myGroupsList=myGroupsArrayList;
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public int getItemViewType(int position){
        CommunityGroupsModel myGroupsModel=myGroupsList.get(position);
        if(myGroupsModel!=null){
            return myGroupsModel.getViewType();
        }
        return 0;
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
        CommunityGroupsModel myGroupsModel=myGroupsList.get(position);
        switch (myGroupsModel.getViewType()) {

            case JoinedGroupsCell:
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupTitle.setText(myGroupsModel.getCommunityGroupTitle());
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupAdmin.setText(myGroupsModel.getCommunityGroupAdmin());
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupDescription.setText(myGroupsModel.getCommunityGroupDescription());
                ((MyGroupsAdapter.JoinedViewHolder) holder).communityGroupCreationDateAndTime.setText(myGroupsModel.getCommunityGroupCreationDateAndTime());
                ((MyGroupsAdapter.JoinedViewHolder) holder).adminProfileIcon.setImageResource(myGroupsModel.getAdminProfileIcon());
                break;
            case RemovedCell:
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupTitle.setText(myGroupsModel.getCommunityGroupTitle());
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupAdmin.setText(myGroupsModel.getCommunityGroupAdmin());
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupDescription.setText(myGroupsModel.getCommunityGroupDescription());
                ((MyGroupsAdapter.GroupContainerHolder) holder).communityGroupCreationDateAndTime.setText(myGroupsModel.getCommunityGroupCreationDateAndTime());
                ((MyGroupsAdapter.GroupContainerHolder) holder).adminProfileIcon.setImageResource(myGroupsModel.getAdminProfileIcon());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return myGroupsList.size();
    }

    class JoinedViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime;
        ImageView adminProfileIcon;
        public JoinedViewHolder(@NonNull View itemView) {
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
            onItemClickListener.onClikJoinedGroup(getAdapterPosition());
        }
    }
    class GroupContainerHolder extends RecyclerView.ViewHolder {
        TextView communityGroupTitle,communityGroupAdmin,communityGroupDescription;
        TextView communityGroupCreationDateAndTime;
        ImageView adminProfileIcon;
        public GroupContainerHolder(@NonNull View itemView) {
            super(itemView);
            communityGroupTitle=itemView.findViewById(R.id.groupsName);
            communityGroupAdmin=itemView.findViewById(R.id.groupAdmin);
            communityGroupCreationDateAndTime=itemView.findViewById(R.id.groupCreationDateAndTime);
            communityGroupDescription =itemView.findViewById(R.id.groupsDescription);
            adminProfileIcon=itemView.findViewById(R.id.communityGroupsProfileIcon);
            adminProfileIcon.setImageResource(R.drawable.profile_image);


        }
    }

    public interface OnItemClickListener{
        void onClikJoinedGroup(int position);
        void onClikRemovedGroup(int position);



    }
}
