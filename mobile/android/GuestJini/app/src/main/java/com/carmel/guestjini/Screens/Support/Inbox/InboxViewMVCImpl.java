package com.carmel.guestjini.Screens.Support.Inbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Tickets.TaskCountResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.card.MaterialCardView;

import java.util.Set;

public class InboxViewMVCImpl
        extends BaseObservableViewMvc<InboxViewMVC.Listener>
        implements InboxViewMVC {

    private final ProgressBar progressBar;
    private final RelativeLayout layoutLucky;
    private final MaterialCardView cardSharedInbox;
    private final MaterialCardView cardYourInbox;
    private final MaterialCardView cardTeamInbox;

    private final TextView txtSharedNewCount;
    private final RelativeLayout layoutSharedUnassigned;
    private final TextView txtSharedInboxUnassignedCount;
    private final RelativeLayout layoutSharedOpen;
    private final TextView txtSharedInboxOpenCount;
    private final RelativeLayout layoutSharedClosed;
    private final TextView txtSharedInboxClosedCount;
    private final TextView txtYourNewCount;
    private final RelativeLayout layoutYourOpen;
    private final TextView txtYourInboxOpenCount;
    private final RelativeLayout layoutYourClosed;
    private final TextView txtYourInboxClosedCount;
    private final TextView txtTeamNewCount;
    private final RelativeLayout layoutTeamUnassigned;
    private final TextView txtTeamInboxUnassignedCount;
    private final RelativeLayout layoutTeamOpen;
    private final TextView txtTeamInboxOpenCount;
    private final RelativeLayout layoutTeamClosed;
    private final TextView txtTeamInboxClosedCount;

    public InboxViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_inbox, parent, false));
        progressBar = findViewById(R.id.progress);
        layoutLucky = findViewById(R.id.layoutLucky);
        cardSharedInbox = findViewById(R.id.cardSharedInbox);
        cardYourInbox = findViewById(R.id.cardYourInbox);
        cardTeamInbox = findViewById(R.id.cardTeamInbox);
        txtSharedNewCount = findViewById(R.id.txtSharedNewCount);
        layoutSharedUnassigned = findViewById(R.id.layoutSharedUnassigned);
        txtSharedInboxUnassignedCount = findViewById(R.id.txtSharedInboxUnassignedCount);
        layoutSharedOpen = findViewById(R.id.layoutSharedOpen);
        txtSharedInboxOpenCount = findViewById(R.id.txtSharedInboxOpenCount);
        layoutSharedClosed = findViewById(R.id.layoutSharedClosed);
        txtSharedInboxClosedCount = findViewById(R.id.txtSharedInboxClosedCount);
        txtYourNewCount = findViewById(R.id.txtYourNewCount);
        layoutYourOpen = findViewById(R.id.layoutYourOpen);
        txtYourInboxOpenCount = findViewById(R.id.txtYourInboxOpenCount);
        layoutYourClosed = findViewById(R.id.layoutYourClosed);
        txtYourInboxClosedCount = findViewById(R.id.txtYourInboxClosedCount);
        txtTeamNewCount = findViewById(R.id.txtTeamNewCount);
        layoutTeamUnassigned = findViewById(R.id.layoutTeamUnassigned);
        txtTeamInboxUnassignedCount = findViewById(R.id.txtTeamInboxUnassignedCount);
        layoutTeamOpen = findViewById(R.id.layoutTeamOpen);
        txtTeamInboxOpenCount = findViewById(R.id.txtTeamInboxOpenCount);
        layoutTeamClosed = findViewById(R.id.layoutTeamClosed);
        txtTeamInboxClosedCount = findViewById(R.id.txtTeamInboxClosedCount);

        cardSharedInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onSharedInboxClicked();
                }
            }
        });

        cardYourInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onYourInboxClicked();
                }
            }
        });

        cardTeamInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onTeamInboxClicked();
                }
            }
        });

        layoutSharedUnassigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onSharedUnassignedClicked();
                }
            }
        });

        layoutSharedOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onSharedOpenClicked();
                }
            }
        });

        layoutSharedClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onSharedClosedClicked();
                }
            }
        });


        layoutYourOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onYourOpenClicked();
                }
            }
        });

        layoutYourClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onYourUnassignedClicked();
                }
            }
        });


        layoutTeamUnassigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onSharedUnassignedClicked();
                }
            }
        });

        layoutTeamOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onSharedUnassignedClicked();
                }
            }
        });

        layoutTeamClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener : getListeners()) {
                    listener.onSharedUnassignedClicked();
                }
            }
        });
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
    public void bindRoles(Set<String> grants) {

    }

    @Override
    public void bindData(TaskCountResponse taskCountResponse) {
        if (taskCountResponse.getSharedInboxUnassignedCount()
                + taskCountResponse.getSharedInboxOpenCount()
                + taskCountResponse.getYourInboxOpenCount()
                + taskCountResponse.getTeamInboxUnassignedCount()
                + taskCountResponse.getTeamInboxOpenCount()
                == 0
        ) {
            layoutLucky.setVisibility(View.GONE);
        } else {
            layoutLucky.setVisibility(View.VISIBLE);
        }

        if (taskCountResponse.getSharedInboxUnassignedCount() > 0) {
            layoutSharedUnassigned.setVisibility(View.VISIBLE);
            txtSharedInboxUnassignedCount.setText(
                    String.valueOf(taskCountResponse.getSharedInboxUnassignedCount())
            );
        }else{
            layoutSharedUnassigned.setVisibility(View.GONE);
        }

        if (taskCountResponse.getSharedInboxOpenCount() > 0) {
            layoutSharedOpen.setVisibility(View.VISIBLE);
            txtSharedInboxOpenCount.setText(
                    String.valueOf(taskCountResponse.getSharedInboxOpenCount())
            );
        }else{
            layoutSharedOpen.setVisibility(View.GONE);
        }

        if (taskCountResponse.getSharedInboxClosedCount() > 0) {
            layoutSharedClosed.setVisibility(View.VISIBLE);
            txtSharedInboxClosedCount.setText(
                    String.valueOf(taskCountResponse.getSharedInboxClosedCount())
            );
        }else{
            layoutSharedClosed.setVisibility(View.GONE);
        }


        if (taskCountResponse.getYourInboxOpenCount() > 0) {
            layoutYourOpen.setVisibility(View.VISIBLE);
            txtYourInboxOpenCount.setText(
                    String.valueOf(taskCountResponse.getYourInboxOpenCount())
            );
        }else{
            layoutYourOpen.setVisibility(View.GONE);
        }

        if (taskCountResponse.getYourInboxClosedCount() > 0) {
            layoutYourClosed.setVisibility(View.VISIBLE);
            txtYourInboxClosedCount.setText(
                    String.valueOf(taskCountResponse.getYourInboxClosedCount())
            );
        }else{
            layoutYourClosed.setVisibility(View.GONE);
        }

        if (taskCountResponse.getTeamInboxUnassignedCount() > 0) {
            layoutTeamUnassigned.setVisibility(View.VISIBLE);
            txtTeamInboxUnassignedCount.setText(
                    String.valueOf(taskCountResponse.getTeamInboxUnassignedCount())
            );

        }else{
            layoutTeamUnassigned.setVisibility(View.GONE);
        }

        if (taskCountResponse.getTeamInboxOpenCount() > 0) {
            layoutTeamOpen.setVisibility(View.VISIBLE);
            txtTeamInboxOpenCount.setText(
                    String.valueOf(taskCountResponse.getTeamInboxOpenCount())
            );
        }else{
            layoutTeamOpen.setVisibility(View.GONE);
        }

        if (taskCountResponse.getTeamInboxClosedCount() > 0) {
            layoutTeamClosed.setVisibility(View.VISIBLE);
            txtTeamInboxClosedCount.setText(
                    String.valueOf(taskCountResponse.getTeamInboxClosedCount())
            );
        }else{
            layoutTeamClosed.setVisibility(View.GONE);
        }

    }
}
