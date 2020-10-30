package com.carmel.guestjini.Screens.Support.TicketDetail;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.TicketDetail.TaskTicketCategory.TaskTicketCategoryViewMVC;

import java.util.ArrayList;
import java.util.List;

public class TaskTicketCategoryRecycleAdapter
        extends RecyclerView.Adapter<TaskTicketCategoryRecycleAdapter.TicketCategoryViewHolder>
        implements TaskTicketCategoryViewMVC.Listener {

    static class TicketCategoryViewHolder extends RecyclerView.ViewHolder {
        private final TaskTicketCategoryViewMVC viewMVC;

        public TicketCategoryViewHolder(@NonNull TaskTicketCategoryViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<TicketCategory> ticketCategoryList = new ArrayList<>();

    public TaskTicketCategoryRecycleAdapter(ViewMVCFactory viewMVCFactory) {
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindTicketCategories(List<TicketCategory> ticketCategoryList) {
        this.ticketCategoryList = new ArrayList<>(ticketCategoryList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskTicketCategoryViewMVC viewMvc = viewMVCFactory.getTaskTicketCategoryViewMVC(parent);
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