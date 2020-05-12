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

import com.carmel.guestjini.Common.AgeCalculator;
import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Models.Ticket.Ticket;
import com.carmel.guestjini.R;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Ticket> tickets;
    private Context context;
    private TicketAdapter.OnItemClickListener onItemClickListener;

    public TicketAdapter(ArrayList<Ticket> tickets,OnItemClickListener onItemClickListener) {
        this.tickets = tickets;
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public int getItemViewType(int position) {
        Ticket ticket=tickets.get(position);
        if(ticket!=null){
            //TODO: return ticket.getViewType();
        }
        return 0;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_tickets_list,parent,false);
        return new OneViewHolder(view,onItemClickListener);
//
//        switch (viewType){
//            case ONE_TYPE:
//                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_tickets_list,parent,false);
//                return new OneViewHolder(view,onItemClickListener);
//
//            case TWO_TYPE:
//                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.draft_list,parent,false);
//                return new TwoViewHolder(view,onItemClickListener);
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Ticket ticket =tickets.get(position);
        String strTicketStatus = "OPEN";
        switch (ticket.getTicketStatus()){
            case 3:
            {
                strTicketStatus = "OPEN";
            }
            break;
            case 2:
            {
                strTicketStatus = "STARTED";
            }
            break;
            case 1:
            {
                strTicketStatus = "CLOSED";
            }
            break;
            default:
            {
                strTicketStatus = "NEW";
            }
            break;

        }
        ((OneViewHolder)holder).ticketsStatus.setText(strTicketStatus);
        Date creationDate = DateUtil.convertToDate(ticket.getCreationTime());
        LocalDate localCreationDate = Instant.ofEpochMilli(creationDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Date today = new Date();
        LocalDate localeToday = Instant.ofEpochMilli(today.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        ((OneViewHolder)holder).ticketsDate.setText(DateUtil.getFormattedDate(creationDate));
        ((OneViewHolder)holder).ticketsName.setText(ticket.getTicketTitle());
        ((OneViewHolder)holder).ticketsValue.setText(ticket.getTicketNo());
        ((OneViewHolder)holder).ticketsTime.setText(String.valueOf(AgeCalculator.calculateDateLapse(localCreationDate, localeToday)));
        //   TODO:    switch (myTicketsModel.getViewType())
    }

    @Override
    public int getItemCount() {
        return tickets.size();
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

    public void updateData(List<Ticket> ticketList){
        this.tickets.addAll(ticketList);
        notifyDataSetChanged();;
    }
}
