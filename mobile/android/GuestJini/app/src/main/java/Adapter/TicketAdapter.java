package Adapter;

import android.content.Context;
import android.media.Image;
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

import static Model.MyTicketsModel.ONE_TYPE;
import static Model.MyTicketsModel.TWO_TYPE;

public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<MyTicketsModel> myTicketsModels;
    private Context context;
    private TicketAdapter.OnItemClickListener onItemClickListener;

    public TicketAdapter(ArrayList<MyTicketsModel> myTicketsModelsList,OnItemClickListener onItemClickListener) {
        this.myTicketsModels = myTicketsModelsList;
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public int getItemViewType(int position) {
        MyTicketsModel myTicketsModel=myTicketsModels.get(position);
        if(myTicketsModel!=null){
            return myTicketsModel.getViewType();
        }
        return 0;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case ONE_TYPE:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_tickets_list,parent,false);
                return new OneViewHolder(view,onItemClickListener);

            case TWO_TYPE:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.draft_list,parent,false);
                return new TwoViewHolder(view,onItemClickListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        MyTicketsModel myTicketsModel =myTicketsModels.get(position);
       switch (myTicketsModel.getViewType()){
           case ONE_TYPE:
               ((OneViewHolder)holder).ticketsStatus.setText(myTicketsModel.getTicketsStatus());
               ((OneViewHolder)holder).ticketsDate.setText(myTicketsModel.getTicketsDateAndTime());
               ((OneViewHolder)holder).ticketsName.setText(myTicketsModel.getTicketsName());
               ((OneViewHolder)holder).ticketsNo.setText(myTicketsModel.getTicketsNo());
               ((OneViewHolder)holder).ticketsValue.setText(myTicketsModel.getTicketsValue());
               ((OneViewHolder)holder).clock.setText(myTicketsModel.getClock());
               ((OneViewHolder)holder).ticketsTime.setText(myTicketsModel.getTicketsTime());
               ((OneViewHolder)holder).notification.setImageResource(myTicketsModel.getNotificationIcon());

                break;
           case TWO_TYPE:
               ((TwoViewHolder)holder).ticketsStatus.setText(myTicketsModel.getTicketsStatus());
               ((TwoViewHolder)holder).ticketsDate.setText(myTicketsModel.getTicketsDateAndTime());
               ((TwoViewHolder)holder).ticketsName.setText(myTicketsModel.getTicketsName());
               ((TwoViewHolder)holder).ticketDelete.setText(myTicketsModel.getDelete());
                break;
       }
    }

    @Override
    public int getItemCount() {
        return myTicketsModels.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView ticketsStatus,ticketsDate,ticketsName,ticketsNo,ticketsValue,clock,ticketsTime;
        ImageView notification;
        CardView cardView;
        OnItemClickListener onItemClickListener;
        public OneViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            ticketsStatus=itemView.findViewById(R.id.ticketsStatus);
            ticketsDate=itemView.findViewById(R.id.tickestsDateAndTime);
            ticketsName=itemView.findViewById(R.id.ticketsName);
            ticketsNo=itemView.findViewById(R.id.ticketsNo);
            ticketsValue=itemView.findViewById(R.id.ticketsValue);
            clock=itemView.findViewById(R.id.clock);
            ticketsTime=itemView.findViewById(R.id.clockTime);
            this.onItemClickListener=onItemClickListener;
            notification=itemView.findViewById(R.id.notification);
            itemView.setOnClickListener(this);
//            cardView=itemView.findViewById(R.id.OpenCardView);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    class TwoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ticketsStatus,ticketsDate,ticketsName,ticketDelete;
        ImageView deleteIcon;
        OnItemClickListener onItemClickListener;
        public TwoViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            ticketsStatus=itemView.findViewById(R.id.ticketsStatus);
            ticketsDate=itemView.findViewById(R.id.tickestsDateAndTime);
            ticketsName=itemView.findViewById(R.id.ticketsName);
//            cardView=itemView.findViewById(R.id.OpenCardView);
            ticketDelete=itemView.findViewById(R.id.deleteText);
            deleteIcon=itemView.findViewById(R.id.deleteIcon);
            this.onItemClickListener=onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onclickDraft(getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onClick(int position,String name);
        void onclickDraft(int position);
    }
}
