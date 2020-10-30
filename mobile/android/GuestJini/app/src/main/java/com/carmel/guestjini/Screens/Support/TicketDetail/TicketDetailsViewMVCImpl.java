package com.carmel.guestjini.Screens.Support.TicketDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;

import java.util.Date;
import java.util.List;

public class TicketDetailsViewMVCImpl
        extends BaseObservableViewMvc<TicketDetailsViewMVC.Listener>
        implements TicketDetailsViewMVC,
        TicketCommentsRecyclerAdapter.Listener{

    private final RelativeLayout layoutNewTicketIndicator;
    private final TextView txtTicketStatus;
    private final TextView txtTicketDate;
    private final TextView txtTicketTitle;
    private final TextView txtTicketNumber;
    private final RecyclerView lstTicketCategories;
    private final RecyclerView lstTaskNotes;
    private final ImageView ratingStar1;
    private final ImageView ratingStar2;
    private final ImageView ratingStar3;
    private final ImageView ratingStar4;
    private final ImageView ratingStar5;

    private final ImageView btnBack;
    private final EditText txtTicketComment;
    private final MaterialButton btnSubmitComment;
    private final ProgressBar progressBar;

    private final TicketCommentsRecyclerAdapter ticketCommentsRecyclerAdapter;
    private final TaskTicketCategoryRecycleAdapter taskTicketCategoryRecycleAdapter;
    private Ticket ticket;

    public TicketDetailsViewMVCImpl(LayoutInflater inflater,
                                    @Nullable ViewGroup parent,
                                    ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_detail, parent, false));

        layoutNewTicketIndicator = findViewById(R.id.layoutNewTicketIndicator);
        txtTicketStatus = findViewById(R.id.txtTicketStatus);
        txtTicketDate = findViewById(R.id.txtTicketDate);
        txtTicketTitle = findViewById(R.id.txtTicketTitle);
        txtTicketNumber = findViewById(R.id.txtTicketNumber);
        lstTicketCategories = findViewById(R.id.lstTicketCategories);
        lstTaskNotes = findViewById(R.id.lstTaskNotes);
        ratingStar1 = findViewById(R.id.ratingStar1);
        ratingStar2 = findViewById(R.id.ratingStar2);
        ratingStar3 = findViewById(R.id.ratingStar3);
        ratingStar4 = findViewById(R.id.ratingStar4);
        ratingStar5 = findViewById(R.id.ratingStar5);
        btnBack = findViewById(R.id.btnBack);
        txtTicketComment = findViewById(R.id.txtTicketComment);
        btnSubmitComment = findViewById(R.id.btnSubmitComment);
        progressBar = findViewById(R.id.progress);

        lstTaskNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketCommentsRecyclerAdapter = new TicketCommentsRecyclerAdapter(this, viewMVCFactory);
        lstTaskNotes.setAdapter(ticketCommentsRecyclerAdapter);

        lstTicketCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        taskTicketCategoryRecycleAdapter = new TaskTicketCategoryRecycleAdapter(viewMVCFactory);
        lstTicketCategories.setAdapter(taskTicketCategoryRecycleAdapter);


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
        ratingStar1.setOnClickListener(v -> {
            if (ratingStar1.getTag().toString().contains("white")) {
                ratingStar1.setImageResource((R.drawable.star_yellow_icon));
                ratingStar1.setTag("yellow");
            }else{
                ratingStar1.setImageResource((R.drawable.star_white_icon));
                ratingStar1.setTag("white");
            }
            ratingStar2.setImageResource((R.drawable.star_white_icon));
            ratingStar2.setTag("white");
            ratingStar3.setImageResource((R.drawable.star_white_icon));
            ratingStar3.setTag("white");
            ratingStar4.setImageResource((R.drawable.star_white_icon));
            ratingStar4.setTag("white");
            ratingStar5.setImageResource((R.drawable.star_white_icon));
            ratingStar5.setTag("white");
        });

        ratingStar2.setOnClickListener(v -> {
            if (ratingStar2.getTag().toString().contains("white")) {
                ratingStar2.setImageResource((R.drawable.star_yellow_icon));
                ratingStar2.setTag("yellow");
                ratingStar1.setImageResource((R.drawable.star_yellow_icon));
                ratingStar1.setTag("yellow");
            }else{
                ratingStar2.setImageResource((R.drawable.star_white_icon));
                ratingStar2.setTag("white");
            }
            ratingStar3.setImageResource((R.drawable.star_white_icon));
            ratingStar3.setTag("white");
            ratingStar4.setImageResource((R.drawable.star_white_icon));
            ratingStar4.setTag("white");
            ratingStar5.setImageResource((R.drawable.star_white_icon));
            ratingStar5.setTag("white");
        });

        ratingStar3.setOnClickListener(v -> {
            if (ratingStar3.getTag().toString().contains("white")) {
                ratingStar3.setImageResource((R.drawable.star_yellow_icon));
                ratingStar3.setTag("yellow");
                ratingStar2.setImageResource((R.drawable.star_yellow_icon));
                ratingStar2.setTag("yellow");
                ratingStar1.setImageResource((R.drawable.star_yellow_icon));
                ratingStar1.setTag("yellow");
            }else{
                ratingStar3.setImageResource((R.drawable.star_white_icon));
                ratingStar3.setTag("white");
            }
            ratingStar4.setImageResource((R.drawable.star_white_icon));
            ratingStar4.setTag("white");
            ratingStar5.setImageResource((R.drawable.star_white_icon));
            ratingStar5.setTag("white");
        });

        ratingStar4.setOnClickListener(v -> {
            if (ratingStar4.getTag().toString().contains("white")) {
                ratingStar4.setImageResource((R.drawable.star_yellow_icon));
                ratingStar4.setTag("yellow");
                ratingStar3.setImageResource((R.drawable.star_yellow_icon));
                ratingStar3.setTag("yellow");
                ratingStar2.setImageResource((R.drawable.star_yellow_icon));
                ratingStar2.setTag("yellow");
                ratingStar1.setImageResource((R.drawable.star_yellow_icon));
                ratingStar1.setTag("yellow");
            }else{
                ratingStar4.setImageResource((R.drawable.star_white_icon));
                ratingStar4.setTag("white");
            }
            ratingStar5.setImageResource((R.drawable.star_white_icon));
            ratingStar5.setTag("white");
        });

        ratingStar5.setOnClickListener(v -> {
            if (ratingStar4.getTag().toString().contains("white")) {
                ratingStar5.setImageResource((R.drawable.star_yellow_icon));
                ratingStar5.setTag("yellow");
                ratingStar4.setImageResource((R.drawable.star_yellow_icon));
                ratingStar4.setTag("yellow");
                ratingStar3.setImageResource((R.drawable.star_yellow_icon));
                ratingStar3.setTag("yellow");
                ratingStar2.setImageResource((R.drawable.star_yellow_icon));
                ratingStar2.setTag("yellow");
                ratingStar1.setImageResource((R.drawable.star_yellow_icon));
                ratingStar1.setTag("yellow");
            }else{
                ratingStar5.setImageResource((R.drawable.star_white_icon));
                ratingStar5.setTag("white");
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
        int drawableResourceId = 0;
        int colorResourceId = 0;
        switch (ticket.getTicketStatus()) {
            case 0: {
                strTicketStatus = "DRAFT";
                drawableResourceId = R.drawable.rectangle_border_draft_ticket;
                colorResourceId = R.color.draftTicketText;
            }
            break;
            case 1: {
                strTicketStatus = "COMPLETED";
                drawableResourceId = R.drawable.rectangle_border_open_ticket;
                colorResourceId = R.color.openTicketText;
            }
            break;
            case 2: {
                strTicketStatus = "STARTED";
                drawableResourceId = R.drawable.rectangle_border_open_ticket;
                colorResourceId = R.color.openTicketText;
            }
            break;
            case 3: {
                strTicketStatus = "NOT STARTED";
                drawableResourceId = R.drawable.rectangle_border_open_ticket;
                colorResourceId = R.color.openTicketText;
            }
            break;
            case 4: {
                strTicketStatus = "ON HOLD";
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
        txtTicketStatus.setText(strTicketStatus);
        Date creationDate = DateUtil.convertToDate(ticket.getCreationTime());
        txtTicketDate.setText(DateUtil.getFormattedDate(creationDate));
        txtTicketNumber.setText(ticket.getTicketNo());
        txtTicketTitle.setText(ticket.getTicketTitle());
    }

    @Override
    public void bindTicketCategories(List<TicketCategory> ticketCategoryList) {
        taskTicketCategoryRecycleAdapter.bindTicketCategories(ticketCategoryList);
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
