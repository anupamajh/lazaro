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

import Model.RentInvoiceModel;

public class RentInvoiceAdapter extends RecyclerView.Adapter<RentInvoiceAdapter.ViewHolder> {

    private Context context;
    private ArrayList<RentInvoiceModel> rentInvoiceList;
    private OnItemClickListener onItemClickListener;
    public RentInvoiceAdapter(Context context, ArrayList<RentInvoiceModel> rentInvoiceList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.rentInvoiceList = rentInvoiceList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rent_invoice_list,parent,false);
        return new RentInvoiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RentInvoiceModel rentInvoiceModel=rentInvoiceList.get(position);
        holder.rentInvoiceDate.setText(rentInvoiceModel.getRentInvoiceDate());
        holder.rentInvoiceNo.setText(rentInvoiceModel.getRentInvoiceNo());
        holder.rentInvoiceAmount.setText(rentInvoiceModel.getRentInvoiceAmount());
        holder.rightNavigationIcon.setImageResource(rentInvoiceModel.getRightNavigationIcon());
    }

    @Override
    public int getItemCount() {
        return rentInvoiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView rentInvoiceDate,rentInvoiceNo,rentInvoiceAmount;
        private ImageView rightNavigationIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rentInvoiceDate=itemView.findViewById(R.id.rentInvoiceDate);
            rentInvoiceNo=itemView.findViewById(R.id.rentInvoiceNo);
            rentInvoiceAmount=itemView.findViewById(R.id.rentInvoiceAmount);
            rightNavigationIcon=itemView.findViewById(R.id.rightNavigationIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
