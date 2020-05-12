package com.carmel.guestjini.Accounts;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Components.OkHttpClientInstance;
import com.carmel.guestjini.Models.Accounts.AccountTicket;
import com.carmel.guestjini.Models.Accounts.AccountTicketResponse;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Services.Accounts.AccountTicketService;
import com.carmel.guestjini.Services.Authentication.AuthService;
import com.carmel.guestjini.Services.Authentication.AuthServiceHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.BillDetailsAdapter;
import Model.BillDetailsModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RentInvoiceDetailsFragment extends Fragment {
    private ArrayList<BillDetailsModel> billsDetailsArrayList = new ArrayList<>();
    String AccountsTitle, RentInvoiceDate, RentInvoiceNo, RentInvoiceAmount, Amount, ReceiptsDate, ReceiptsAmount, BillsDate, BillsNo, ProductAmount, ProductName;
    private TextView rentInvoiceDate, rentInvoiceNo, rentInvoiceAmount, accountsTitle, receiptsDate, receiptsAmount, billsDate, billsNo, productAmount, productName, txtNarration;
    private RelativeLayout moreDetailsLayout, transactionSuccessfulLayout, billsDetailsLayout;
    private ScrollView rentInvoiceDetails;
    private ImageView dropDownIcon, backArrow;
    private Button payNowButton;
    private RecyclerView billsDetailsRecyclerView;
    private ConstraintLayout receiptsDetailsLayout, moreDetailsHeader;

    String accountTicketId = "";
    BillDetailsAdapter billsDetailsAdapter;
    AccountTicket accountTicket;
    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rent_invoice_details, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        progressDialog = builder.create();
        rentInvoiceDate = rootView.findViewById(R.id.rentDate);
        rentInvoiceNo = rootView.findViewById(R.id.rentNo);
        txtNarration = rootView.findViewById(R.id.towardsRent);
        rentInvoiceAmount = rootView.findViewById(R.id.totalAmount);
        moreDetailsHeader = rootView.findViewById(R.id.moreDetailsHeader);
        moreDetailsLayout = rootView.findViewById(R.id.moreDetailsLayout);
        dropDownIcon = rootView.findViewById(R.id.dropDownIcon);
        accountsTitle = rootView.findViewById(R.id.accountsHeaderTitle);
        backArrow = rootView.findViewById(R.id.backArrow);
        payNowButton = rootView.findViewById(R.id.payNowButton);
        transactionSuccessfulLayout = rootView.findViewById(R.id.transactionSuccessfulLayout);
        receiptsDetailsLayout = rootView.findViewById(R.id.receiptsDetailsLayout);
        receiptsDate = rootView.findViewById(R.id.receiptsDateValue);
        receiptsAmount = rootView.findViewById(R.id.amountValue);
        rentInvoiceDetails = rootView.findViewById(R.id.rentInvoiceDetails);
        billsDetailsLayout = rootView.findViewById(R.id.billsDetailsLayout);
        billsDate = rootView.findViewById(R.id.billsDate);
        billsNo = rootView.findViewById(R.id.billsNo);

        billsDetailsRecyclerView = rootView.findViewById(R.id.billsDetailsRecyclerView);

        final Bundle bundle = getArguments();
        if (bundle != null) {
            accountTicketId = bundle.getString("accountTicketId");
            getAccountTicket(accountTicketId);
            RentInvoiceDate = bundle.getString("RentInvoiceDate");
            RentInvoiceNo = bundle.getString("RentInvoiceNo");
            RentInvoiceAmount = bundle.getString("RentInvoiceAmount");
            AccountsTitle = bundle.getString("AccountsTitle");

            rentInvoiceDate.setText(RentInvoiceDate);
            rentInvoiceNo.setText(RentInvoiceNo);
            rentInvoiceAmount.setText(RentInvoiceAmount);
            accountsTitle.setText(AccountsTitle);
        }

        final Bundle bundle1 = getArguments();
        if (bundle1 != null) {
            ReceiptsDate = bundle1.getString("ReceiptsDate");
            ReceiptsAmount = bundle1.getString("ReceiptsAmount");
            AccountsTitle = bundle1.getString("AccountsTitle");

            receiptsDate.setText(ReceiptsDate);
            receiptsAmount.setText(ReceiptsAmount);
            accountsTitle.setText(AccountsTitle);
        }

        final Bundle bundle2 = getArguments();
        if (bundle2 != null) {
            BillsDate = bundle2.getString("BillsDate");
            BillsNo = bundle2.getString("BillsNo");
            AccountsTitle = bundle2.getString("AccountsTitle");

            billsDate.setText(BillsDate);
            billsNo.setText(BillsNo);
            accountsTitle.setText(AccountsTitle);
        }

        if (AccountsTitle.equals("RECEIPTS")) {
            receiptsDetailsLayout.setVisibility(View.VISIBLE);
            rentInvoiceDetails.setVisibility(View.GONE);
            billsDetailsLayout.setVisibility(View.GONE);
        }
        if (AccountsTitle.equals("LEDGER")) {
            receiptsDetailsLayout.setVisibility(View.GONE);
            rentInvoiceDetails.setVisibility(View.GONE);
            billsDetailsLayout.setVisibility(View.VISIBLE);
        }
        if (AccountsTitle.equals("BILLS")) {
            receiptsDetailsLayout.setVisibility(View.GONE);
            rentInvoiceDetails.setVisibility(View.GONE);
            billsDetailsLayout.setVisibility(View.VISIBLE);
        }
//        final Bundle bundle1=getArguments();
//        if(bundle1!=null){
//
//            Amount=bundle1.getString("amount");
////            rentInvoiceAmount.setText(Amount);
//            payNowButton.setVisibility(View.GONE);
//            transactionSuccessfulLayout.setVisibility(View.VISIBLE);
//        }

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountsDetailsFragment accountsDetailsFragment = new AccountsDetailsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.AccountsPlaceHolder, accountsDetailsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        moreDetailsHeader.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    dropDownIcon.setImageResource(R.drawable.drop_down_icon_xhdpi);
                    moreDetailsLayout.setVisibility(View.GONE);
                } else {
                    flag = true;
                    dropDownIcon.setImageResource(R.drawable.drop_up_icon);
                    moreDetailsLayout.setVisibility(View.VISIBLE);
                }

            }
        });

        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("accountTicketId", accountTicket.getId());
                bundle.putString("CUST_ID", accountTicket.getGuestId());
                bundle.putString("EMAIL", "prasanna.pete@gmail.com");
                bundle.putString("TXN_AMOUNT", String.valueOf(accountTicket.getNetTotal()));
                PaymentOptionsFragment paymentOptionsFragment = new PaymentOptionsFragment();
                paymentOptionsFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.AccountsPlaceHolder, paymentOptionsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        billsDetailsRecyclerView.setLayoutManager(linearLayoutManager);
        billsDetailsRecyclerView.setHasFixedSize(true);
        billsDetailsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        billsDetailsAdapter = new BillDetailsAdapter(getContext(), billsDetailsArrayList);
        billsDetailsRecyclerView.setAdapter(billsDetailsAdapter);

//        billsDetailsArrayList.add(new BillDetailsModel("Water Bottle", 100, 1, 110));
//        billsDetailsArrayList.add(new BillDetailsModel("Water Bottle", 100, 1, 110));
        return rootView;
    }

    private void getAccountTicket(String accountTicketId) {
        try {
            progressDialog.show();
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
            Map<String, String> postData = new HashMap<>();
            postData.put("id", accountTicketId);
            AccountTicketService accountTicketService = retrofit.create(AccountTicketService.class);
            Call<AccountTicketResponse> accountTicketResponseCall = accountTicketService.getRentInvoice(postData);
            accountTicketResponseCall.enqueue(new Callback<AccountTicketResponse>() {
                @Override
                public void onResponse(Call<AccountTicketResponse> call, Response<AccountTicketResponse> response) {
                    try {
                        progressDialog.dismiss();
                        AccountTicketResponse accountTicketResponse = response.body();
                        if (accountTicketResponse.getSuccess()) {
                            accountTicket = accountTicketResponse.getAccountTicket();
                            //TODO: FOrmat date
                            rentInvoiceDate.setText(accountTicket.getCreationTime());
                            rentInvoiceNo.setText(accountTicket.getTicketNumber());
                            txtNarration.setText(accountTicket.getTicketNarration());
                            rentInvoiceAmount.setText(String.valueOf(accountTicket.getNetTotal()));
                            billsDetailsArrayList = new ArrayList<>();
                            accountTicket.getAccountTicketItems().forEach(accountTicketItem -> {
                                billsDetailsArrayList.add(
                                        new BillDetailsModel(
                                                accountTicketItem.getItemNarration(),
                                                accountTicketItem.getRate(),
                                                accountTicketItem.getQty(),
                                                accountTicketItem.getItemTotal())
                                );

                            });
                            billsDetailsAdapter.update(billsDetailsArrayList);
                        } else {
                            showDialog(false, "There was problem fetching invoice! Please Try after sometime");
                        }
                    } catch (Exception ex) {
                        showDialog(false, "There was problem fetching invoice! Please Try after sometime");
                    }
                }

                @Override
                public void onFailure(Call<AccountTicketResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showDialog(false, "There was problem fetching invoice! Please Try after sometime");
                }
            });
        } catch (Exception ex) {
            progressDialog.dismiss();
            showDialog(false, "There was problem fetching invoice! Please Try after sometime");
        }
    }
    private void showDialog(boolean isSuccess, String message) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.alert_dailogbox);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView alertDailogTitle = (TextView) dialog.findViewById(R.id.alertDailogTitle);
        if (isSuccess) {
            alertDailogTitle.setText(getText(R.string.success));
        } else {
            alertDailogTitle.setText(getText(R.string.failed));
            alertDailogTitle.setTextColor(Color.parseColor("#E65959"));
        }
        TextView alertDailogMessage = (TextView) dialog.findViewById(R.id.alertDailogDescription);
        alertDailogMessage.setText(message);
        FloatingActionButton doneButton = (FloatingActionButton) dialog.findViewById(R.id.done_button);
        if (isSuccess) {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#32BDD2")));
        } else {
            doneButton.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#E65959")));
        }


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
