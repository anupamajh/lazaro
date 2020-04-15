package com.carmel.guestjini.Accounts;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Adapter.AccountsAdapter;
import Model.AccountsModel;


public class AccountsFragment extends Fragment implements AccountsAdapter.OnItemClickListener{
    private RecyclerView accountsRecyclerView;
    ArrayList<AccountsModel> accountsArrayList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_accounts, container, false);
        accountsRecyclerView=rootView.findViewById(R.id.accountsRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        accountsRecyclerView.setLayoutManager(linearLayoutManager);
        accountsRecyclerView.setHasFixedSize(true);
        AccountsAdapter accountsAdapter=new AccountsAdapter(getContext(),accountsArrayList,this);
        accountsRecyclerView.setAdapter(accountsAdapter);

        accountsArrayList.add(new AccountsModel("RENT INVOICE","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis"));
        accountsArrayList.add(new AccountsModel("RECEIPTS","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis"));
        accountsArrayList.add(new AccountsModel("LEDGER","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis"));
        accountsArrayList.add(new AccountsModel("BILLS","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam erat sapien, ultricies quis"));

        return rootView;
    }

    @Override
    public void onItemClick(int position) {
        AccountsDetailsFragment accountsDetailsFragment=new AccountsDetailsFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder,accountsDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle=new Bundle();
        bundle.putString("AccountsTitle",accountsArrayList.get(position).getAccountsTitle());
        accountsDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onReceiptClick(int position) {
        AccountsDetailsFragment accountsDetailsFragment=new AccountsDetailsFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder,accountsDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle=new Bundle();
        bundle.putString("AccountsTitle",accountsArrayList.get(position).getAccountsTitle());
        accountsDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onLedgerItemCilck(int position) {
        AccountsDetailsFragment accountsDetailsFragment=new AccountsDetailsFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder,accountsDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle=new Bundle();
        bundle.putString("AccountsTitle",accountsArrayList.get(position).getAccountsTitle());
        accountsDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onBillsItemClick(int position) {
        AccountsDetailsFragment accountsDetailsFragment=new AccountsDetailsFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.AccountsPlaceHolder,accountsDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        Bundle bundle=new Bundle();
        bundle.putString("AccountsTitle",accountsArrayList.get(position).getAccountsTitle());
        accountsDetailsFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
}
