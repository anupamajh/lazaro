package com.carmel.guestjini.Screens.Support.TicketDetail;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.TicketDetail.TicketComments.TicketCommentsViewMVC;

import java.util.ArrayList;
import java.util.List;

public class TicketCommentsRecyclerAdapter
        extends RecyclerView.Adapter<TicketCommentsRecyclerAdapter.CommentHolder>
        implements TicketCommentsViewMVC.Listener {

    public interface Listener {
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
        private final TicketCommentsViewMVC viewMVC;

        public CommentHolder(@NonNull TicketCommentsViewMVC viewMVC) {
            super(viewMVC.getRootView());
            this.viewMVC = viewMVC;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<TaskNote> taskNotes = new ArrayList<>();

    public TicketCommentsRecyclerAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindTaskNotes(List<TaskNote> taskNoteList) {
        this.taskNotes = new ArrayList<>(taskNoteList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TicketCommentsViewMVC viewMVC = viewMVCFactory.getTicketCommentsViewMVC(parent);
        viewMVC.registerListener(this);
        return new CommentHolder(viewMVC);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.viewMVC.bindTaskNote(this.taskNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return this.taskNotes.size();
    }
}
