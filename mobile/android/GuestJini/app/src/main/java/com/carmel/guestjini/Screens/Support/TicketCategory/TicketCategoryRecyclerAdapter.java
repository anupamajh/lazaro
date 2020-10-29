package com.carmel.guestjini.Screens.Support.TicketCategory;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryItem.TicketCategoryItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class TicketCategoryRecyclerAdapter
        extends RecyclerView.Adapter<TicketCategoryRecyclerAdapter.TicketCategoryViewHolder>
        implements TicketCategoryItemViewMVC.Listener {
    public interface Listener {
        void onTicketCategoryItemClicked(TicketCategory ticketCategory);
    }

    static class TicketCategoryViewHolder extends RecyclerView.ViewHolder {
        private final TicketCategoryItemViewMVC viewMVC;

        public TicketCategoryViewHolder(@NonNull TicketCategoryItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<TicketCategory> ticketCategoryList = new ArrayList<>();

    public TicketCategoryRecyclerAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindTicketCategories(List<TicketCategory> ticketCategoryList) {
        this.ticketCategoryList = new ArrayList<>(ticketCategoryList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TicketCategoryItemViewMVC viewMvc = viewMVCFactory.getTicketCategoryItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new TicketCategoryViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketCategoryViewHolder holder, int position) {
        holder.viewMVC.bindTicketCategory(this.ticketCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.ticketCategoryList.size();
    }

    @Override
    public void onTicketCategoryItemClicked(TicketCategory ticketCategory) {
        listener.onTicketCategoryItemClicked(ticketCategory);
    }
}
