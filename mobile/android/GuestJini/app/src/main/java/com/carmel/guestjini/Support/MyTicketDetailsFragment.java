package com.carmel.guestjini.Support;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carmel.guestjini.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyTicketDetailsFragment extends Fragment {

    private ImageView backArrow,star1,star2,star3,feedbackEditIcon,chatIcon;
    private LinearLayout foreground;
    private String ticket_status;
    private RelativeLayout ratingLayout,rateYourExperienceLayout,reopenedLayout;
    private FloatingActionButton ratingEditIcon;
    private Button submitButton,reopenButton;
    private EditText feedbackEditText;
    private TextView feedbackText;
    private ConstraintLayout yourFeedbackLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView ticketName,ticketStatus,ticketDateAndTime,ticketValue;
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_my_ticket_details, container, false);
        backArrow=rootView.findViewById(R.id.leftArrowMark);
        String ticket_name,ticket_dateAndTime,ticket_value;
        ticketStatus=rootView.findViewById(R.id.ticketsStatus);
        ticketName =rootView.findViewById(R.id.ticketsName);
        ticketDateAndTime=rootView.findViewById(R.id.tickestsDateAndTime);
        ticketValue=rootView.findViewById(R.id.ticketsValue);
        foreground=rootView.findViewById(R.id.foreground);
        ratingLayout=rootView.findViewById(R.id.ratingLayout);
        star1=rootView.findViewById(R.id.star1);
        star2=rootView.findViewById(R.id.star2);
        star3=rootView.findViewById(R.id.star3);
        chatIcon=rootView.findViewById(R.id.chatIcon);
        rateYourExperienceLayout=rootView.findViewById(R.id.rateYourExperienceLayout);
        reopenedLayout=rootView.findViewById(R.id.reopenedLayout);
        yourFeedbackLayout=rootView.findViewById(R.id.yourFeedbackLayout);
        submitButton=rootView.findViewById(R.id.yourFeedbackSubmitButton);
        reopenButton=rootView.findViewById(R.id.reopenButton);
        feedbackEditText=rootView.findViewById(R.id.yourFeedbackEditText);
        feedbackText=rootView.findViewById(R.id.yourFeedbackText);
        feedbackEditIcon=rootView.findViewById(R.id.feedbackEditIcon);
        ratingEditIcon=rootView.findViewById(R.id.ratingEditicon);
        RelativeLayout attchFile=rootView.findViewById(R.id.materialCardViewLayout);
        ConstraintLayout closed=rootView.findViewById(R.id.closedLayout);

        FloatingActionButton attachFilesIcon=(FloatingActionButton)rootView.findViewById(R.id.attachFilesIcon);
        final ConstraintLayout attachFiles=rootView.findViewById(R.id.attachfilesLayout);

        attachFilesIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag) {
                    flag=false;
                    attachFiles.setVisibility(View.VISIBLE);
                }else {
                    flag=true;
                    attachFiles.setVisibility(View.GONE);
                }
            }
        });
        ratingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingLayout.setBackgroundResource(0);
                ratingEditIcon.setVisibility(View.VISIBLE);
                star1.setImageResource(R.drawable.rating_xxhdpi);
                star2.setImageResource(R.drawable.rating_xxhdpi);
                star3.setImageResource(R.drawable.rating_xxhdpi);
            }
        });

        ratingEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingEditIcon.setVisibility(View.GONE);
                ratingLayout.setBackgroundResource(R.drawable.rate_your_experience);
                star1.setImageResource(R.drawable.unrating_xxhdpi);
                star2.setImageResource(R.drawable.unrating_xxhdpi);
                star3.setImageResource(R.drawable.unrating_xxhdpi);
            }
        });
        foreground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foreground.setVisibility(View.GONE);
                chatIcon.setImageResource(R.drawable.chat_icon_dimgrey);
            }
        });
        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            ticket_status  = bundle.getString("ticket_status");
            ticket_name  = bundle.getString("ticket_name");
            ticket_dateAndTime  = bundle.getString("ticket_dateAndTime");
            ticket_value  = bundle.getString("ticket_value");

            ticketName.setText(ticket_name);
            ticketStatus.setText(ticket_status);
            ticketDateAndTime.setText(ticket_dateAndTime);
            ticketValue.setText(ticket_value);
        }
        if(ticket_status.equals("OPEN")){
            attchFile.setVisibility(View.VISIBLE);
            closed.setVisibility(View.GONE);
        }
        else {
            closed.setVisibility(View.VISIBLE);
            attchFile.setVisibility(View.GONE);
            foreground.setVisibility(View.GONE);
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Feedback_Text=feedbackEditText.getText().toString();
                feedbackText.setText(Feedback_Text);
                feedbackText.setVisibility(View.VISIBLE);
                feedbackEditIcon.setVisibility(View.VISIBLE);
                feedbackEditText.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                yourFeedbackLayout.setBackgroundResource(0);
            }
        });

        reopenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateYourExperienceLayout.setVisibility(View.GONE);
                reopenedLayout.setVisibility(View.VISIBLE);
                chatIcon.setImageResource(R.drawable.chat_icon_dimgrey);
            }
        });
        feedbackEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackText.setVisibility(View.GONE);
                feedbackEditIcon.setVisibility(View.GONE);
                feedbackEditText.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                yourFeedbackLayout.setBackgroundResource(R.drawable.rate_your_experience);
            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTicketsRecyclerViewFragment myTicketsRecyclerViewFragmentt=new MyTicketsRecyclerViewFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder,myTicketsRecyclerViewFragmentt);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }

}
