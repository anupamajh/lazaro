package com.carmel.guestjini.Screens.Support.TicketDetail.TicketComments;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class TicketCommentsViewMVCImpl
        extends BaseObservableViewMvc<TicketCommentsViewMVC.Listener>
        implements TicketCommentsViewMVC {

    private final TextView txtAuthorName;
    private final TextView txtCommentDate;
    private final TextView txtComment;

    public TicketCommentsViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_comment_item, parent, false));
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtCommentDate = findViewById(R.id.txtCommentDate);
        txtComment = findViewById(R.id.txtComment);
    }

    @Override
    public void bindTaskNote(TaskNote taskNote) {
        txtAuthorName.setText(taskNote.getUserName());
        txtCommentDate.setText(taskNote.getCreationTime());//TODO: Format the date Here
        txtComment.setText(taskNote.getNotes());
    }
}
