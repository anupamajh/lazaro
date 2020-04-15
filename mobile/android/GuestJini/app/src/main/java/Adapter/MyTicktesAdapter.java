package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.MyTicketsModel;

public class MyTicktesAdapter extends RecyclerView.Adapter<MyTicktesAdapter.ViewHolder> {
    private Context context;
    ArrayList<MyTicketsModel> myTicketsModelArrayList;
    public MyTicktesAdapter(Context context, ArrayList<MyTicketsModel> myTicketsModelsList) {
      this.context=context;
      this.myTicketsModelArrayList=myTicketsModelsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final  View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_tickets_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyTicketsModel myTicketsModel=this.myTicketsModelArrayList.get(position);
        holder.ticketsStatus.setText(String.valueOf(myTicketsModel.getTicketsStatus()));
        holder.ticketsDate.setText(String.valueOf(myTicketsModel.getTicketsDateAndTime()));
        holder.ticketsName.setText(String.valueOf(myTicketsModel.getTicketsName()));
        holder.ticketsNo.setText(String.valueOf(myTicketsModel.getTicketsNo()));
        holder.ticketsValue.setText(String.valueOf(myTicketsModel.getTicketsValue()));
        holder.clock.setText(String.valueOf(myTicketsModel.getClock()));
        holder.ticketsTime.setText(String.valueOf(myTicketsModel.getTicketsTime()));

    }

    @Override
    public int getItemCount() {
        return myTicketsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView notificationIcon;
        TextView ticketsStatus,ticketsDate,ticketsName,ticketsNo,ticketsValue,clock,ticketsTime;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketsStatus=itemView.findViewById(R.id.ticketsStatus);
            ticketsDate=itemView.findViewById(R.id.tickestsDateAndTime);
            ticketsName=itemView.findViewById(R.id.ticketsName);
            ticketsNo=itemView.findViewById(R.id.ticketsNo);
            ticketsValue=itemView.findViewById(R.id.ticketsValue);
            clock=itemView.findViewById(R.id.clock);
            ticketsTime=itemView.findViewById(R.id.clockTime);
            cardView=itemView.findViewById(R.id.OpenCardView);
        }
    }
}
