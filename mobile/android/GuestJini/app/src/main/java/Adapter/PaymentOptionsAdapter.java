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

import Model.PaymentOptionsModel;

public class PaymentOptionsAdapter extends RecyclerView.Adapter<PaymentOptionsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PaymentOptionsModel> paymentOptionsList;
    private OnItemClickListener onItemClickListener;

    public PaymentOptionsAdapter(Context context, ArrayList<PaymentOptionsModel> paymentOptionsList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.paymentOptionsList = paymentOptionsList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_options_list,parent,false);
        return new PaymentOptionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaymentOptionsModel paymentOptionsModel=paymentOptionsList.get(position);
        holder.paymentOptionsIcon.setImageResource(paymentOptionsModel.getPaymentOptionsIcon());
        holder.paymentOptionsName.setText(paymentOptionsModel.getPaymentOptionsName());

    }

    @Override
    public int getItemCount() {
        return paymentOptionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView paymentOptionsIcon;
        private TextView paymentOptionsName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentOptionsIcon=itemView.findViewById(R.id.paymentOptionsIcon);
            paymentOptionsName=itemView.findViewById(R.id.paymentOptionsName);
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
