package com.carmel.guestjini.Screens.Accounts.RentInvoiceList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Accounts.AccountTicket;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceItem.RentInvoiceItemMVC;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;

import java.util.ArrayList;
import java.util.List;

public class RentInvoiceRecycleAdapter
        extends RecyclerView.Adapter<RentInvoiceRecycleAdapter.AccountViewHolder>
        implements RentInvoiceItemMVC.Listener {

    public interface Listener {
        void onAccountItemClicked(AccountTicket accountTicket);
    }

    static class AccountViewHolder extends RecyclerView.ViewHolder {
        private final RentInvoiceItemMVC viewMVC;
        public AccountViewHolder(@NonNull RentInvoiceItemMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<AccountTicket> accountTickets = new ArrayList<>();

    public RentInvoiceRecycleAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindAccountTickets(List<AccountTicket> accountTickets) {
        this.accountTickets = new ArrayList<>(accountTickets);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RentInvoiceItemMVC viewMvc = viewMVCFactory.getRentInvoiceItemMVC(parent);
        viewMvc.registerListener(this);
        return new AccountViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        holder.viewMVC.bindAccountTicket(this.accountTickets.get(position));
    }

    @Override
    public int getItemCount() {
        return this.accountTickets.size();
    }

    @Override
    public void onRentItemClicked(AccountTicket accountTicket) {
        listener.onAccountItemClicked(accountTicket);
    }
}
