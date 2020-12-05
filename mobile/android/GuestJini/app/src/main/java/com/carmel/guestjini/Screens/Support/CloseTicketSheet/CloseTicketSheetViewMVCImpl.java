package com.carmel.guestjini.Screens.Support.CloseTicketSheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class CloseTicketSheetViewMVCImpl
        extends BaseObservableViewMvc<CloseTicketSheetViewMVC.Listener>
        implements CloseTicketSheetViewMVC {
    private final EditText txtFeedBack;
    private final Button btnCloseTicket;

    public CloseTicketSheetViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_inbox_close_ticket_sheet, parent, false));
        txtFeedBack = findViewById(R.id.txtFeedBack);
        btnCloseTicket = findViewById(R.id.btnCloseTicket);

        btnCloseTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener: getListeners()){
                    listener.onCloseTicketClicked(txtFeedBack.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void showProgressIndication() {

    }

    @Override
    public void hideProgressIndication() {

    }

    @Override
    public void showTicketClosed() {
        Toast.makeText(getContext(), "Ticket has been closed.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketCloseFailed() {
        Toast.makeText(getContext(), "There was an error closing ticket, Kindly try after sometime.", Toast.LENGTH_LONG).show();
    }
}
