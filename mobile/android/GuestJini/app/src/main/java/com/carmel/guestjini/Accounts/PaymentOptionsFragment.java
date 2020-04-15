package com.carmel.guestjini.Accounts;


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

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.PaymentOptionsAdapter;
import Model.PaymentOptionsModel;

public class PaymentOptionsFragment extends Fragment implements PaymentOptionsAdapter.OnItemClickListener{
    private RecyclerView paymentOptionsRecyclerView;
    private ArrayList<PaymentOptionsModel> paymentOptionsArrayList=new ArrayList<>();
    private ImageView backArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_payment_options, container, false);
        paymentOptionsRecyclerView=rootView.findViewById(R.id.paymentOptionsRecyclerView);
        backArrow=rootView.findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentInvoiceDetailsFragment rentInvoiceDetailsFragment=new RentInvoiceDetailsFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.AccountsPlaceHolder,rentInvoiceDetailsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        paymentOptionsRecyclerView.setLayoutManager(linearLayoutManager);
        paymentOptionsRecyclerView.setHasFixedSize(true);
        paymentOptionsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        PaymentOptionsAdapter paymentOptionsAdapter=new PaymentOptionsAdapter(getContext(),paymentOptionsArrayList,this);
        paymentOptionsRecyclerView.setAdapter(paymentOptionsAdapter);

        PaymentOptionsModel paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.google_pay_icon_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("Google Pay - Earn Flat Rs 250* on your 1st ever transaction");
        paymentOptionsArrayList.add(paymentOptionsModel);

        paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.debit_card_icon_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("Debit/Credit Card");
        paymentOptionsArrayList.add(paymentOptionsModel);

        paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.net_banking_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("Net Banking");
        paymentOptionsArrayList.add(paymentOptionsModel);

        paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.upi_icon_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("UPI");
        paymentOptionsArrayList.add(paymentOptionsModel);

        paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.mobile_wallet_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("Mobile Wallets");
        paymentOptionsArrayList.add(paymentOptionsModel);

        paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.gift_voucher_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("Gift Voucher");
        paymentOptionsArrayList.add(paymentOptionsModel);

        paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.credit_voucher_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("Credit Voucher");
        paymentOptionsArrayList.add(paymentOptionsModel);

        paymentOptionsModel=new PaymentOptionsModel();
        paymentOptionsModel.setPaymentOptionsIcon(R.drawable.redeem_points_xxhdpi);
        paymentOptionsModel.setPaymentOptionsName("Redeem Points");
        paymentOptionsArrayList.add(paymentOptionsModel);
        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        DebitCreditCardFragment debitCreditCardFragment=new DebitCreditCardFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder,debitCreditCardFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
