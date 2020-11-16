package com.carmel.guestjini.Screens.Support.TicketAttachment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TaskAttachment;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.TicketAttachment.AttachmentListItem.AttachmentListItemViewMVC;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TicketAttachmentRecyclerAdapter
        extends RecyclerView.Adapter<TicketAttachmentRecyclerAdapter.TaskAttachmentHolder>
        implements AttachmentListItemViewMVC.Listener {

    public interface Listener {
        void onDeleteClicked(TaskAttachment taskAttachment);

        void onAttachmentClicked(TaskAttachment taskAttachment);
    }

    static class TaskAttachmentHolder extends RecyclerView.ViewHolder {
        private final AttachmentListItemViewMVC viewMVC;

        public TaskAttachmentHolder(@NonNull AttachmentListItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<TaskAttachment> taskAttachments = new ArrayList<>();

    public TicketAttachmentRecyclerAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindAttachments(List<TaskAttachment> taskAttachmentList) {
        this.taskAttachments = new ArrayList<>(taskAttachmentList);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public TaskAttachmentHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        AttachmentListItemViewMVC viewMVC = viewMVCFactory.getAttachmentListItemViewMVC(parent);
        viewMVC.registerListener(this);
        return new TaskAttachmentHolder(viewMVC);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskAttachmentHolder holder, int position) {
        holder.viewMVC.bindAttachment(this.taskAttachments.get(position));
    }

    @Override
    public int getItemCount() {
        return this.taskAttachments.size();
    }

    @Override
    public void onDeleteClicked(TaskAttachment taskAttachment) {
        listener.onDeleteClicked(taskAttachment);
    }

    @Override
    public void onAttachmentClicked(TaskAttachment taskAttachment) {
        listener.onAttachmentClicked(taskAttachment);
    }
}
