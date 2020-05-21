package com.carmel.guestjini.Screens.Support.TicketList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListItem.TicketListItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class TicketListRecyclerAdapter
        extends RecyclerView.Adapter<TicketListRecyclerAdapter.TicketViewHolder>
        implements TicketListItemViewMVC.Listener {

    public interface Listener {
        void onTicketClicked(Ticket ticket);
    }

    static class TicketViewHolder extends RecyclerView.ViewHolder {
        private final TicketListItemViewMVC viewMVC;

        public TicketViewHolder(@NonNull TicketListItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<Ticket> tickets = new ArrayList<>();

    public TicketListRecyclerAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindTickets(List<Ticket> ticketList) {
        this.tickets = new ArrayList<>(ticketList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TicketListItemViewMVC viewMvc = viewMVCFactory.getTicketListItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new TicketViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        holder.viewMVC.bindTicket(this.tickets.get(position));
    }

    @Override
    public int getItemCount() {
        return this.tickets.size();
    }

    @Override
    public void onTicketClicked(Ticket ticket) {
        listener.onTicketClicked(ticket);
    }
}
