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

import Model.MyGroupsRequestsModel;

public class MyGroupsRequestsAdpter extends RecyclerView.Adapter<MyGroupsRequestsAdpter.ViewHolder> {
    private Context context;
    private ArrayList<MyGroupsRequestsModel> myGroupsRequestslist;

    public MyGroupsRequestsAdpter(Context context, ArrayList<MyGroupsRequestsModel> myGroupsRequestslist) {
        this.context = context;
        this.myGroupsRequestslist = myGroupsRequestslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.requests_members_list,parent,false);
        return new MyGroupsRequestsAdpter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyGroupsRequestsModel myGroupsRequestsModel=myGroupsRequestslist.get(position);
        holder.requestsProfilePicture.setImageResource(myGroupsRequestsModel.getRequestsProfilePicture());
        holder.requestsMemberName.setText(myGroupsRequestsModel.getRequestsMembersName());
        holder.requestsDate.setText(myGroupsRequestsModel.getRequestsDate());
        holder.requestsStatuIcon.setImageResource(myGroupsRequestsModel.getRequestStatusIcon());
        holder.requestsStatus.setText(myGroupsRequestsModel.getRequestsStatus());
    }

    @Override
    public int getItemCount() {
        return myGroupsRequestslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView requestsProfilePicture,requestsStatuIcon;
        private TextView requestsMemberName,requestsDate,requestsStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requestsProfilePicture=itemView.findViewById(R.id.requestsProfilePicture);
            requestsMemberName=itemView.findViewById(R.id.requestsMemberName);
            requestsDate=itemView.findViewById(R.id.requestsDate);
            requestsStatus=itemView.findViewById(R.id.requestsStatus);
            requestsStatuIcon=itemView.findViewById(R.id.requestsStatusButton);
        }
    }
}
