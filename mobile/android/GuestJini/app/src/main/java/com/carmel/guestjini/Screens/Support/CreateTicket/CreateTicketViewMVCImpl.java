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

import com.carmel.guestjini.Networking.Tickets.TicketCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;

public class CreateTicketViewMVCImpl
        extends BaseObservableViewMvc<CreateTicketViewMVC.Listener>
        implements CreateTicketViewMVC {

    private final ProgressBar progressBar;
    private final EditText txtNarration;
    private final TextView txtTicketCategoryTitle;
    private final TextView txtNarrationError;
    private final TextView txtMessageTitle;
    private final TextView txtSubTicketCategoryTitle;
    private final RelativeLayout layoutTicketCategory;
    private final ImageView imgCategorySeparator;

    private TicketCategory ticketCategory;

    private boolean isDraftEnabled = false;

    public CreateTicketViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_create, parent, false));
        txtNarration = findViewById(R.id.txtNarration);
        progressBar = findViewById(R.id.progress);
        txtTicketCategoryTitle = findViewById(R.id.txtTicketCategoryTitle);
        txtNarrationError = findViewById(R.id.txtNarrationError);
        txtMessageTitle = findViewById(R.id.txtMessageTitle);
        txtSubTicketCategoryTitle = findViewById(R.id.txtSubTicketCategoryTitle);
        layoutTicketCategory = findViewById(R.id.layoutTicketCategory);
        imgCategorySeparator = findViewById(R.id.imgCategorySeparator);
        MaterialButton btnSubmit = findViewById(R.id.btnSubmit);
        MaterialButton btnDraft = findViewById(R.id.btnSaveDraft);
        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnBackToCategory = findViewById(R.id.btnBackToCategory);
        txtNarration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if(!isDraftEnabled) {
                        btnDraft.setEnabled(true);
                        isDraftEnabled = true;
                        btnDraft.setTextColor(getRootView().getResources().getColor(R.color.colorNewMidnightBlue));
                    }
                }else{
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
            if(txtNarration.getText().toString().trim().length() == 0){
                txtNarrationError.setVisibility(View.VISIBLE);
            }else {
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
            if(txtNarration.getText().toString().trim().length() == 0){
                txtNarrationError.setVisibility(View.VISIBLE);
            }else {
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

        btnBackToCategory.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackToCategoryClicked();
            }
        });

    }

    @Override
    public void bindTicketCategoryData(TicketCategory parentTicketCategory) {
        this.ticketCategory = parentTicketCategory;

        txtTicketCategoryTitle.setText("");
        txtSubTicketCategoryTitle.setText("");
        if (parentTicketCategory != null) {
            txtTicketCategoryTitle.setText(parentTicketCategory.getCategoryDescription());
            if (parentTicketCategory.getChild() != null) {
                txtSubTicketCategoryTitle.setText(parentTicketCategory.getChild().getCategoryDescription());
            } else {
                imgCategorySeparator.setVisibility(View.GONE);
            }
        } else {
            layoutTicketCategory.setVisibility(View.GONE);
        }
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
        Toast.makeText(getContext(),"Ticked saved as draft", Toast.LENGTH_LONG).show();
    }
}
