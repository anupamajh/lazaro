package com.carmel.guestjini.Accounts;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Accounts.AccountTicket;
import com.carmel.guestjini.Models.Accounts.AccountTicketResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Accounts.AccountTicketService;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;

import java.util.ArrayList;

import Adapter.BillsAdapter;
import Adapter.LedgerAdapter;
import Adapter.ReceiptsAdapter;
import Adapter.RentInvoiceAdapter;
import Model.BillsModel;
import Model.LedgerModel;
import Model.ReceiptsModel;
import Model.RentInvoiceModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AccountsDetailsFragment extends Fragment implements RentInvoiceAdapter.OnItemClickListener, ReceiptsAdapter.OnItemClickListener, LedgerAdapter.OnItemClickListener, BillsAdapter.OnItemClickListener {
    private RecyclerView rentInvoiceRecyclerView, receiptsRecyclerView, ledgerRecyclerView, billsRecyclerView;
    private ArrayList<AccountTicket> rentInvoiceArrayList = new ArrayList<>();
    private ArrayList<ReceiptsModel> receiptsArrayList = new ArrayList<>();
    private ArrayList<LedgerModel> ledgerArrayList = new ArrayList<>();
    private ArrayList<BillsModel> billsArrayList = new ArrayList<>();
    String AccountsTitle;
    private TextView accountsTitle;
    private ImageView backArrow;
    private RelativeLayout rentInvoiceListLayout, receiptsListLayout, ledgerMainLayout, billMainLayout;

    RentInvoiceAdapter rentInvoiceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_accounts_details, container, false);
        rentInvoiceRecyclerView = rootView.findViewById(R.id.rentInvoiceRecyclerView);
        receiptsRecyclerView = rootView.findViewById(R.id.receiptsRecyclerView);
        ledgerRecyclerView = rootView.findViewById(R.id.ledgerRecyclerView);
        billsRecyclerView = rootView.findViewById(R.id.billsRecyclerView);

        accountsTitle = rootView.findViewById(R.id.accountsHeaderTitle);
        backArrow = rootView.findViewById(R.id.backArrow);
        rentInvoiceListLayout = rootView.findViewById(R.id.rentInvoiceListLayout);
        receiptsListLayout = rootView.findViewById(R.id.receiptsListLayout);
        ledgerMainLayout = rootView.findViewById(R.id.ledgerMainLayout);
        billMainLayout = rootView.findViewById(R.id.billMainLayout);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountsFragment accountsFragment = new AccountsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.AccountsPlaceHolder, accountsFragment);
                fragmentTransaction.commit();
            }
        });


        final Bundle bundle = getArguments();
        if (bundle != null) {
            AccountsTitle = bundle.getString("AccountsTitle");
            accountsTitle.setText(AccountsTitle);
        }
        if (AccountsTitle.equals("RENT INVOICE")) {
            rentInvoiceListLayout.setVisibility(View.VISIBLE);
            receiptsListLayout.setVisibility(View.GONE);
            billsRecyclerView.setVisibility(View.GONE);
            ledgerMainLayout.setVisibility(View.GONE);
        }
        if (AccountsTitle.equals("RECEIPTS")) {
            rentInvoiceListLayout.setVisibility(View.GONE);
            receiptsListLayout.setVisibility(View.VISIBLE);
            ledgerMainLayout.setVisibility(View.GONE);
            billsRecyclerView.setVisibility(View.GONE);
        }
        if (AccountsTitle.equals("LEDGER")) {
            rentInvoiceListLayout.setVisibility(View.GONE);
            receiptsListLayout.setVisibility(View.GONE);
            ledgerMainLayout.setVisibility(View.VISIBLE);
            billsRecyclerView.setVisibility(View.GONE);
        }
        if (AccountsTitle.equals("BILLS")) {
            rentInvoiceListLayout.setVisibility(View.GONE);
            receiptsListLayout.setVisibility(View.GONE);
            ledgerMainLayout.setVisibility(View.GONE);
            billsRecyclerView.setVisibility(View.VISIBLE);
        }
//        final Bundle bundle1=getArguments();
//        if(bundle1!=null){
//            AccountsTitle=bundle1.getString("PageTitle");
//            accountsTitle.setText(AccountsTitle);
//        }
//       Rent Invoice
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rentInvoiceRecyclerView.setLayoutManager(linearLayoutManager);
        rentInvoiceRecyclerView.setHasFixedSize(true);
        rentInvoiceRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rentInvoiceAdapter = new RentInvoiceAdapter(getContext(), rentInvoiceArrayList, this);
        rentInvoiceRecyclerView.setAdapter(rentInvoiceAdapter);

//        rentInvoiceArrayList.add(new RentInvoiceModel("05 Jul 2019","RI00056780","Rs. 10,988",R.drawable.navigation_icon_xhdpi));
//        rentInvoiceArrayList.add(new RentInvoiceModel("02 Jun 2019","RI00056779","Rs. 10,112",R.drawable.navigation_icon_xhdpi));
//        rentInvoiceArrayList.add(new RentInvoiceModel("04 May 2019","RI00056778","Rs. 08,988",R.drawable.navigation_icon_xhdpi));
//        rentInvoiceArrayList.add(new RentInvoiceModel("10 Apr 2019","RI00056777","Rs. 07,988",R.drawable.navigation_icon_xhdpi));
//        rentInvoiceArrayList.add(new RentInvoiceModel("15 Mar 2019","RI00056776","Rs. 10,908",R.drawable.navigation_icon_xhdpi));

//        Receipts
        LinearLayoutManager receiptsLinearLayoutManager = new LinearLayoutManager(getContext());
        receiptsRecyclerView.setLayoutManager(receiptsLinearLayoutManager);
        receiptsRecyclerView.setHasFixedSize(true);
        receiptsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        ReceiptsAdapter receiptsAdapter = new ReceiptsAdapter(getContext(), receiptsArrayList, this);
        receiptsRecyclerView.setAdapter(receiptsAdapter);

        receiptsArrayList.add(new ReceiptsModel("01 Sep 2018", "RECEIPT/000001/2018", "04 SEP 2018, 7:59 PM", "Rs.50,000", R.drawable.navigation_next_xxhdpi));

//      Ledger
        LinearLayoutManager ledgerLinearLayoutManager = new LinearLayoutManager(getContext());
        ledgerRecyclerView.setLayoutManager(ledgerLinearLayoutManager);
        ledgerRecyclerView.setHasFixedSize(true);
        ledgerRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        LedgerAdapter ledgerAdapter = new LedgerAdapter(getContext(), ledgerArrayList, this);
        ledgerRecyclerView.setAdapter(ledgerAdapter);

        ledgerArrayList.add(new LedgerModel("05 Jul 2018", "RENT INVOICE", "Rs. 10,000 pm", 10000, -22688));
        ledgerArrayList.add(new LedgerModel("05 Jul 2018", "BILL", "House Keeping", 300, -12568));
        ledgerArrayList.add(new LedgerModel("05 Jul 2018", "BILL", "House Keeping", 1200, -12268));
        ledgerArrayList.add(new LedgerModel("05 Jul 2018", "DEBIT NOTE", "Breakage", 500, -11068));

//      Bills
        LinearLayoutManager billsLinearLayoutManager = new LinearLayoutManager(getContext());
        billsRecyclerView.setLayoutManager(billsLinearLayoutManager);
        billsRecyclerView.setHasFixedSize(true);
        billsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        BillsAdapter billsAdapter = new BillsAdapter(getContext(), billsArrayList, this);
        billsRecyclerView.setAdapter(billsAdapter);

        billsArrayList.add(new BillsModel("05 Sep 2018", "BILL/000024/2018-19", "Water Bottle", "Rs.110", R.drawable.navigation_next_xxhdpi));
        billsArrayList.add(new BillsModel("01 Sep 2018", "BILL/000024/2018-19", "Room Freshner", "Rs.330", R.drawable.navigation_next_xxhdpi));
        billsArrayList.add(new BillsModel("24 Aug 2018", "BILL/000024/2018-19", "Water Bottle", "Rs.230", R.drawable.navigation_next_xxhdpi));
        getMyRentInvoices();
        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        AccountTicket accountTicket = rentInvoiceArrayList.get(position);
        RentInvoiceDetailsFragment rentInvoiceDetailsFragment = new RentInvoiceDetailsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder, rentInvoiceDetailsFragment);
        Bundle bundle = new Bundle();
//        bundle.putString("RentInvoiceDate",rentInvoiceArrayList.get(position).getRentInvoiceDate());
//        bundle.putString("RentInvoiceNo",rentInvoiceArrayList.get(position).getRentInvoiceNo());
//        bundle.putString("RentInvoiceAmount",rentInvoiceArrayList.get(position).getRentInvoiceAmount());
        bundle.putString("AccountsTitle", AccountsTitle);
        bundle.putString("accountTicketId",accountTicket.getId());
        rentInvoiceDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();

    }

    @Override
    public void onReceiptsClick(int position) {
        RentInvoiceDetailsFragment rentInvoiceDetailsFragment = new RentInvoiceDetailsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder, rentInvoiceDetailsFragment);
        Bundle bundle = new Bundle();
        bundle.putString("ReceiptsDate", receiptsArrayList.get(position).getReceiptsDate());
        bundle.putString("ReceiptsAmount", receiptsArrayList.get(position).getAmount());
        bundle.putString("AccountsTitle", AccountsTitle);
        rentInvoiceDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onLedgerItemClick(int position) {
        RentInvoiceDetailsFragment rentInvoiceDetailsFragment = new RentInvoiceDetailsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder, rentInvoiceDetailsFragment);
        Bundle bundle = new Bundle();
//        bundle.putString("RentInvoiceDate",rentInvoiceArrayList.get(position).getRentInvoiceDate());
//        bundle.putString("RentInvoiceNo",rentInvoiceArrayList.get(position).getRentInvoiceNo());
//        bundle.putString("RentInvoiceAmount",rentInvoiceArrayList.get(position).getRentInvoiceAmount());
        bundle.putString("AccountsTitle", AccountsTitle);
        rentInvoiceDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onBillsDetailsClick(int position) {
        RentInvoiceDetailsFragment rentInvoiceDetailsFragment = new RentInvoiceDetailsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder, rentInvoiceDetailsFragment);
        Bundle bundle = new Bundle();
        bundle.putString("BillsDate", billsArrayList.get(position).getBillsDate());
        bundle.putString("BillsNo", billsArrayList.get(position).getBillsNo());
//        bundle.putString("ProductAmount",billsArrayList.get(position).getProductAmount());
        bundle.putString("AccountsTitle", AccountsTitle);
        rentInvoiceDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }


    private void getMyRentInvoices() {
        try {
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

            AccountTicketService accountTicketService = retrofit.create(AccountTicketService.class);
            Call<AccountTicketResponse> accountTicketResponseCall = accountTicketService.getMyRentInvoices();
            accountTicketResponseCall.enqueue(new Callback<AccountTicketResponse>() {
                @Override
                public void onResponse(Call<AccountTicketResponse> call, Response<AccountTicketResponse> response) {
                    AccountTicketResponse accountTicketResponse = response.body();
                    if(accountTicketResponse.getSuccess()){
                        rentInvoiceArrayList = new ArrayList<>();
                        rentInvoiceArrayList.addAll(accountTicketResponse.getAccountTicketList());
                        rentInvoiceAdapter.update(accountTicketResponse.getAccountTicketList());

                    }else{
                        //TODo: Display appropriate error message and Handle the error
                    }
                }

                @Override
                public void onFailure(Call<AccountTicketResponse> call, Throwable t) {
                    //TODo: Display appropriate error message and Handle the error
                }
            });
        }catch (Exception ex){
            //TODo: Display appropriate error message and Handle the error
        }

    }
}
