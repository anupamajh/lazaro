package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.carmel.guestjini.R;
import java.util.ArrayList;
import Model.BillsModel;

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BillsModel> billsModelList;
    private OnItemClickListener onItemClickListener;

    public BillsAdapter(Context context, ArrayList<BillsModel> billsArrayList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.billsModelList = billsArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public BillsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bills_itemlist,parent,false);
        return new BillsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillsAdapter.ViewHolder holder, int position) {
        BillsModel billsModel=billsModelList.get(position);
        holder.billsDate.setText(billsModel.getBillsDate());
        holder.billsNo.setText(billsModel.getBillsNo());
        holder.productName.setText(billsModel.getProductName());
        holder.productAmount.setText(billsModel.getProductAmount());
        holder.rightNavigationIcon.setImageResource(billsModel.getRightNavigationIcon());
    }

    @Override
    public int getItemCount() {
        return billsModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView billsDate,billsNo,productAmount,productName;
        private ImageView rightNavigationIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            billsDate=itemView.findViewById(R.id.billsDate);
            billsNo=itemView.findViewById(R.id.billsNo);
            productName=itemView.findViewById(R.id.productName);
            productAmount=itemView.findViewById(R.id.productAmount);
            rightNavigationIcon=itemView.findViewById(R.id.billsNavigationIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onBillsDetailsClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        void onBillsDetailsClick(int position);
    }
}
