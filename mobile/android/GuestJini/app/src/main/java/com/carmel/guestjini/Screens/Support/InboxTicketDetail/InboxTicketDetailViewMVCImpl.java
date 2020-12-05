package com.carmel.guestjini.Screens.Support.InboxTicketDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.AgeCalculator;
import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Networking.Tickets.TaskAssigneeResponse;
import com.carmel.guestjini.Networking.Tickets.TaskForceGroup;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBack;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBackResponse;
import com.carmel.guestjini.Networking.Users.User;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class InboxTicketDetailViewMVCImpl
extends BaseObservableViewMvc<InboxTicketDetailViewMVC.Listener>
    implements InboxTicketDetailViewMVC,
        InboxTicketCommentsRecyclerAdapter.Listener
{
    private final ProgressBar progressBar;
    private final ImageView btnBack;
    private final TextView txtStatus;
    private final TextView txtTicketDate;
    private final TextView txtCreatorName;
    private final TextView txtGuestPod;
    private final TextView txtTicketNumber;
    private final RecyclerView lstTicketCategories;

    private final RelativeLayout layoutAssignTicketDetails;
    private final ImageView btnAssignTicket;

    private final RelativeLayout layoutAssignedGroupDetails;
    private final TextView txtAssignedGroupName;
    private final TextView txtAssignedGroupAssignmentDetails;
    private final TextView txtAssignedGroupAssignedSince;
    private final Button btnWithdrawFromGroup;
    private final TextView txtToAgentWithdrawTitle;
    private final TextView txtWithdrawTitle;

    private final RelativeLayout layoutAssignTicketDetailsToAgent;
    private final ImageView btnAssignTicketToAgent;


    private final RelativeLayout layoutAssignedAgentDetails;
    private final TextView txtAssignedToAgentName;
    private final TextView txtAssignedToAgentAssignmentDetails;
    private final TextView txtAssignedToAgentAssignedSince;
    private final Button btnWithdrawFromAgent;

    private final RelativeLayout layoutCloseTicketDetails;
    private final ImageView btnCloseTicket;

    private final RelativeLayout layoutCustomerFeedback;
    private final TextView txtCustomerFeedbackDate;
    private final ImageView ratingStar1;
    private final ImageView ratingStar2;
    private final ImageView ratingStar3;
    private final ImageView ratingStar4;
    private final ImageView ratingStar5;
    private final TextView txtCustomerFeedback;

    private final RelativeLayout layoutTicketDetails;

    private final RelativeLayout layoutCommentList;
    private final RecyclerView lstTaskNotes;

    private final InboxTicketCommentsRecyclerAdapter inboxTicketCommentsRecyclerAdapter;
    private final InboxTaskTicketCategoryRecycleAdapter inboxTaskTicketCategoryRecycleAdapter;

    private Ticket ticket;
    private int rating = 0;
    private int inboxType = 0;

    public InboxTicketDetailViewMVCImpl(LayoutInflater inflater,
                                    @Nullable ViewGroup parent,
                                    ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_inbox_ticket_detail, parent, false));
        progressBar = findViewById(R.id.progress);
        btnBack = findViewById(R.id.btnBack);
        txtToAgentWithdrawTitle = findViewById(R.id.txtToAgentWithdrawTitle);
        txtWithdrawTitle = findViewById(R.id.txtWithdrawTitle);
        txtStatus = findViewById(R.id.txtStatus);
        txtTicketDate = findViewById(R.id.txtTicketDate);
        txtCreatorName = findViewById(R.id.txtCreatorName);
        txtGuestPod = findViewById(R.id.txtGuestPod);
        txtTicketNumber = findViewById(R.id.txtTicketNumber);
        lstTicketCategories = findViewById(R.id.lstTicketCategories);
        layoutAssignTicketDetails = findViewById(R.id.layoutAssignTicketDetails);
        btnAssignTicket = findViewById(R.id.btnAssignTicket);
        layoutAssignedGroupDetails = findViewById(R.id.layoutAssignedGroupDetails);
        txtAssignedGroupName = findViewById(R.id.txtAssignedGroupName);
        txtAssignedGroupAssignmentDetails = findViewById(R.id.txtAssignedGroupAssignmentDetails);
        txtAssignedGroupAssignedSince = findViewById(R.id.txtAssignedGroupAssignedSince);
        btnWithdrawFromGroup = findViewById(R.id.btnWithdrawFromGroup);
        layoutAssignTicketDetailsToAgent = findViewById(R.id.layoutAssignTicketDetailsToAgent);
        btnAssignTicketToAgent = findViewById(R.id.btnAssignTicketToAgent);
        layoutAssignedAgentDetails = findViewById(R.id.layoutAssignedAgentDetails);
        txtAssignedToAgentName = findViewById(R.id.txtAssignedToAgentName);
        txtAssignedToAgentAssignmentDetails = findViewById(R.id.txtAssignedToAgentAssignmentDetails);
        txtAssignedToAgentAssignedSince = findViewById(R.id.txtAssignedToAgentAssignedSince);
        btnWithdrawFromAgent = findViewById(R.id.btnWithdrawFromAgent);
        layoutCloseTicketDetails = findViewById(R.id.layoutCloseTicketDetails);
        btnCloseTicket = findViewById(R.id.btnCloseTicket);
        layoutCustomerFeedback = findViewById(R.id.layoutCustomerFeedback);
        txtCustomerFeedbackDate = findViewById(R.id.txtCustomerFeedbackDate);
        ratingStar1 = findViewById(R.id.ratingStar1);
        ratingStar2 = findViewById(R.id.ratingStar2);
        ratingStar3 = findViewById(R.id.ratingStar3);
        ratingStar4 = findViewById(R.id.ratingStar4);
        ratingStar5 = findViewById(R.id.ratingStar5);
        txtCustomerFeedback = findViewById(R.id.txtCustomerFeedback);
        layoutCommentList = findViewById(R.id.layoutCommentList);
        lstTaskNotes = findViewById(R.id.lstTaskNotes);

        layoutTicketDetails = findViewById(R.id.layoutTicketDetails);

        lstTaskNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        inboxTicketCommentsRecyclerAdapter = new InboxTicketCommentsRecyclerAdapter(this, viewMVCFactory);
        lstTaskNotes.setAdapter(inboxTicketCommentsRecyclerAdapter);

        lstTicketCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        inboxTaskTicketCategoryRecycleAdapter = new InboxTaskTicketCategoryRecycleAdapter(viewMVCFactory);
        lstTicketCategories.setAdapter(inboxTaskTicketCategoryRecycleAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onBackClicked();
                }
            }
        });

        btnCloseTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onCloseTicketClicked();
                }
            }
        });

        btnAssignTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onAssignTicketClicked();
                }
            }
        });

        btnAssignTicketToAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onAssignTicketToAgentClicked("", ticket.getId());
                }
            }
        });

        btnWithdrawFromAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onWithdrawFromAgentClicked();
                }
            }
        });

        btnWithdrawFromGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener:getListeners()){
                    listener.onWithdrawFromGroupClicked();
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

        layoutTicketDetails.setVisibility(View.GONE);
        layoutAssignTicketDetails.setVisibility(View.GONE);
        layoutAssignedGroupDetails.setVisibility(View.GONE);
        layoutAssignTicketDetailsToAgent.setVisibility(View.GONE);
        layoutAssignedAgentDetails.setVisibility(View.GONE);
        layoutCloseTicketDetails.setVisibility(View.GONE);
        layoutCustomerFeedback.setVisibility(View.GONE);
        //layoutCommentList.setVisibility(View.GONE);
    }

    @Override
    public void bindTicket(Ticket ticket) {
        this.ticket = ticket;
        layoutTicketDetails.setVisibility(View.VISIBLE);
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

        txtStatus.setBackground(getContext().getDrawable(drawableResourceId));
        txtStatus.setTextColor(getContext().getResources().getColor(colorResourceId));
        txtStatus.setText(strTicketStatus);
        Date creationDate = DateUtil.convertToDate(ticket.getCreationTime());
        txtTicketDate.setText(DateUtil.getFormattedDate(creationDate));
        txtTicketNumber.setText("Ticket #  " + ticket.getTicketNo());
        txtCreatorName.setText(ticket.getRequesterName());
        txtGuestPod.setText(ticket.getRequesterInventoryTitle());
        if(inboxType == 1 && ticket.getTicketStatus() != 5){
            if(ticket.getTaskForceGroupId() == null || ticket.getTaskForceGroupId().equals("")) {
                layoutAssignTicketDetails.setVisibility(View.VISIBLE);
            }else{
                layoutAssignTicketDetails.setVisibility(View.GONE);
            }
            if(ticket.getTaskRunnerId() == null || ticket.getTaskRunnerId().equals("")) {
                layoutAssignTicketDetailsToAgent.setVisibility(View.VISIBLE);
                layoutCloseTicketDetails.setVisibility(View.GONE);
            }else {
                layoutAssignTicketDetailsToAgent.setVisibility(View.GONE);
                layoutCloseTicketDetails.setVisibility(View.VISIBLE);
            }
        }else{
            layoutAssignTicketDetails.setVisibility(View.GONE);
            layoutAssignTicketDetailsToAgent.setVisibility(View.GONE);
        }

    }

    @Override
    public void bindTicketCategories(List<TicketCategory> taskTicketCategories) {
        inboxTaskTicketCategoryRecycleAdapter.bindTicketCategories(taskTicketCategories);
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
    public void bindTicketAssignmentDetails(TaskAssigneeResponse taskAssigneeResponse) {
        if(taskAssigneeResponse.getTaskForceGroup() != null){
            TaskForceGroup taskForceGroup = taskAssigneeResponse.getTaskForceGroup();
            txtAssignedGroupName.setText(taskForceGroup.getName());
            txtAssignedGroupAssignmentDetails.setText("Assigned on " + DateUtil.getFormattedDate(taskAssigneeResponse.getCreationTime()));
            Date today = new Date();
            LocalDate assignedDate = Instant.ofEpochMilli(taskAssigneeResponse.getCreationTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localeToday = Instant.ofEpochMilli(today.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            txtAssignedGroupAssignedSince.setText(
                    AgeCalculator.calculateDateLapse(
                            assignedDate, localeToday
                    ) + " days ago by " + taskAssigneeResponse.getCreatedBy().getFullName()
            );
            layoutAssignedGroupDetails.setVisibility(View.VISIBLE);
            if(ticket.getTicketStatus() == 5){
                btnWithdrawFromGroup.setVisibility(View.GONE);
                txtWithdrawTitle.setVisibility(View.GONE);
            }
        }

        if(taskAssigneeResponse.getUserDTO() != null){
            User user = taskAssigneeResponse.getUserDTO();
            txtAssignedToAgentName.setText(user.getFullName());
            txtAssignedToAgentAssignmentDetails.setText("Assigned on " + DateUtil.getFormattedDate(taskAssigneeResponse.getCreationTime()));
            Date today = new Date();
            LocalDate assignedDate = Instant.ofEpochMilli(taskAssigneeResponse.getCreationTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localeToday = Instant.ofEpochMilli(today.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            txtAssignedToAgentAssignedSince.setText(
                    AgeCalculator.calculateDateLapse(
                            assignedDate, localeToday
                    ) + " days ago by " + taskAssigneeResponse.getCreatedBy().getFullName()
            );
            layoutAssignedAgentDetails.setVisibility(View.VISIBLE);
            if(ticket.getTicketStatus() == 5){
                txtToAgentWithdrawTitle.setVisibility(View.GONE);
                btnWithdrawFromAgent.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void setupView(int inboxType) {
        this.inboxType = inboxType;
    }

    @Override
    public void showTicketWithdrawnFromAgent() {
        Toast.makeText(getContext(), "Ticket has been withdrawn from agent successfully.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketWithdrawFromAgentFailed() {
        Toast.makeText(getContext(), "There was an error withdrawing ticket.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketWithdrawnFromGroup() {
        Toast.makeText(getContext(), "Ticket has been withdrawn from group successfully.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketWithdrawFromGroupFailed() {
        Toast.makeText(getContext(), "There was an error withdrawing ticket.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void bindTaskNotes(List<TaskNote> taskNoteList) {
        inboxTicketCommentsRecyclerAdapter.bindTaskNotes(taskNoteList);
    }

    @Override
    public void bindTicketFeedback(TicketFeedBackResponse ticketFeedBackResponse) {
        if (ticketFeedBackResponse.isSuccess()) {
            if (ticketFeedBackResponse.getTicketFeedBack() != null) {
                layoutCustomerFeedback.setVisibility(View.VISIBLE);
                TicketFeedBack ticketFeedBack = ticketFeedBackResponse.getTicketFeedBack();
                txtCustomerFeedback.setText(ticketFeedBack.getFeedback());
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
}
