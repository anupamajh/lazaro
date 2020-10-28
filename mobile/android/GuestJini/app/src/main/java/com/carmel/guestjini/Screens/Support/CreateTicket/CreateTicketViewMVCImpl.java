package com.carmel.guestjini.Screens.Support.CreateTicket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private final TextView txtSubTicketCategoryTitle;
    private final RelativeLayout layoutTicketCategory;
    private final ImageView imgCategorySeparator;

    public CreateTicketViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_create, parent, false));
        txtNarration = findViewById(R.id.txtNarration);
        progressBar = findViewById(R.id.progress);
        txtTicketCategoryTitle = findViewById(R.id.txtTicketCategoryTitle);
        txtSubTicketCategoryTitle = findViewById(R.id.txtSubTicketCategoryTitle);
        layoutTicketCategory = findViewById(R.id.layoutTicketCategory);
        imgCategorySeparator = findViewById(R.id.imgCategorySeparator);
        MaterialButton btnSubmit = findViewById(R.id.btnSubmit);
        MaterialButton btnDraft = findViewById(R.id.btnSaveDraft);
        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnBackToCategory = findViewById(R.id.btnBackToCategory);
        btnSubmit.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onCreateTicketClicked(
                        "",
                        txtNarration.getText().toString().trim()
                );
            }
        });
        btnDraft.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onSaveDraftClicked(
                        "",
                        txtNarration.getText().toString().trim()
                );
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
        txtTicketCategoryTitle.setText("");
        txtSubTicketCategoryTitle.setText("");
        if (parentTicketCategory != null) {
            txtTicketCategoryTitle.setText(parentTicketCategory.getCategoryDescription());
            if (parentTicketCategory.getChild() != null) {
                txtSubTicketCategoryTitle.setText(parentTicketCategory.getChild().getCategoryDescription());

            } else {
                imgCategorySeparator.setVisibility(View.GONE);
            }
        }else {
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
}
