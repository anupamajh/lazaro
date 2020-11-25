package com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketComments;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.Date;

public class InboxTicketCommentViewMVCImpl
        extends BaseObservableViewMvc<InboxTicketCommentViewMVC.Listener>
        implements InboxTicketCommentViewMVC {

    private final TextView txtAuthorName;
    private final TextView txtCommentDate;
    private final TextView txtComment;
    private final TextView txtItemTitle;

    public InboxTicketCommentViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_comment_item, parent, false));
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtCommentDate = findViewById(R.id.txtCommentDate);
        txtComment = findViewById(R.id.txtComment);
        txtItemTitle = findViewById(R.id.txtItemTitle);
    }

    @Override
    public void bindTaskNote(TaskNote taskNote) {
        txtAuthorName.setText(taskNote.getUserName());
        Date creationDate = DateUtil.convertToDate(taskNote.getCreationTime());
        txtCommentDate.setText(DateUtil.getFormattedDate(creationDate));
        txtComment.setText(taskNote.getNotes());
        if(taskNote.getIsMine() == 1){
            txtItemTitle.setText("YOU WROTE");
        }else{
            txtItemTitle.setText("INBOX");
        }
    }
}
