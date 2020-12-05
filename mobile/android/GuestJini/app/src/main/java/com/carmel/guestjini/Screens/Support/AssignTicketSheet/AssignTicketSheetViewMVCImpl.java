package com.carmel.guestjini.Screens.Support.AssignTicketSheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.ArrayList;
import java.util.List;

public class AssignTicketSheetViewMVCImpl
        extends BaseObservableViewMvc<AssignTicketSheetViewMVC.Listener>
        implements AssignTicketSheetViewMVC
{

    private final Spinner cmbGroup;
    private final Button btnSheetAssignTicket;

    private List<TaskAssignee> taskAssigneeList;
    private ArrayAdapter<TaskAssignee> assigneeArrayAdapter;

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
                        int selectedItemPosition = cmbGroup.getSelectedItemPosition();
                        TaskAssignee taskAssignee = taskAssigneeList.get(selectedItemPosition);
                        for (Listener listener: getListeners()){
                            listener.onAssignTicketClicked(taskAssignee);
                        }
                    }
                }
        );
        taskAssigneeList = new ArrayList<>();
        assigneeArrayAdapter = new ArrayAdapter<TaskAssignee>(getContext(), android.R.layout.simple_list_item_1, taskAssigneeList);
        cmbGroup.setAdapter(assigneeArrayAdapter);


    }

    @Override
    public void bindTaskAssignees(List<TaskAssignee> taskAssigneeList) {
        this.taskAssigneeList = taskAssigneeList;
        assigneeArrayAdapter.clear();
        assigneeArrayAdapter.addAll(taskAssigneeList);
        assigneeArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressIndication() {

    }

    @Override
    public void hideProgressIndication() {

    }

    @Override
    public void showTicketAssigned() {
        Toast.makeText(getContext(), "Ticket has been assigned successfully.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTicketAssignFailed() {
        Toast.makeText(getContext(), "There was a problem assigning the ticket", Toast.LENGTH_LONG).show();
    }
}
