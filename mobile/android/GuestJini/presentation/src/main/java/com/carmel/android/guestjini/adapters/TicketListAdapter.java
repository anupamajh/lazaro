package com.carmel.android.guestjini.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.android.guestjini.R;
import com.carmel.android.guestjini.models.Ticket.Ticket;

import java.util.List;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.TicketRowHolder> {

    List<Ticket> ticketList;
    private Context context;
    private int SHOW_PROGRESS = 1;
    private RecyclerViewClickListener recyclerViewClickListener;


    public interface RecyclerViewClickListener {
        void onItemClick(View view, int position);
    }


    public TicketListAdapter(List<Ticket> ticketList, Context context, RecyclerViewClickListener recyclerViewClickListener) {
        this.ticketList = ticketList;
        this.context = context;
        this.recyclerViewClickListener = recyclerViewClickListener;

    }

    @Override
    public int getItemViewType(int position) {
        return ticketList.get(position) == null ? SHOW_PROGRESS : super.getItemViewType(position);
    }

    @NonNull
    @Override
    public TicketRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketRowHolder(LayoutInflater.from(context).inflate(R.layout.ticket_list_row,parent, false), context, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketRowHolder holder, int position) {
        holder.bind(ticketList.get(position));
    }

    @Override
    public int getItemCount() {
        return ticketList == null ? 0 : ticketList.size();
    }

    public void swapDataSet(List<Ticket> ticketList) {
        this.ticketList = ticketList;
        //now, tell the adapter about the update
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    public class TicketRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ticketStatusText;
        TextView ticketDate;
        TextView ticketTitle;
        TextView ticketNo;
        Context context;
        LinearLayout ticketStatusWrap;

        private RecyclerViewClickListener recyclerViewClickListener;

        public TicketRowHolder(View itemView, Context context, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            this.initViews(context);
            this.recyclerViewClickListener = recyclerViewClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onItemClick(view, getAdapterPosition());
        }

        private void initViews(Context context) {
            this.context = context;
            ticketStatusText = itemView.findViewById(R.id.ticket_status);
            ticketDate = itemView.findViewById(R.id.ticket_date);
            ticketTitle = itemView.findViewById(R.id.ticket_title);
            ticketNo = itemView.findViewById(R.id.ticket_no);
            ticketStatusWrap = itemView.findViewById(R.id.ticket_status_wrap);

        }

        public void bind(Ticket ticket) {
            if(ticket.getTicketStatus() == 3){
                ticketStatusText.setText("OPEN");
                ticketStatusWrap.setBackgroundColor(context.getColor(R.color.coral));
            }else if(ticket.getTicketStatus() == 2){
                ticketStatusText.setText("STARTED");
                ticketStatusWrap.setBackgroundColor(context.getColor(R.color.squash));
            }else if(ticket.getTicketStatus() == 1){
                ticketStatusText.setText("CLOSED");
                ticketStatusWrap.setBackgroundColor(context.getColor(R.color.blueyGrey));
            }else{
                ticketStatusText.setText("NEW");
                ticketStatusWrap.setBackgroundColor(context.getColor(R.color.coral));
            }

            ticketDate.setText(ticket.getCreationTime());
            ticketTitle.setText(ticket.getTicketTitle());
            ticketNo.setText(ticket.getTicketNo());
        }
    }
}

