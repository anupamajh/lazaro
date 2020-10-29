package com.carmel.guestjini.Screens.Support.TicketList.TicketListItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Common.AgeCalculator;
import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TicketListItemViewMVCImpl
        extends BaseObservableViewMvc<TicketListItemViewMVC.Listener>
        implements TicketListItemViewMVC {

    private TextView txtTicketStatus;
    private TextView txtTicketDate;
    private TextView txtTicketTitle;
    private TextView txtTicketNumber;

    private Ticket ticket;

    public TicketListItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_list_item, parent, false));

        txtTicketStatus = findViewById(R.id.txtTicketStatus);
        txtTicketDate = findViewById(R.id.txtTicketDate);
        txtTicketTitle = findViewById(R.id.txtTicketTitle);
        txtTicketNumber = findViewById(R.id.txtTicketNumber);
        getRootView().setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onTicketClicked(ticket);
            }
        });
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

    }
}
