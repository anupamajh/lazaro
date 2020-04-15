package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.CommunityGroupsActivity;
import com.carmel.guestjini.InterestGroupsActivity;

import com.carmel.guestjini.MyGroupsActivity;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.CommunityModel;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CommunityModel> communityModels;
    public GroupsAdapter(Context context, ArrayList<CommunityModel> groupsModelArrayList) {
        this.context=context;
        this.communityModels =groupsModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_landing_list,parent,false);
        return new GroupsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommunityModel communityModel=communityModels.get(position);
        holder.title.setText(String.valueOf(communityModel.getTitle()));
        holder.description.setText(String.valueOf(communityModel.getDescription()));

    }

    @Override
    public int getItemCount() {
        return communityModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title,description;
        private View notificationBar;
        private final Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationBar=itemView.findViewById(R.id.notifiactionBar);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
            context=itemView.getContext();
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            switch (getAdapterPosition()){
                case 0:
                    intent =  new Intent(context, InterestGroupsActivity.class);
                    break;
                case 1:
                    intent =  new Intent(context, CommunityGroupsActivity.class);
                    break;
                case 2:
                    intent =  new Intent(context, MyGroupsActivity.class);
                    break;
                default:
                    intent =  new Intent(context, CommunityActivity.class);
                    break;
            }
            context.startActivity(intent);
        }
        }
    }

