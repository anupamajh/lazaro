package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketDetailViewMVC;

public class AssignTicketSheetViewMVCImpl
        extends BaseObservableViewMvc<AssignTicketSheetViewMVC.Listener>
        implements AssignTicketSheetViewMVC
{

    private final Spinner cmbGroup;
    private final Button btnSheetAssignTicket;

    public AssignTicketSheetViewMVCImpl(LayoutInflater inflater,
                                        @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_inbox_assign_ticket_sheet, parent, false));
        cmbGroup = findViewById(R.id.cmbGroup);
        btnSheetAssignTicket = findViewById(R.id.btnSheetAssignTicket);

        btnSheetAssignTicket.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (Listener listener: getListeners()){
                            listener.onAssignTicketClicked();
                        }
                    }
                }
        );

    }

    @Override
    public void showProgressIndication() {

    }

    @Override
    public void hideProgressIndication() {

    }
}
