package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.GroupChatModel;
import Model.MyTicketsModel;

import static Model.MyTicketsModel.ONE_TYPE;
import static Model.MyTicketsModel.TWO_TYPE;

public class GroupChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<GroupChatModel> groupChatList;
    public GroupChatAdapter(Context context, ArrayList<GroupChatModel> groupChatList) {
    this.groupChatList=groupChatList;
    this.context=context;
    }
    @Override
    public int getItemViewType(int position) {
        GroupChatModel groupChatModel=groupChatList.get(position);
        if(groupChatModel!=null){
            return groupChatModel.getViewType();
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case ONE_TYPE:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_container_group_member,parent,false);
                return new GroupChatAdapter.ViewHolder(view);

            case TWO_TYPE:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.user_message_container,parent,false);
                return new GroupChatAdapter.ViewHolder1(view);
        }
        return null;
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_container_group_member,parent,false);
//        ViewHolder viewHolder=new ViewHolder(view);
//        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final GroupChatModel groupChatModel=this.groupChatList.get(position);
        switch (groupChatModel.getViewType()){
            case ONE_TYPE:
                ((ViewHolder)holder).groupChatDateAndTime.setText(groupChatModel.getAddGroupChatDateAndTime());
                ((ViewHolder)holder).groupChatMemberName.setText(groupChatModel.getAddGroupChatMemberName());
                ((ViewHolder)holder).groupChatMessage.setText(groupChatModel.getAddGroupChatMessage());
                break;
            case TWO_TYPE:
                ((ViewHolder1)holder).groupChatDateAndTime.setText(groupChatModel.getAddGroupChatDateAndTime());
                ((ViewHolder1)holder).groupChatMemberName.setText(groupChatModel.getAddGroupChatMemberName());
                ((ViewHolder1)holder).groupChatMessage.setText(groupChatModel.getAddGroupChatMessage());
                break;
        }
    }


    @Override
    public int getItemCount() {
        return groupChatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupChatDateAndTime,groupChatMemberName,groupChatMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupChatDateAndTime=itemView.findViewById(R.id.messageRecivedDateAndTime);
            groupChatMemberName=itemView.findViewById(R.id.memberName);
            groupChatMessage=itemView.findViewById(R.id.messageContainerGroup);


        }
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView groupChatDateAndTime,groupChatMemberName,groupChatMessage;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            groupChatDateAndTime=itemView.findViewById(R.id.messageSentDateAndTime);
            groupChatMemberName=itemView.findViewById(R.id.userName);
            groupChatMessage=itemView.findViewById(R.id.messageContainerUser);


        }
    }
}
