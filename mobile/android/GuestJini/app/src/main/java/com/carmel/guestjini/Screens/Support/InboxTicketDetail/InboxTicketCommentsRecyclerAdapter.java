package com.carmel.guestjini.Screens.Support.InboxTicketDetail;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketComments.InboxTicketCommentViewMVC;

import java.util.ArrayList;
import java.util.List;

public class InboxTicketCommentsRecyclerAdapter
        extends RecyclerView.Adapter<InboxTicketCommentsRecyclerAdapter.CommentHolder>
        implements InboxTicketCommentViewMVC.Listener {

    public interface Listener {
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
        private final InboxTicketCommentViewMVC viewMVC;

        public CommentHolder(@NonNull InboxTicketCommentViewMVC viewMVC) {
            super(viewMVC.getRootView());
            this.viewMVC = viewMVC;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<TaskNote> taskNotes = new ArrayList<>();

    public InboxTicketCommentsRecyclerAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
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
        InboxTicketCommentViewMVC viewMVC = viewMVCFactory.getInboxTicketCommentViewMVC(parent);
        viewMVC.registerListener(this);
        return new InboxTicketCommentsRecyclerAdapter.CommentHolder(viewMVC);

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
