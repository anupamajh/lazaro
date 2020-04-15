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


import Model.LedgerModel;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LedgerModel> ledgerModelList;
    private OnItemClickListener onItemClickListener;

    public LedgerAdapter(Context context, ArrayList<LedgerModel> ledgerModelList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.ledgerModelList = ledgerModelList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public LedgerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ledger_list,parent,false);
        return new LedgerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LedgerAdapter.ViewHolder holder, int position) {
        LedgerModel ledgerModel = ledgerModelList.get(position);
        holder.ledgerDate.setText(ledgerModel.getLedgerDate());
        holder.ledgerType.setText(ledgerModel.getLedgerType());
        holder.ledgerName.setText(ledgerModel.getLedgerName());
        holder.credit.setText(Integer.toString(ledgerModel.getDebitAmount()));
        holder.balance.setText(Integer.toString(ledgerModel.getBalanceAmount()));
    }

    @Override
    public int getItemCount() {
        return ledgerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView ledgerDate,ledgerType,ledgerName,credit,balance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ledgerDate=itemView.findViewById(R.id.ledgerDate);
            ledgerType=itemView.findViewById(R.id.ledgerType);
            ledgerName=itemView.findViewById(R.id.ledgerName);
            credit=itemView.findViewById(R.id.debitAmount);
            balance=itemView.findViewById(R.id.balanceAmount);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onLedgerItemClick(getAdapterPosition());

        }
    }
    public interface OnItemClickListener {
        void onLedgerItemClick(int position);
    }
}
