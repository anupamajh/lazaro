package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Models.Accounts.AccountTicket;
import com.carmel.guestjini.R;

import java.util.ArrayList;
import java.util.List;

import Model.RentInvoiceModel;

public class RentInvoiceAdapter extends RecyclerView.Adapter<RentInvoiceAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AccountTicket> rentInvoiceList;
    private OnItemClickListener onItemClickListener;
    public RentInvoiceAdapter(Context context, ArrayList<AccountTicket> rentInvoiceList,OnItemClickListener onItemClickListener) {
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
        AccountTicket accountTicket=rentInvoiceList.get(position);
        holder.rentInvoiceDate.setText(accountTicket.getCreationTime());//TODO: Format Date
        holder.rentInvoiceNo.setText(accountTicket.getTicketNumber());
        holder.rentInvoiceAmount.setText(String.valueOf(accountTicket.getNetTotal()));
        holder.rightNavigationIcon.setImageResource(R.drawable.navigation_icon_xhdpi);
    }

    @Override
    public int getItemCount() {
        return rentInvoiceList.size();
    }

    public void update(List<AccountTicket> accountTicketList) {
        rentInvoiceList = new ArrayList<>();
        rentInvoiceList.addAll(accountTicketList);
        notifyDataSetChanged();
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
