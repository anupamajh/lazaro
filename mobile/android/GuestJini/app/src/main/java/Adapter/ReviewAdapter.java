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

import Model.ReviewModel;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context context;
    ArrayList<ReviewModel> reviewModels;
    public ReviewAdapter(Context context, ArrayList<ReviewModel> reviewModelArrayList) {
        this.context=context;
        this.reviewModels=reviewModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewModel reviewModel=this.reviewModels.get(position);
        holder.reviewerName.setText(String.valueOf(reviewModel.getReviewerName()));
        holder.reviewerDate.setText(String.valueOf(reviewModel.getReviewDate()));
        holder.reviewDescription.setText(String.valueOf(reviewModel.getReviewDescription()));

    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reviewerName,reviewerDate,reviewDescription;
        ImageView revierProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewerName=itemView.findViewById(R.id.reviewerName);
            reviewerDate=itemView.findViewById(R.id.reviewerDate);
            reviewDescription=itemView.findViewById(R.id.reviewDescription);
            revierProfile=itemView.findViewById(R.id.reviewProfilePicture);
            revierProfile.setImageResource(R.drawable.profile_image);
        }
    }
}
