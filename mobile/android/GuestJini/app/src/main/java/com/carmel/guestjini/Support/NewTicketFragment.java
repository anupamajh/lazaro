package com.carmel.guestjini.Support;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Ticket.Ticket;
import com.carmel.guestjini.Models.Ticket.TicketRequest;
import com.carmel.guestjini.Models.Ticket.TicketResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.carmel.guestjini.Services.Ticket.TicketService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewTicketFragment extends Fragment {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    MaterialButton submitButton;
    EditText subjectEditText;
    EditText complaintEditText;
    TextView subjectErrorField;
    MaterialCardView attachmentsCardView;
    ImageView leftArrow;

    AlertDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_ticket, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        submitButton = rootView.findViewById(R.id.newTicketSubmitButton);
        subjectEditText = rootView.findViewById(R.id.subjectEditText);
        complaintEditText = rootView.findViewById(R.id.complaintEditText);
        subjectErrorField = rootView.findViewById(R.id.subjectErrorField);
        attachmentsCardView = rootView.findViewById(R.id.attchmentCardView);
        leftArrow = rootView.findViewById(R.id.leftArrowMark);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupportLandingFragment supportLandingFragment = new SupportLandingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, supportLandingFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subjectEditText.getText().toString().trim().length() == 0) {
                    subjectErrorField.setVisibility(View.VISIBLE);
                    subjectEditText.setBackgroundResource(R.drawable.subject_red_editbox);
                } else {
                    progressDialog.show();
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
                    Ticket ticket = new Ticket();
                    ticket.setTicketTitle(subjectEditText.getText().toString().trim());
                    ticket.setTicketNarration(complaintEditText.getText().toString().trim());
                    TicketRequest ticketRequest = new TicketRequest();
                    ticketRequest.setTaskTicket(ticket);
                    ticketRequest.setTaskAttachmentList(new ArrayList<>());
                    accessToken = preferences.getString("access_token", "");
                    Call<TicketResponse> ticketSaveCall =
                            ticketService.saveTicket(ticketRequest);
                    ticketSaveCall.enqueue(new Callback<TicketResponse>() {
                        @Override
                        public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful()) {
                                TicketResponse ticketResponse = response.body();
                                if (ticketResponse.isSuccess()) {
                                    showTicketSuccessDialog(true);
                                } else {
                                    showTicketSuccessDialog(false);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<TicketResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            showTicketSuccessDialog(false);
                        }
                    });

                }
            }
        });

        attachmentsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttachFilesFragment attachFilesFragment = new AttachFilesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, attachFilesFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }

    private void showTicketSuccessDialog(boolean isSuccess) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.alert_dailogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
        if (isSuccess) {
            alertDailogTitle.setText(getText(R.string.success));
        } else {
            alertDailogTitle.setText(getText(R.string.failed));
            alertDailogTitle.setTextColor(Color.parseColor("#E65959"));
        }
        TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
        if (isSuccess) {
            alertDailogMessage.setText(R.string.ticket_success);
        } else {
            alertDailogMessage.setText(R.string.ticket_failed);
        }

        FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
        if (isSuccess) {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#32BDD2")));
        } else {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#E65959")));
        }


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                MyTicketsRecyclerViewFragment myTicketsRecyclerViewFragment = new MyTicketsRecyclerViewFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.SuppotPlaceHolder, myTicketsRecyclerViewFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        dialog.show();
    }

}
