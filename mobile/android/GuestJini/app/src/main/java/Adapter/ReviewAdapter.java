package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Models.Ticket.KBReview;
import com.carmel.guestjini.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context context;
    ArrayList<KBReview> reviewModels;

    public ReviewAdapter(Context context, ArrayList<KBReview> reviewModelArrayList) {
        this.context = context;
        this.reviewModels = reviewModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KBReview kbReview = this.reviewModels.get(position);
        holder.reviewerName.setText(String.valueOf(kbReview.getReviewByName()));
        //TODO: Format Date
        holder.reviewerDate.setText(String.valueOf(kbReview.getCreationTime()));
        holder.reviewDescription.setText(String.valueOf(kbReview.getReviewComment()));
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public void update(List<KBReview> kbReviewList) {
        this.reviewModels = new ArrayList<>();
        this.reviewModels.addAll(kbReviewList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reviewerName, reviewerDate, reviewDescription;
        ImageView revierProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewerName = itemView.findViewById(R.id.reviewerName);
            reviewerDate = itemView.findViewById(R.id.reviewerDate);
            reviewDescription = itemView.findViewById(R.id.reviewDescription);
            revierProfile = itemView.findViewById(R.id.reviewProfilePicture);
            revierProfile.setImageResource(R.drawable.profile_image);
        }
    }
}
