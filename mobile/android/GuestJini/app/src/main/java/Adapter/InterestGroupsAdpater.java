package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Models.Group.Group;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import static Model.InterestGroupsModel.FOURTH_TYPE;
import static Model.InterestGroupsModel.THIRD_TYPE;
import static Model.MyTicketsModel.ONE_TYPE;
import static Model.MyTicketsModel.TWO_TYPE;

public class InterestGroupsAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Group> groupArrayList;
    private OnItemClickListener onItemClickListener;

    public InterestGroupsAdpater(ArrayList<Group> groupArrayList, OnItemClickListener onItemClickListener) {
        this.groupArrayList = groupArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        Group group = groupArrayList.get(position);
        int viewType = getViewType(group);
        if (group != null) {
            return viewType;
        }
        return 0;
    }

    private int getViewType(Group group) {
        int viewType = 0;
        if (group.getIsSubscribed() == 1) {
            viewType = ONE_TYPE;
        } else {
            if (group.getIsMatchingInterest() == 0) {
                viewType = TWO_TYPE;
            } else {
                viewType = THIRD_TYPE;
            }
        }
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ONE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscribed_interest_group_container, parent, false);
                return new InterestGroupsAdpater.OneViewHolder(view, onItemClickListener);

            case TWO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscribed_read_interest_group_cell, parent, false);
                return new InterestGroupsAdpater.SecondViewHolder(view);
            case THIRD_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unsubscribed_interest_group_container, parent, false);
                return new InterestGroupsAdpater.ThirdViewHolder(view, onItemClickListener);

            case FOURTH_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unsubscribed_interest_group_does_not_match_cell, parent, false);
                return new InterestGroupsAdpater.FourthViewHolder(view);

        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Group group = groupArrayList.get(position);
        int viewType = getViewType(group);
        switch (viewType) {
            case ONE_TYPE:
                ((OneViewHolder) holder).interestCategoryTitle.setText(group.getInterestCategoryName());
                ((OneViewHolder) holder).interestGroupTitle.setText(group.getName());
                ((OneViewHolder) holder).interestGroupDescription.setVisibility(View.GONE);
                break;
            case TWO_TYPE:
                ((SecondViewHolder) holder).interestCategoryTitle.setText(group.getInterestCategoryName());
                ((SecondViewHolder) holder).interestGroupTitle.setText(group.getName());
                ((SecondViewHolder) holder).interestGroupDescription.setVisibility(View.GONE);
                break;
            case THIRD_TYPE:
                ((ThirdViewHolder) holder).interestCategoryTitle.setText(group.getInterestCategoryName());
                ((ThirdViewHolder) holder).interestGroupTitle.setText(group.getName());
                ((ThirdViewHolder) holder).interestGroupDescription.setVisibility(View.GONE);
                break;
            case FOURTH_TYPE:
                ((FourthViewHolder) holder).interestCategoryTitle.setText(group.getInterestCategoryName());
                ((FourthViewHolder) holder).interestGroupTitle.setText(group.getName());
                ((FourthViewHolder) holder).interestGroupDescription.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }

    public void update(ArrayList<Group> groupArrayList) {
        this.groupArrayList = groupArrayList;
        notifyDataSetChanged();
    }

    class OneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView interestCategoryTitle, interestGroupTitle, interestGroupDescription;
        OnItemClickListener onItemClickListener;

        public OneViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            interestCategoryTitle = itemView.findViewById(R.id.subscribedInterestCategoryTitle);
            interestGroupTitle = itemView.findViewById(R.id.subscribedInterestGroupTitle);
            interestGroupDescription = itemView.findViewById(R.id.interestLargeDescription);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onclickSubscribeGroup(getAdapterPosition());
        }
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        TextView interestCategoryTitle, interestGroupTitle, interestGroupDescription;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            interestCategoryTitle = itemView.findViewById(R.id.techTitle);
            interestGroupTitle = itemView.findViewById(R.id.textView4);
            interestGroupDescription = itemView.findViewById(R.id.roboticsLargeDescription);
        }
    }

    class ThirdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView interestCategoryTitle, interestGroupTitle, interestGroupDescription, indicatorDescription;
        ImageView indicators, informationIcons;
        OnItemClickListener onItemClickListener;

        public ThirdViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            interestCategoryTitle = itemView.findViewById(R.id.interestCategoryTitle);
            interestGroupTitle = itemView.findViewById(R.id.interestGroupTitle);
            interestGroupDescription = itemView.findViewById(R.id.interestGroupDescription);
            indicatorDescription = itemView.findViewById(R.id.indicatorSmallDescription);
            indicators = itemView.findViewById(R.id.interestGroupIndicator);
            informationIcons = itemView.findViewById(R.id.informationIcon);
            indicators.setImageResource(R.drawable.indicator_icon);
            informationIcons.setImageResource(R.drawable.information_icon);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onclickUnsubscribeGroup(getAdapterPosition());
        }
    }

    class FourthViewHolder extends RecyclerView.ViewHolder {
        TextView interestCategoryTitle, interestGroupTitle, interestGroupDescription;
        ImageView indicators, informationIcons;

        public FourthViewHolder(@NonNull View itemView) {
            super(itemView);
            interestCategoryTitle = itemView.findViewById(R.id.outdoorTitle);
            interestGroupTitle = itemView.findViewById(R.id.skyDivingTitle);
            interestGroupDescription = itemView.findViewById(R.id.skyDivingLargeDescription);
            informationIcons = itemView.findViewById(R.id.circleIcon);

        }

    }

    public interface OnItemClickListener {
        void onclickUnsubscribeGroup(int position);

        void onclickSubscribeGroup(int position);

    }
}
