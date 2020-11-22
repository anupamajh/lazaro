package com.carmel.guestjini.Screens.Support.InboxList.TicketListItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class InboxListItemViewMVCImpl
        extends BaseObservableViewMvc<InboxListItemViewMVC.Listener>
        implements InboxListItemViewMVC {

    private TextView txtTicketStatus;
    private TextView txtTicketDate;
    private TextView txtTicketTitle;
    private TextView txtTicketNumber;
    private TextView txtTicketNarration;

    private Ticket ticket;

    public InboxListItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_inbox_ticket_list_item, parent, false));
        txtTicketStatus = findViewById(R.id.txtTicketStatus);
        txtTicketDate = findViewById(R.id.txtTicketDate);
        txtTicketTitle = findViewById(R.id.txtTicketTitle);
        txtTicketNumber = findViewById(R.id.txtTicketNumber);
        txtTicketNarration = findViewById(R.id.txtTicketNarration);
        getRootView().setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onTicketClicked(ticket);
            }
        });
    }

    @Override
    public void bindTicket(Ticket ticket) {
        try {
            this.ticket = ticket;
            String strTicketStatus = "OPEN";
            int drawableResourceId = 0;
            int colorResourceId = 0;
            switch (ticket.getTicketStatus()) {
                case 0: {
                    strTicketStatus = "DRAFT";
                    drawableResourceId = R.drawable.rectangle_border_draft_ticket;
                    colorResourceId = R.color.draftTicketText;
                }
                break;
                case 4:
                case 3:
                case 2:
                case 1: {
                    strTicketStatus = "OPEN";
                    drawableResourceId = R.drawable.rectangle_border_open_ticket;
                    colorResourceId = R.color.openTicketText;
                }
                break;
                case 5: {
                    strTicketStatus = "CLOSED";
                    drawableResourceId = R.drawable.rectangle_border_closed_ticket;
                    colorResourceId = R.color.closedTicketText;
                }
                break;
                default: {
                    strTicketStatus = "NEW";
                    drawableResourceId = R.drawable.rectangle_border_open_ticket;
                    colorResourceId = R.color.openTicketText;
                }
                break;

            }
            txtTicketStatus.setBackground(getContext().getDrawable(drawableResourceId));
            txtTicketStatus.setTextColor(getContext().getResources().getColor(colorResourceId));
            if(ticket.getCreationTime() == null || ticket.getCreationTime().isEmpty()){
                ticket.setCreationTime(ticket.getLastModifiedTime());
            }
            Date creationDate = DateUtil.convertToDate(ticket.getCreationTime());
            LocalDate localCreationDate = Instant.ofEpochMilli(creationDate.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            Date today = new Date();
            LocalDate localeToday = Instant.ofEpochMilli(today.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            txtTicketStatus.setText(strTicketStatus);
            txtTicketDate.setText(DateUtil.getFormattedDate(creationDate));
            txtTicketTitle.setText(ticket.getTicketTitle());
            txtTicketNumber.setText(ticket.getTicketNo());
            if(ticket.getTicketNarration() == null){
                ticket.setTicketNarration("");
            }

            if(!ticket.getTicketNarration().equals("")){
                txtTicketNarration.setVisibility(View.VISIBLE);
                txtTicketNarration.setText(ticket.getTicketNarration());
            }else{
                txtTicketNarration.setVisibility(View.GONE);
            }
        }catch (Exception ex){

        }

    }
}
