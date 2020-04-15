package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.ReceiptsModel;

public class ReceiptsAdapter extends RecyclerView.Adapter<ReceiptsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ReceiptsModel> receiptsModelList;
    private OnItemClickListener onItemClickListener;

    public ReceiptsAdapter(Context context, ArrayList<ReceiptsModel> receiptsModelList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.receiptsModelList = receiptsModelList;
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.receipts_list,parent,false);
        return new ReceiptsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReceiptsModel receiptsModel=receiptsModelList.get(position);
        holder.receiptsDate.setText(receiptsModel.getReceiptsDate());
        holder.receiptsNo.setText(receiptsModel.getReceiptsNo());
        holder.receptsAmount.setText(receiptsModel.getAmount());
        holder.reciptsCreationDateTime.setText(receiptsModel.getReceptsCreatedDateTime());
        holder.rightNavigationIcon.setImageResource(receiptsModel.getNavigationIcon());
    }

    @Override
    public int getItemCount() {
        return receiptsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView receiptsDate,receiptsNo,receptsAmount,reciptsCreationDateTime;
        private ImageView rightNavigationIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiptsDate=itemView.findViewById(R.id.receiptsDate);
            receiptsNo=itemView.findViewById(R.id.receiptsNo);
            receptsAmount=itemView.findViewById(R.id.amount);
            reciptsCreationDateTime=itemView.findViewById(R.id.createdDateTime);
            rightNavigationIcon=itemView.findViewById(R.id.navigateRight);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onReceiptsClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        void onReceiptsClick(int position);
    }
}
