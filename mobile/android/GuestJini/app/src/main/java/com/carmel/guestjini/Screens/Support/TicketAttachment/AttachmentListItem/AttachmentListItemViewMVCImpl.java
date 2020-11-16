package com.carmel.guestjini.Screens.Support.TicketAttachment.AttachmentListItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Tickets.TaskAttachment;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class AttachmentListItemViewMVCImpl
extends BaseObservableViewMvc<AttachmentListItemViewMVC.Listener>
implements AttachmentListItemViewMVC{

    private final TextView txtAttachmentName;
    private final ImageView btnDeleteAttachment;

    private TaskAttachment taskAttachment;

    public AttachmentListItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
            ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_attachment_list_item, parent, false));
        txtAttachmentName = findViewById(R.id.txtAttachmentName);
        btnDeleteAttachment = findViewById(R.id.btnDeleteAttachment);
        txtAttachmentName.setOnClickListener(v -> {
            for(Listener listener:getListeners()){
                listener.onAttachmentClicked(taskAttachment);
            }
        });

        btnDeleteAttachment.setOnClickListener(v -> {
            for(Listener listener:getListeners()){
                listener.onDeleteClicked(taskAttachment);
            }
        });
    }

    @Override
    public void bindAttachment(TaskAttachment taskAttachment) {
        this.taskAttachment = taskAttachment;
        this.txtAttachmentName.setText(taskAttachment.getName());
    }
}
