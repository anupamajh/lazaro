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

import Model.BillDetailsModel;

public class BillDetailsAdapter extends RecyclerView.Adapter<BillDetailsAdapter.ViewHolder> {
    private ArrayList<BillDetailsModel> billsDetailsList;
    private Context context;
    public BillDetailsAdapter(Context context, ArrayList<BillDetailsModel> billsDetailsArrayList) {
        this.context=context;
        this.billsDetailsList=billsDetailsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bills_details_list,parent,false);
        return new BillDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillDetailsModel billDetailsModel=billsDetailsList.get(position);
        holder.itemsName.setText(billDetailsModel.getItemsName());
        holder.itemsRate.setText(Integer.toString(billDetailsModel.getItemsRate()));
        holder.itemsQuantity.setText(Integer.toString(billDetailsModel.getItemsQuantity()));
        holder.itemsAmount.setText(Integer.toString(billDetailsModel.getItemsAmount()));

    }

    @Override
    public int getItemCount() {
        return billsDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemsName,itemsRate,itemsQuantity,itemsAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemsName=itemView.findViewById(R.id.itemsName);
            itemsRate=itemView.findViewById(R.id.itemsRate);
            itemsQuantity=itemView.findViewById(R.id.itemsQuantity);
            itemsAmount=itemView.findViewById(R.id.itemsAmount);
        }
    }

}
