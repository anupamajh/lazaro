package com.carmel.guestjini.Screens.Support.TicketDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;

import java.util.Date;
import java.util.List;

public class TicketDetailsViewMVCImpl
        extends BaseObservableViewMvc<TicketDetailsViewMVC.Listener>
        implements TicketDetailsViewMVC,
        TicketCommentsRecyclerAdapter.Listener {

    private final ImageView btnBack;
    private final TextView txtTicketStatus;
    private final TextView txtTicketDate;
    private final TextView txtTicketNumber;
    private final TextView txtTicketTitle;
    private final TextView txtTicketNarration;
    private final EditText txtTicketComment;
    private final MaterialButton btnSubmitComment;
    private final ProgressBar progressBar;

    private final TicketCommentsRecyclerAdapter ticketCommentsRecyclerAdapter;
    private Ticket ticket;

    public TicketDetailsViewMVCImpl(LayoutInflater inflater,
                                    @Nullable ViewGroup parent,
                                    ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_detail, parent, false));
        RecyclerView taskNoteRecyclerView = findViewById(R.id.lstTaskNotes);
        btnBack = findViewById(R.id.btnBack);
        txtTicketStatus = findViewById(R.id.txtTicketStatus);
        txtTicketDate = findViewById(R.id.txtTicketDate);
        txtTicketNumber = findViewById(R.id.txtTicketNumber);
        txtTicketTitle = findViewById(R.id.txtTicketTitle);
        txtTicketNarration = findViewById(R.id.txtTicketNarration);
        txtTicketComment = findViewById(R.id.txtTicketComment);
        btnSubmitComment = findViewById(R.id.btnSubmitComment);
        progressBar = findViewById(R.id.progress);
        taskNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketCommentsRecyclerAdapter = new TicketCommentsRecyclerAdapter(this, viewMVCFactory);
        taskNoteRecyclerView.setAdapter(ticketCommentsRecyclerAdapter);

        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnSubmitComment.setOnClickListener(view -> {
            if (!txtTicketComment.getText().toString().trim().equals("")) {
                for (Listener listener : getListeners()) {
                    listener.onSubmitClicked(ticket.getId(),
                            txtTicketComment.getText().toString().trim());
                }
            }
        });

    }

    @Override
    public void bindTaskNotes(List<TaskNote> taskNotes) {
        ticketCommentsRecyclerAdapter.bindTaskNotes(taskNotes);
    }

    @Override
    public void bindTicket(Ticket ticket) {
        this.ticket = ticket;
        String strTicketStatus = "OPEN";
        switch (ticket.getTicketStatus()) {
            case 3: {
                strTicketStatus = "OPEN";
            }
            break;
            case 2: {
                strTicketStatus = "STARTED";
            }
            break;
            case 1: {
                strTicketStatus = "CLOSED";
            }
            break;
            default: {
                strTicketStatus = "NEW";
            }
            break;

        }
        txtTicketStatus.setText(strTicketStatus);
        Date creationDate = DateUtil.convertToDate(ticket.getCreationTime());
        txtTicketDate.setText(DateUtil.getFormattedDate(creationDate));
        txtTicketNumber.setText(ticket.getTicketNo());
        txtTicketTitle.setText(ticket.getTicketTitle());
        txtTicketNarration.setText(ticket.getTicketNarration());
    }

    @Override
    public void clearComment() {
        txtTicketComment.setText("");
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }


}
