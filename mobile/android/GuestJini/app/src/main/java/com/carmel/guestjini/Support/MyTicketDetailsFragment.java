package com.carmel.guestjini.Support;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.carmel.guestjini.Common.DateUtil;
import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Ticket.TaskNote;
import com.carmel.guestjini.Models.Ticket.TaskNotesResponse;
import com.carmel.guestjini.Models.Ticket.Ticket;
import com.carmel.guestjini.Models.Ticket.TicketResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Ticket.TaskNoteService;
import com.carmel.guestjini.Services.Ticket.TicketService;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Adapter.TicketNotesAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyTicketDetailsFragment extends Fragment {

    private ImageView backArrow, star1, star2, star3, feedbackEditIcon, chatIcon;
    private LinearLayout foreground;
    private String ticket_status;
    private RelativeLayout ratingLayout, rateYourExperienceLayout, reopenedLayout;
    private FloatingActionButton ratingEditIcon;
    private Button submitButton, reopenButton, btnSaveNotes;
    private EditText feedbackEditText, txtTaskNotes;
    private TextView feedbackText;
    private ConstraintLayout yourFeedbackLayout;
    TextView ticketName, ticketStatus, ticketDateAndTime, ticketValue, ticketDescription;

    RecyclerView recyclerView;

    private ArrayList<TaskNote> taskNotes = new ArrayList<>();

    private String ticketId;

    private TicketNotesAdapter ticketNotesAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_ticket_details, container, false);
        recyclerView = rootView.findViewById(R.id.taskTicketNotes);
        backArrow = rootView.findViewById(R.id.leftArrowMark);
        String ticket_name, ticket_dateAndTime, ticket_value;
        ticketStatus = rootView.findViewById(R.id.ticketsStatus);
        ticketName = rootView.findViewById(R.id.ticketsName);
        ticketDateAndTime = rootView.findViewById(R.id.tickestsDateAndTime);
        ticketValue = rootView.findViewById(R.id.ticketsValue);
        ticketDescription = rootView.findViewById(R.id.tickestsDescription);
        //foreground = rootView.findViewById(R.id.foreground);
        ratingLayout = rootView.findViewById(R.id.ratingLayout);
        star1 = rootView.findViewById(R.id.star1);
        star2 = rootView.findViewById(R.id.star2);
        star3 = rootView.findViewById(R.id.star3);
        //chatIcon = rootView.findViewById(R.id.chatIcon);
        rateYourExperienceLayout = rootView.findViewById(R.id.rateYourExperienceLayout);
        reopenedLayout = rootView.findViewById(R.id.reopenedLayout);
        yourFeedbackLayout = rootView.findViewById(R.id.yourFeedbackLayout);
        submitButton = rootView.findViewById(R.id.yourFeedbackSubmitButton);
        reopenButton = rootView.findViewById(R.id.reopenButton);
        feedbackEditText = rootView.findViewById(R.id.yourFeedbackEditText);
        feedbackText = rootView.findViewById(R.id.yourFeedbackText);
        feedbackEditIcon = rootView.findViewById(R.id.feedbackEditIcon);
        ratingEditIcon = rootView.findViewById(R.id.ratingEditicon);
        btnSaveNotes = rootView.findViewById(R.id.writeReviewSubmitButton);
        RelativeLayout attchFile = rootView.findViewById(R.id.materialCardViewLayout);
        ConstraintLayout closed = rootView.findViewById(R.id.closedLayout);
        txtTaskNotes = rootView.findViewById(R.id.youEditBox);

        FloatingActionButton attachFilesIcon = (FloatingActionButton) rootView.findViewById(R.id.attachFilesIcon);
        final ConstraintLayout attachFiles = rootView.findViewById(R.id.attachfilesLayout);

        btnSaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtTaskNotes.getText().toString().trim().equals("")) {
                    saveTicketNotes();
                }
            }
        });

        attachFilesIcon.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    attachFiles.setVisibility(View.VISIBLE);
                } else {
                    flag = true;
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
//        foreground.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                foreground.setVisibility(View.GONE);
//                chatIcon.setImageResource(R.drawable.chat_icon_dimgrey);
//            }
//        });
        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            ticketId = bundle.getString("ticketId");
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Feedback_Text = feedbackEditText.getText().toString();
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
                //chatIcon.setImageResource(R.drawable.chat_icon_dimgrey);
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
                MyTicketsRecyclerViewFragment myTicketsRecyclerViewFragmentt = new MyTicketsRecyclerViewFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, myTicketsRecyclerViewFragmentt);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ticketNotesAdapter = new TicketNotesAdapter(getContext(), taskNotes);
        ticketNotesAdapter.setHasStableIds(true);
        recyclerView.setAdapter(ticketNotesAdapter);
        this.getTicketById(ticketId);
        this.getTicketNotes(ticketId);
        return rootView;
    }

    private void getTicketById(String ticketId) {
        try {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            TicketService ticketService = retrofit.create(TicketService.class);
            Map<String, String> requestData = new HashMap<>();
            requestData.put("id", ticketId);
            Call<TicketResponse> ticketResponseCall = ticketService.get(requestData);
            ticketResponseCall.enqueue(new Callback<TicketResponse>() {
                @Override
                public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                    try {
                        TicketResponse ticketResponse = response.body();
                        if (ticketResponse.isSuccess()) {
                            Ticket ticket = ticketResponse.getTaskTicket();
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
                            ticketStatus.setText(strTicketStatus);
                            Date creationDate = DateUtil.convertToDate(ticket.getCreationTime());
                            ticketDateAndTime.setText(DateUtil.getFormattedDate(creationDate));
                            ticketValue.setText(ticket.getTicketNo());
                            ticketName.setText(ticket.getTicketTitle());
                            ticketDescription.setText(ticket.getTicketNarration());

                        } else {
                            //TODO: SHow appropriate alert
                        }
                    } catch (Exception ex) {
                        //TODO: SHow appropriate alert
                    }
                }

                @Override
                public void onFailure(Call<TicketResponse> call, Throwable t) {
                    //TODO: Show appropriate alert dialog
                }
            });

        } catch (Exception ex) {

        }
    }

    private void getTicketNotes(String ticketId) {
        try {
            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            TaskNoteService taskNoteService = retrofit.create(TaskNoteService.class);
            Map<String, String> requestData = new HashMap<>();
            requestData.put("ticketId", ticketId);
            Call<TaskNotesResponse> taskNotesResponseCall = taskNoteService.getAll(requestData);
             taskNotesResponseCall.enqueue(new Callback<TaskNotesResponse>() {
                @Override
                public void onResponse(Call<TaskNotesResponse> call, Response<TaskNotesResponse> response) {
                    try {
                        TaskNotesResponse taskNotesResponse = response.body();
                        if (taskNotesResponse.isSuccess()) {
                            taskNotes = new ArrayList<>();
                            taskNotes.addAll(taskNotesResponse.getTaskNoteList());
                            ticketNotesAdapter.updateData(taskNotes);
                        } else {
                            //TODO: Show appropriate alert message
                        }
                    } catch (Exception ex) {
                        //TODO: Show appropriate alert message
                    }
                }

                @Override
                public void onFailure(Call<TaskNotesResponse> call, Throwable t) {
                    //TODO: Show appropriate alert message
                }
            });

        } catch (Exception ex) {

        }
    }

    private void saveTicketNotes() {
        try {
            TaskNote taskNote = new TaskNote();
            taskNote.setTicketId(ticketId);
            taskNote.setNotes(txtTaskNotes.getText().toString().trim());

            AuthServiceHolder authServiceHolder = new AuthServiceHolder();
            SharedPreferences preferences = getContext().getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
            String accessToken = preferences.getString("access_token", "");
            OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(getActivity(), authServiceHolder)
                    .addHeader("Authorization", accessToken)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoints.END_POINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            AuthService authService = retrofit.create(AuthService.class);
            authServiceHolder.set(authService);

            TaskNoteService taskNoteService = retrofit.create(TaskNoteService.class);
            Call<TaskNotesResponse> taskNotesResponseCall = taskNoteService.save(taskNote);
            taskNotesResponseCall.enqueue(new Callback<TaskNotesResponse>() {
                @Override
                public void onResponse(Call<TaskNotesResponse> call, Response<TaskNotesResponse> response) {
                    try {
                        TaskNotesResponse taskNotesResponse = response.body();
                        if (taskNotesResponse.isSuccess()) {
                            //TODO: Show appropriate alert
                            getTicketNotes(ticketId);
                        } else {
                            //TODO: Show appropriate alert
                        }
                    } catch (Exception ex) {
                        //TODO: Show appropriate alert
                    }
                }

                @Override
                public void onFailure(Call<TaskNotesResponse> call, Throwable t) {
                    //TODO: Show appropriate alert
                }
            });


        } catch (Exception ex) {

        }
    }
}
