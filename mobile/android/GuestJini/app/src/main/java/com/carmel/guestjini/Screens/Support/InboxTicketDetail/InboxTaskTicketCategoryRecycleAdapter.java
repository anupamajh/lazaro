package com.carmel.guestjini.Screens.Support.InboxTicketDetail;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketCategoryItem.InboxTicketCategoryItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class InboxTaskTicketCategoryRecycleAdapter
        extends RecyclerView.Adapter<InboxTaskTicketCategoryRecycleAdapter.TicketCategoryViewHolder>
        implements InboxTicketCategoryItemViewMVC.Listener {

    static class TicketCategoryViewHolder extends RecyclerView.ViewHolder {
        private final InboxTicketCategoryItemViewMVC viewMVC;

        public TicketCategoryViewHolder(@NonNull InboxTicketCategoryItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<TicketCategory> ticketCategoryList = new ArrayList<>();

    public InboxTaskTicketCategoryRecycleAdapter(ViewMVCFactory viewMVCFactory) {
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindTicketCategories(List<TicketCategory> ticketCategoryList) {
        this.ticketCategoryList = new ArrayList<>(ticketCategoryList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InboxTicketCategoryItemViewMVC viewMvc = viewMVCFactory.getInboxTicketCategoryItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new TicketCategoryViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketCategoryViewHolder holder, int position) {
        holder.viewMVC.bindTicketCategory(this.ticketCategoryList.get(position));
        if (position == 0) {
            holder.viewMVC.hideSeparator(true);
        } else {
            holder.viewMVC.hideSeparator(false);
        }
    }

    @Override
    public int getItemCount() {
        return this.ticketCategoryList.size();
    }

}