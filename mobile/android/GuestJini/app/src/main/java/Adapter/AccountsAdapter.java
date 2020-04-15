package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.AccountsModel;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<AccountsModel> accountsModelList;
    private OnItemClickListener onItemClickListener;

    public AccountsAdapter(Context context, ArrayList<AccountsModel> accountsModelList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.accountsModelList = accountsModelList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public AccountsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.accounts_landing_list,parent,false);
        return new AccountsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsAdapter.ViewHolder holder, int position) {
        AccountsModel accountsModel=accountsModelList.get(position);
        holder.accountsTitle.setText(accountsModel.getAccountsTitle());
        holder.accountsDescription.setText(accountsModel.getAccountsDescription());
    }

    @Override
    public int getItemCount() {
        return accountsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView accountsTitle,accountsDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountsTitle=itemView.findViewById(R.id.accountsTilte);
            accountsDescription=itemView.findViewById(R.id.accountsDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
            onItemClickListener.onReceiptClick(getAdapterPosition());
            onItemClickListener.onLedgerItemCilck(getAdapterPosition());
            onItemClickListener.onBillsItemClick(getAdapterPosition());

        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onReceiptClick(int position);
        void onLedgerItemCilck(int position);
        void onBillsItemClick(int position);
    }
}
