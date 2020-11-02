package com.carmel.guestjini.Screens.Support.TicketDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBack;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBackResponse;
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

    private final RelativeLayout layoutNewTicketIndicator;
    private final RelativeLayout layoutTicketNarration;
    private final TextView txtTicketStatus;
    private final TextView txtTicketDate;
    private final TextView txtTicketTitle;
    private final TextView txtTicketNumber;
    private final TextView txtTicketNarration;
    private final RecyclerView lstTicketCategories;
    private final RecyclerView lstTaskNotes;
    private final ImageView ratingStar1;
    private final ImageView ratingStar2;
    private final ImageView ratingStar3;
    private final ImageView ratingStar4;
    private final ImageView ratingStar5;
    private final CardView ticketMessageCard;
    private final CardView ticketRatingCard;

    private final ImageView btnBack;
    private final EditText txtTicketComment;
    private final MaterialButton btnSubmitComment;
    private final ProgressBar progressBar;

    private final EditText txtFeedBack;
    private final MaterialButton btnSubmitFeedback;


    private final TicketCommentsRecyclerAdapter ticketCommentsRecyclerAdapter;
    private final TaskTicketCategoryRecycleAdapter taskTicketCategoryRecycleAdapter;
    private Ticket ticket;
    private int rating = 0;

    public TicketDetailsViewMVCImpl(LayoutInflater inflater,
                                    @Nullable ViewGroup parent,
                                    ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_detail, parent, false));

        layoutNewTicketIndicator = findViewById(R.id.layoutNewTicketIndicator);
        layoutTicketNarration = findViewById(R.id.layoutTicketNarration);
        txtTicketStatus = findViewById(R.id.txtTicketStatus);
        txtTicketDate = findViewById(R.id.txtTicketDate);
        txtTicketTitle = findViewById(R.id.txtTicketTitle);
        txtTicketNumber = findViewById(R.id.txtTicketNumber);
        txtTicketNarration = findViewById(R.id.txtTicketNarration);
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
        txtFeedBack = findViewById(R.id.txtFeedBack);
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback);
        progressBar = findViewById(R.id.progress);
        ticketMessageCard = findViewById(R.id.ticketMessageCard);
        ticketRatingCard = findViewById(R.id.ticketRatingCard);
        lstTaskNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketCommentsRecyclerAdapter = new TicketCommentsRecyclerAdapter(this, viewMVCFactory);
        lstTaskNotes.setAdapter(ticketCommentsRecyclerAdapter);

        lstTicketCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        taskTicketCategoryRecycleAdapter = new TaskTicketCategoryRecycleAdapter(viewMVCFactory);
        lstTicketCategories.setAdapter(taskTicketCategoryRecycleAdapter);


        btnSubmitFeedback.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onSubmitFeedbackClicked(rating, txtFeedBack.getText().toString());
            }
        });

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
                rating = 1;
            } else {
                ratingStar1.setImageResource((R.drawable.star_white_icon));
                ratingStar1.setTag("white");
                rating = 0;
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
                rating = 2;
            } else {
                ratingStar2.setImageResource((R.drawable.star_white_icon));
                ratingStar2.setTag("white");
                rating = 1;
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
                rating = 3;
            } else {
                ratingStar3.setImageResource((R.drawable.star_white_icon));
                ratingStar3.setTag("white");
                rating = 2;
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
                rating = 4;
            } else {
                ratingStar4.setImageResource((R.drawable.star_white_icon));
                ratingStar4.setTag("white");
                rating = 3;
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
                rating = 5;
            } else {
                ratingStar5.setImageResource((R.drawable.star_white_icon));
                ratingStar5.setTag("white");
                rating = 4;
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
        if (this.ticket.getTicketStatus() == 5) {
            ticketMessageCard.setVisibility(View.GONE);
            ticketRatingCard.setVisibility(View.VISIBLE);
        } else {
            ticketMessageCard.setVisibility(View.VISIBLE);
            ticketRatingCard.setVisibility(View.GONE);
        }
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
        txtTicketStatus.setText(strTicketStatus);
        Date creationDate = DateUtil.convertToDate(ticket.getCreationTime());
        txtTicketDate.setText(DateUtil.getFormattedDate(creationDate));
        txtTicketNumber.setText(ticket.getTicketNo());
        txtTicketTitle.setText(ticket.getTicketTitle());
        if (ticket.getTicketNarration() == null) {
            ticket.setTicketNarration("");
        }
        if (!ticket.getTicketNarration().equals("")) {
            txtTicketNarration.setText(ticket.getTicketNarration());
            layoutTicketNarration.setVisibility(View.VISIBLE);
        } else {
            layoutTicketNarration.setVisibility(View.GONE);
        }
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

    @Override
    public void bindTicketFeedback(TicketFeedBackResponse ticketFeedBackResponse) {
        if (ticketFeedBackResponse.isSuccess()) {
            if (ticketFeedBackResponse.getTicketFeedBack() != null) {
                TicketFeedBack ticketFeedBack = ticketFeedBackResponse.getTicketFeedBack();
                txtFeedBack.setText(ticketFeedBack.getFeedback());
                switch (ticketFeedBack.getRating()) {
                    case 1: {
                        ratingStar1.setTag("white");
                        ratingStar1.callOnClick();
                    }
                    break;
                    case 2: {
                        ratingStar2.setTag("white");
                        ratingStar2.callOnClick();
                    }
                    break;
                    case 3: {
                        ratingStar3.setTag("white");
                        ratingStar3.callOnClick();
                    }
                    break;
                    case 4: {
                        ratingStar4.setTag("white");
                        ratingStar4.callOnClick();
                    }
                    break;
                    case 5: {
                        ratingStar5.setTag("white");
                        ratingStar5.callOnClick();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void showFeedbackSaved() {
        Toast.makeText(getContext(), "Your feedback has been saved.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFeedbackSaveFailed() {
        Toast.makeText(getContext(), "There was an error saving feedback, Kindly try after sometime.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTaskNotesSaved() {
        Toast.makeText(getContext(), "Your message has been send.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTaskNotesSaveFailed() {
        Toast.makeText(getContext(), "There was an error sending your message, Kindly try after sometime.", Toast.LENGTH_LONG).show();
    }
}
