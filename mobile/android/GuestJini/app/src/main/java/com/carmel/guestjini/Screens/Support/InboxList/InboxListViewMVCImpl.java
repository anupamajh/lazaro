package com.carmel.guestjini.Screens.Support.InboxList;

import android.app.DatePickerDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.Search.OrderUnit;
import com.carmel.guestjini.Common.Search.SearchCriteria;
import com.carmel.guestjini.Common.Search.SearchRequest;
import com.carmel.guestjini.Common.Search.SearchUnit;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InboxListViewMVCImpl
        extends BaseObservableViewMvc<InboxListViewMVC.Listener>
        implements InboxListViewMVC,
        InboxListRecyclerAdapter.Listener {

    private final int TICKET_STATUS_FILTER_NONE = 0;
    private final int TICKET_STATUS_FILTER_OPEN = 1;
    private final int TICKET_STATUS_FILTER_DRAFT = 2;
    private final int TICKET_STATUS_FILTER_CLOSED = 3;


    private final EditText txtSearch;
    private final FloatingActionButton btnNewTicket;
    private final InboxListRecyclerAdapter inboxListRecyclerAdapter;
    private final ProgressBar progressBar;
    private final RelativeLayout layoutNoResult;
    private final RelativeLayout layoutClearAllEnabled;
    private final RelativeLayout layoutClearAllDisabled;
    private final DrawerLayout inboxListDrawerLayout;
    private final ImageView btnFilter;
    private final ImageView btnSortOption;
    private final TextView txtHeaderTitle;
    private final RelativeLayout chkTicketStatusOpen;
    private final RelativeLayout chkTicketStatusClosed;
    private final RelativeLayout chkTicketStatusDraft;
    private final TextView txtSelectDate;
    private final TextView txtSortDirection;
    private final TextView txtSelectDateHeading;
    private final TextView txtSelectToDate;
    private final TextView txtSelectToDateHeading;
    private final Spinner cmbDateFilter;
    private final RadioButton rdbSubmittedDate;
    private final RadioButton rdbSubject;
    private final RadioButton rdbStatus;
    private final RadioGroup rdgSordOptions;
    private final Calendar calendar;
    private int chkTicketStatus = 0;
    private boolean isDraftSelected = false;
    private boolean isOpenSelected = false;
    private boolean isClosedSelected = false;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int mToYear, mToMonth, mToDay, mToHour, mToMinute;


    public InboxListViewMVCImpl(LayoutInflater inflater,
                                @Nullable ViewGroup parent,
                                ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_inbox_ticket_list, parent, false));
        txtSelectDateHeading = findViewById(R.id.txtSelectDateHeading);
        txtSelectToDate = findViewById(R.id.txtSelectToDate);
        txtSelectToDateHeading = findViewById(R.id.txtSelectToDateHeading);
        rdgSordOptions = findViewById(R.id.rdgSordOptions);
        rdbSubject = findViewById(R.id.rdbSubject);
        rdbSubmittedDate = findViewById(R.id.rdbSubmittedDate);
        rdbStatus = findViewById(R.id.rdbStatus);
        cmbDateFilter = findViewById(R.id.cmbDateFilter);
        chkTicketStatusOpen = findViewById(R.id.chkTicketStatusOpen);
        chkTicketStatusClosed = findViewById(R.id.chkTicketStatusClosed);
        chkTicketStatusDraft = findViewById(R.id.chkTicketStatusDraft);
        txtHeaderTitle = findViewById(R.id.txtHeaderTitle);
        txtSortDirection = findViewById(R.id.txtSortDirection);
        txtSelectDate = findViewById(R.id.txtSelectDate);
        txtSearch = findViewById(R.id.txtSearch);
        btnFilter = findViewById(R.id.btnFilter);
        btnSortOption = findViewById(R.id.btnSortOption);
        progressBar = findViewById(R.id.progress);
        layoutNoResult = findViewById(R.id.layoutNoResult);
        inboxListDrawerLayout = findViewById(R.id.inboxListDrawerLayout);
        layoutClearAllDisabled = findViewById(R.id.layoutClearAllDisabled);
        layoutClearAllEnabled = findViewById(R.id.layoutClearAllEnabled);
        RecyclerView ticketRecyclerView = findViewById(R.id.lstTickets);
        ticketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        inboxListRecyclerAdapter = new InboxListRecyclerAdapter(this, viewMVCFactory);
        ticketRecyclerView.setAdapter(inboxListRecyclerAdapter);
        ImageView btnSearch = findViewById(R.id.btnSearch);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnNewTicket = findViewById(R.id.btnNewTicket);
        btnSearch.setOnClickListener(view -> {
            String searchText = txtSearch.getText().toString().trim();
            for (Listener listener : getListeners()) {
                listener.onSearchClicked(searchText);
            }
        });
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mToYear = calendar.get(Calendar.YEAR);
        mToMonth = calendar.get(Calendar.MONTH);
        mToDay = calendar.get(Calendar.DAY_OF_MONTH);
        txtSelectDate.setText(mDay + "-" + (mMonth + 1) + "-" + mYear);
        txtSelectDate.setVisibility(View.GONE);
        txtSelectDateHeading.setVisibility(View.GONE);

        txtSelectToDate.setText(mToDay + "-" + (mToMonth + 1) + "-" + mToYear);
        txtSelectToDate.setVisibility(View.GONE);
        txtSelectToDateHeading.setVisibility(View.GONE);

        layoutClearAllEnabled.setVisibility(View.GONE);
        layoutClearAllEnabled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllFilters();
            }
        });

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    String searchText = txtSearch.getText().toString().trim();
                    for (Listener listener : getListeners()) {
                        listener.onSearchClicked(searchText);
                    }
                    handled = true;
                }
                return handled;
            }
        });

        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

        btnNewTicket.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onCreateTicketClicked();
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inboxListDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        chkTicketStatusOpen.setOnClickListener(new View.OnClickListener() {
            boolean isChecked = false;

            @Override
            public void onClick(View v) {
                if (!isChecked) {
                    chkTicketStatusOpen.setBackground(getContext().getDrawable(R.drawable.check_box_checked));
                    chkTicketStatus = TICKET_STATUS_FILTER_OPEN;
                    isOpenSelected = true;
                } else {
                    isOpenSelected = false;
                    chkTicketStatus = TICKET_STATUS_FILTER_NONE;
                    chkTicketStatusOpen.setBackground(getContext().getDrawable(R.drawable.check_box_unchecked));
                }
                for (Listener listener : getListeners()) {
                    listener.onFilterApplied(collectSearchRequest());
                }
                isChecked = !isChecked;
            }
        });

        chkTicketStatusClosed.setOnClickListener(new View.OnClickListener() {
            boolean isChecked = false;

            @Override
            public void onClick(View v) {
                if (!isChecked) {
                    chkTicketStatusClosed.setBackground(getContext().getDrawable(R.drawable.check_box_checked));
                    chkTicketStatus = TICKET_STATUS_FILTER_CLOSED;
                    isClosedSelected = true;
                } else {
                    isClosedSelected = false;
                    chkTicketStatus = TICKET_STATUS_FILTER_NONE;
                    chkTicketStatusClosed.setBackground(getContext().getDrawable(R.drawable.check_box_unchecked));
                }
                for (Listener listener : getListeners()) {
                    listener.onFilterApplied(collectSearchRequest());
                }
                isChecked = !isChecked;
            }
        });

        chkTicketStatusDraft.setOnClickListener(new View.OnClickListener() {
            boolean isChecked = false;

            @Override
            public void onClick(View v) {
                if (!isChecked) {
                    chkTicketStatusDraft.setBackground(getContext().getDrawable(R.drawable.check_box_checked));
                    chkTicketStatus = TICKET_STATUS_FILTER_DRAFT;
                    isDraftSelected = true;
                } else {
                    isDraftSelected = false;
                    chkTicketStatus = TICKET_STATUS_FILTER_NONE;
                    chkTicketStatusDraft.setBackground(getContext().getDrawable(R.drawable.check_box_unchecked));
                }
                for (Listener listener : getListeners()) {
                    listener.onFilterApplied(collectSearchRequest());
                }
                isChecked = !isChecked;
            }
        });

        txtSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mMonth = monthOfYear;
                                mYear = year;
                                mDay = dayOfMonth;

                                txtSelectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                for (Listener listener : getListeners()) {
                                    listener.onFilterApplied(collectSearchRequest());
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        txtSelectToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mToDay = monthOfYear;
                                mToDay = year;
                                mToDay = dayOfMonth;
                                txtSelectToDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                for (Listener listener : getListeners()) {
                                    listener.onFilterApplied(collectSearchRequest());
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnSortOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSortDirection.getText().toString().toLowerCase().trim().equals("ascending")) {
                    txtSortDirection.setText("Descending");
                    btnSortOption.setImageResource(R.drawable.dropdown_cyan_icon);
                } else {
                    txtSortDirection.setText("Ascending");
                    btnSortOption.setImageResource(R.drawable.collapse_cyan_icon);
                }
                for (Listener listener : getListeners()) {
                    listener.onFilterApplied(collectSearchRequest());
                }
            }
        });

        cmbDateFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 5:
                        txtSelectDate.setVisibility(View.VISIBLE);
                        txtSelectDateHeading.setVisibility(View.VISIBLE);
                        txtSelectToDate.setVisibility(View.GONE);
                        txtSelectToDateHeading.setVisibility(View.GONE);
                        break;
                    case 6:
                        txtSelectDate.setVisibility(View.VISIBLE);
                        txtSelectDateHeading.setVisibility(View.VISIBLE);
                        txtSelectToDate.setVisibility(View.VISIBLE);
                        txtSelectToDateHeading.setVisibility(View.VISIBLE);
                        break;
                    default:
                        txtSelectDate.setVisibility(View.GONE);
                        txtSelectDateHeading.setVisibility(View.GONE);
                        txtSelectToDate.setVisibility(View.GONE);
                        txtSelectToDateHeading.setVisibility(View.GONE);
                }
                for (Listener listener : getListeners()) {
                    listener.onFilterApplied(collectSearchRequest());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rdgSordOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (Listener listener : getListeners()) {
                    listener.onFilterApplied(collectSearchRequest());
                }
            }
        });
    }


    @Override
    public void onTicketClicked(Ticket ticket) {
        for (Listener listener : getListeners()) {
            listener.onTicketClicked(ticket);
        }
    }

    @Override
    public void bindTickets(List<Ticket> ticketList, int totalItems) {
        if (ticketList.size() == 0) {
            layoutNoResult.setVisibility(View.VISIBLE);
        } else {
            layoutNoResult.setVisibility(View.GONE);
        }
        inboxListRecyclerAdapter.bindTickets(ticketList);
    }

    @Override
    public void bindListTitle(int inboxType) {
        switch (inboxType) {
            case 1:
                txtHeaderTitle.setText("SHARED INBOX");
                break;
            case 2:
                txtHeaderTitle.setText("YOUR INBOX");
                break;
            case 3:
                txtHeaderTitle.setText("TEAM INBOX");
                break;
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

    private void clearAllFilters(){
        cmbDateFilter.setSelection(0);
        if(isClosedSelected){
            chkTicketStatusClosed.callOnClick();
        }
        if(isDraftSelected){
            chkTicketStatusDraft.callOnClick();
        }
        if(isOpenSelected){
            chkTicketStatusOpen.callOnClick();
        }
        rdbSubmittedDate.setChecked(true);
        txtSortDirection.setText("Ascending");
        btnSortOption.setImageResource(R.drawable.collapse_cyan_icon);
        for (Listener listener : getListeners()) {
            listener.onFilterApplied(collectSearchRequest());
        }

    }

    private SearchRequest collectSearchRequest() {
        boolean hasFilter = false;
        SearchRequest searchRequest = new SearchRequest();
        String sortColumn = "creationTime";
        String sortDirection = "ASC";
        if (rdbSubmittedDate.isChecked()) {
            sortColumn = "creationTime";
        }
        if (rdbStatus.isChecked()) {
            sortColumn = "ticketStatus";
        }
        if (rdbSubject.isChecked()) {
            sortColumn = "ticketTitle";
        }
        if (txtSortDirection.getText().toString().toLowerCase().trim().equals("ascending")) {
            sortDirection = "ASC";
        } else {
            sortDirection = "DESC";
        }
        OrderUnit orderUnit = new OrderUnit();
        orderUnit.setDirection(sortDirection);
        orderUnit.setOrderBy(sortColumn);
        List<OrderUnit> orderUnits = new ArrayList<>();
        orderUnits.add(orderUnit);
        searchRequest.setOrderUnits(orderUnits);
        List<SearchUnit> searchUnits = new ArrayList<>();
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();

        SearchCriteria searchCriteria = new SearchCriteria();
        if (isClosedSelected) {
            searchUnits = new ArrayList<>();
            SearchUnit searchUnit = new SearchUnit();
            searchUnit.setField("ticketStatus");
            searchUnit.setOperator("equal");
            searchUnit.setValue("5");
            searchUnits.add(searchUnit);
            searchCriteria = new SearchCriteria();
            searchCriteria.setCondition("or");
            searchCriteria.setSearchUnitCondition("or");
            searchCriteria.setSearchUnits(searchUnits);
            searchCriteriaList.add(searchCriteria);
            hasFilter = true;
        }

        if (isDraftSelected) {
            searchUnits = new ArrayList<>();
            SearchUnit searchUnit = new SearchUnit();
            searchUnit.setField("ticketStatus");
            searchUnit.setOperator("equal");
            searchUnit.setValue("0");
            searchUnits.add(searchUnit);
            searchCriteria = new SearchCriteria();
            searchCriteria.setCondition("or");
            searchCriteria.setSearchUnitCondition("or");
            searchCriteria.setSearchUnits(searchUnits);
            searchCriteriaList.add(searchCriteria);
            hasFilter = true;
        }

        if (isOpenSelected) {
            searchUnits = new ArrayList<>();
            SearchUnit searchUnit = new SearchUnit();
            searchUnit.setField("ticketStatus");
            searchUnit.setOperator("equal");
            searchUnit.setValue("1");
            searchUnits.add(searchUnit);
            searchUnit = new SearchUnit();
            searchUnit.setField("ticketStatus");
            searchUnit.setOperator("equal");
            searchUnit.setValue("2");
            searchUnits.add(searchUnit);
            searchUnit = new SearchUnit();
            searchUnit.setField("ticketStatus");
            searchUnit.setOperator("equal");
            searchUnit.setValue("3");
            searchUnits.add(searchUnit);
            searchUnit = new SearchUnit();
            searchUnit.setField("ticketStatus");
            searchUnit.setOperator("equal");
            searchUnit.setValue("4");
            searchUnits.add(searchUnit);

            searchCriteria = new SearchCriteria();
            searchCriteria.setCondition("or");
            searchCriteria.setSearchUnitCondition("or");
            searchCriteria.setSearchUnits(searchUnits);
            searchCriteriaList.add(searchCriteria);
            hasFilter = true;
        }
        DateTimeFormatter dtfStart = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
         switch (cmbDateFilter.getSelectedItemPosition()) {
            case 0:
                break;
            case 1: {
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

                searchUnits = new ArrayList<>();
                SearchUnit searchUnit = new SearchUnit();
                searchUnit.setField("creationTime");
                searchUnit.setOperator("lessthanorequal");
                searchUnit.setValue(dtfStart.format(tomorrow));
                searchUnits.add(searchUnit);

                searchUnit = new SearchUnit();
                searchUnit.setField("creationTime");
                searchUnit.setOperator("greaterthanorequal");
                searchUnit.setValue(dtfStart.format(today));
                searchUnits.add(searchUnit);
                searchCriteria = new SearchCriteria();
                searchCriteria.setCondition("and");
                searchCriteria.setSearchUnitCondition("and");
                searchCriteria.setSearchUnits(searchUnits);
                searchCriteriaList.add(searchCriteria);
                hasFilter = true;
            }
            break;
             case 2: {
                 LocalDateTime today = LocalDateTime.now().minusDays(1);
                 LocalDateTime tomorrow = LocalDateTime.now();

                 searchUnits = new ArrayList<>();
                 SearchUnit searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("lessthanorequal");
                 searchUnit.setValue(dtfStart.format(tomorrow));
                 searchUnits.add(searchUnit);

                 searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("greaterthanorequal");
                 searchUnit.setValue(dtfStart.format(today));
                 searchUnits.add(searchUnit);
                 searchCriteria = new SearchCriteria();
                 searchCriteria.setCondition("and");
                 searchCriteria.setSearchUnitCondition("and");
                 searchCriteria.setSearchUnits(searchUnits);
                 searchCriteriaList.add(searchCriteria);
                 hasFilter = true;
             }
             break;
             case 3: {
                 LocalDateTime today = LocalDateTime.now().minusDays(7);
                 LocalDateTime tomorrow = LocalDateTime.now();

                 searchUnits = new ArrayList<>();
                 SearchUnit searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("lessthanorequal");
                 searchUnit.setValue(dtfStart.format(tomorrow));
                 searchUnits.add(searchUnit);

                 searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("greaterthanorequal");
                 searchUnit.setValue(dtfStart.format(today));
                 searchUnits.add(searchUnit);
                 searchCriteria = new SearchCriteria();
                 searchCriteria.setCondition("and");
                 searchCriteria.setSearchUnitCondition("and");
                 searchCriteria.setSearchUnits(searchUnits);
                 searchCriteriaList.add(searchCriteria);
                 hasFilter = true;
             }
             break;
             case 4: {
                 LocalDateTime today = LocalDateTime.now().minusMonths(1);
                 LocalDateTime tomorrow = LocalDateTime.now();

                 searchUnits = new ArrayList<>();
                 SearchUnit searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("lessthanorequal");
                 searchUnit.setValue(dtfStart.format(tomorrow));
                 searchUnits.add(searchUnit);

                 searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("greaterthanorequal");
                 searchUnit.setValue(dtfStart.format(today));
                 searchUnits.add(searchUnit);
                 searchCriteria = new SearchCriteria();
                 searchCriteria.setCondition("and");
                 searchCriteria.setSearchUnitCondition("and");
                 searchCriteria.setSearchUnits(searchUnits);
                 searchCriteriaList.add(searchCriteria);
                 hasFilter = true;
             }
             break;

             case 5: {
                 LocalDateTime today = LocalDateTime.now().withMonth(mMonth).withYear(mYear).withDayOfMonth(mDay);
                 LocalDateTime tomorrow = LocalDateTime.now().withMonth(mMonth).withYear(mYear).withDayOfMonth(mDay).plusDays(1);

                 searchUnits = new ArrayList<>();
                 SearchUnit searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("lessthanorequal");
                 searchUnit.setValue(dtfStart.format(tomorrow));
                 searchUnits.add(searchUnit);

                 searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("greaterthanorequal");
                 searchUnit.setValue(dtfStart.format(today));
                 searchUnits.add(searchUnit);
                 searchCriteria = new SearchCriteria();
                 searchCriteria.setCondition("and");
                 searchCriteria.setSearchUnitCondition("and");
                 searchCriteria.setSearchUnits(searchUnits);
                 searchCriteriaList.add(searchCriteria);
                 hasFilter = true;
             }
             break;
             case 6: {
                 LocalDateTime today = LocalDateTime.now().withMonth(mMonth).withYear(mYear).withDayOfMonth(mDay);
                 LocalDateTime tomorrow = LocalDateTime.now().withMonth(mToMonth).withYear(mToYear).withDayOfMonth(mToMonth).plusDays(1);

                 searchUnits = new ArrayList<>();
                 SearchUnit searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("lessthanorequal");
                 searchUnit.setValue(dtfStart.format(tomorrow));
                 searchUnits.add(searchUnit);

                 searchUnit = new SearchUnit();
                 searchUnit.setField("creationTime");
                 searchUnit.setOperator("greaterthanorequal");
                 searchUnit.setValue(dtfStart.format(today));
                 searchUnits.add(searchUnit);
                 searchCriteria = new SearchCriteria();
                 searchCriteria.setCondition("and");
                 searchCriteria.setSearchUnitCondition("and");
                 searchCriteria.setSearchUnits(searchUnits);
                 searchCriteriaList.add(searchCriteria);
                 hasFilter = true;
             }
             break;

         }
        searchRequest.setSearchCriteria(searchCriteriaList);
         if(hasFilter){
             layoutClearAllDisabled.setVisibility(View.GONE);
             layoutClearAllEnabled.setVisibility(View.VISIBLE);
         }else{
             layoutClearAllDisabled.setVisibility(View.VISIBLE);
             layoutClearAllEnabled.setVisibility(View.GONE);
         }
        return searchRequest;
    }

}
