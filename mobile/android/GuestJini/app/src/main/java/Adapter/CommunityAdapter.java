package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.CommunityActivity;
import com.carmel.guestjini.GroupsActivity;
import com.carmel.guestjini.PeopleActivity;
import com.carmel.guestjini.ProfileActivity;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.CommunityModel;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CommunityModel> communityModels;
    public CommunityAdapter(Context context, ArrayList<CommunityModel> communityModelArrayList) {
        this.context=context;
        this.communityModels =communityModelArrayList;
    }

    @NonNull
    @Override
    public CommunityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.community_landing_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityAdapter.ViewHolder holder, int position) {
        CommunityModel communityModel= communityModels.get(position);
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
                    intent =  new Intent(context, ProfileActivity.class);
                    break;
                case 1:
                    intent =  new Intent(context, PeopleActivity.class);
                    break;
                case 2:
                    intent =  new Intent(context, GroupsActivity.class);
                    break;
                default:
                    intent =  new Intent(context, CommunityActivity.class);
                    break;
            }
            context.startActivity(intent);
        }
    }
  }

