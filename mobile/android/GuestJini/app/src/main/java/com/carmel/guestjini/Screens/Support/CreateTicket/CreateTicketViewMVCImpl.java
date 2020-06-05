package com.carmel.guestjini.Screens.Support.CreateTicket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.button.MaterialButton;

public class CreateTicketViewMVCImpl
        extends BaseObservableViewMvc<CreateTicketViewMVC.Listener>
        implements CreateTicketViewMVC {

    private final ProgressBar progressBar;
    private final EditText txtSubject;
    private final EditText txtNarration;
    private final TextView txtSubjectError;

    public CreateTicketViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_create, parent, false));
        txtSubject = findViewById(R.id.txtSubject);
        txtNarration = findViewById(R.id.txtNarration);
        txtSubjectError = findViewById(R.id.txtSubjectError);
        progressBar = findViewById(R.id.progress);
        MaterialButton btnSubmit = findViewById(R.id.btnSubmit);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnSubmit.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onCreateTicketClicked(
                        txtSubject.getText().toString().trim(),
                        txtNarration.getText().toString().trim()
                );
            }
        });
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
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
    public boolean isValid() {
        if (txtSubject.getText().toString().trim().equals("")) {
            txtSubjectError.setVisibility(View.VISIBLE);
            return false;
        }
        txtSubjectError.setVisibility(View.GONE);
        return true;
    }
}
