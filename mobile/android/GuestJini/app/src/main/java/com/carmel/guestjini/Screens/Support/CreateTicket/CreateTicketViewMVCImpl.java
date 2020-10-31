package com.carmel.guestjini.Screens.Support.CreateTicket;

import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.carmel.guestjini.Screens.Support.TicketDetail.TaskTicketCategoryRecycleAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CreateTicketViewMVCImpl
        extends BaseObservableViewMvc<CreateTicketViewMVC.Listener>
        implements CreateTicketViewMVC {

    private final ProgressBar progressBar;
    private final EditText txtNarration;
    private final TextView txtNarrationError;
    private final TextView txtMessageTitle;
    private final RelativeLayout layoutTicketCategory;
    private final RecyclerView lstTicketCategories;

    private final RelativeLayout deleteDraftLayout;
    private final ImageView btnDeleteDraft;


    private TicketCategory ticketCategory;

    private TicketCategory currentTicketCategory;
    private final TaskTicketCategoryRecycleAdapter taskTicketCategoryRecycleAdapter;

    private boolean isDraftEnabled = false;

    public CreateTicketViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_create, parent, false));
        txtNarration = findViewById(R.id.txtNarration);
        progressBar = findViewById(R.id.progress);
        deleteDraftLayout = findViewById(R.id.deleteDraftLayout);
        btnDeleteDraft = findViewById(R.id.btnDeleteDraft);
        txtNarrationError = findViewById(R.id.txtNarrationError);
        txtMessageTitle = findViewById(R.id.txtMessageTitle);
        layoutTicketCategory = findViewById(R.id.layoutTicketCategory);
        lstTicketCategories = findViewById(R.id.lstTicketCategories);
        MaterialButton btnSubmit = findViewById(R.id.btnSubmit);
        MaterialButton btnDraft = findViewById(R.id.btnSaveDraft);
        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnBackToCategory = findViewById(R.id.btnBackToCategory);

        lstTicketCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        taskTicketCategoryRecycleAdapter = new TaskTicketCategoryRecycleAdapter(viewMVCFactory);
        lstTicketCategories.setAdapter(taskTicketCategoryRecycleAdapter);


        txtNarration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (!isDraftEnabled) {
                        btnDraft.setEnabled(true);
                        isDraftEnabled = true;
                        btnDraft.setTextColor(getRootView().getResources().getColor(R.color.colorNewMidnightBlue));
                    }
                } else {
                    isDraftEnabled = false;
                    btnDraft.setEnabled(false);
                    btnDraft.setTextColor(getRootView().getResources().getColor(R.color.colorWhisper1));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSubmit.setOnClickListener(view -> {
            txtNarrationError.setVisibility(View.GONE);
            if (this.currentTicketCategory.getIsMessageMandatory() == 1) {
                if (txtNarration.getText().toString().trim().length() == 0) {
                    txtNarrationError.setVisibility(View.VISIBLE);
                } else {
                    for (Listener listener : getListeners()) {
                        listener.onCreateTicketClicked(
                                "",
                                txtNarration.getText().toString().trim()
                        );
                    }
                }
            } else {
                for (Listener listener : getListeners()) {
                    listener.onCreateTicketClicked(
                            "",
                            txtNarration.getText().toString().trim()
                    );
                }
            }
        });
        btnDraft.setOnClickListener(view -> {
            txtNarrationError.setVisibility(View.GONE);
            if (this.currentTicketCategory.getIsMessageMandatory() == 1) {
                if (txtNarration.getText().toString().trim().length() == 0) {
                    txtNarrationError.setVisibility(View.VISIBLE);
                } else {
                    for (Listener listener : getListeners()) {
                        listener.onSaveDraftClicked(
                                "",
                                txtNarration.getText().toString().trim()
                        );
                    }
                }
            } else {
                for (Listener listener : getListeners()) {
                    listener.onSaveDraftClicked(
                            "",
                            txtNarration.getText().toString().trim()
                    );
                }
            }
        });
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnDeleteDraft.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onDeleteDraftClicked();
            }
        });

        btnBackToCategory.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackToCategoryClicked();
            }
        });

    }

    @Override
    public void bindTicketCategoryData(List<TicketCategory> ticketCategories) {
        taskTicketCategoryRecycleAdapter.bindTicketCategories(ticketCategories);
        this.currentTicketCategory = ticketCategories.get(ticketCategories.size() - 1);
    }


    @Override
    public void bindTicket(Ticket taskTicket) {
        this.txtNarration.setText(taskTicket.getTicketNarration());
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
    public boolean isValid() {
        return true;
    }

    @Override
    public void showDraftSaved() {
        deleteDraftLayout.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Ticked saved as draft", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketSaved() {
        Toast.makeText(getContext(), "Ticked saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketDeleted() {
        Toast.makeText(getContext(), "Draft ticket deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketFetchFailed() {
        Toast.makeText(getContext(), "Error occurred while fetching the draft, Kindly try after sometime.", Toast.LENGTH_LONG).show();
    }
}
