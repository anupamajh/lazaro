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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.TicketsModel;


public class ExploreTicketsAdapter extends RecyclerView.Adapter<ExploreTicketsAdapter.ViewHolder> {
    private Context context;
   private ArrayList<TicketsModel> ticketsModelArrayList;
    private OnItemClickListener onItemClickListener;
    public ExploreTicketsAdapter(Context context, ArrayList<TicketsModel> ticketsModelsList,OnItemClickListener onItemClickListener) {
        this.context=context;
        this.ticketsModelArrayList=ticketsModelsList;
        this.onItemClickListener=onItemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v,onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreTicketsAdapter.ViewHolder holder, int position) {
        final TicketsModel ticketsModel=this.ticketsModelArrayList.get(position);
        holder.ticketsName.setText(String.valueOf(ticketsModel.getTicketsName()));
        holder.ticketsDate.setText(String.valueOf(ticketsModel.getTicketsDate()));
        holder.ticketsAuthorName.setText(String.valueOf(ticketsModel.getTicketsAuthorName()));
        holder.ticketsDescription.setText(String.valueOf(ticketsModel.getTicketsDescription()));
        holder.navigationIcon.setImageResource(ticketsModel.getNavigationIcon());
    }


    @Override
    public int getItemCount() {
        return ticketsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView profilePicture;
        FloatingActionButton navigationIcon;
        TextView ticketsName,ticketsDate,ticketsAuthorName,ticketsDescription;
        OnItemClickListener onItemClickListener;
        ConstraintLayout exploreListLayout;
        public ViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            profilePicture=itemView.findViewById(R.id.profilePicture);
            navigationIcon=itemView.findViewById(R.id.navigationIcon);
            ticketsName=itemView.findViewById(R.id.ticketName);
            ticketsDate=itemView.findViewById(R.id.ticketDate);
            ticketsAuthorName=itemView.findViewById(R.id.ticketAuthorName);
            ticketsDescription=itemView.findViewById(R.id.ticketDescription);
            profilePicture.setImageResource(R.drawable.profile_image);
            exploreListLayout=itemView.findViewById(R.id.exploreListLayout);
            this.onItemClickListener=onItemClickListener;
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
