package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.GroupChatModel;
import Model.InterestGroupsModel;

import static Model.InterestGroupsModel.FOURTH_TYPE;
import static Model.InterestGroupsModel.THIRD_TYPE;
import static Model.MyTicketsModel.ONE_TYPE;
import static Model.MyTicketsModel.TWO_TYPE;

public class InterestGroupsAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<InterestGroupsModel> modelArrayList;
    private OnItemClickListener onItemClickListener;
    public InterestGroupsAdpater(ArrayList<InterestGroupsModel> interestGroupsList,OnItemClickListener onItemClickListener) {
        this.modelArrayList=interestGroupsList;
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public int getItemViewType(int position) {
        InterestGroupsModel interestGroupsModel=modelArrayList.get(position);
        if(interestGroupsModel!=null){
            return interestGroupsModel.getViewType();
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case ONE_TYPE:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subscribed_interest_group_container,parent,false);
                return new InterestGroupsAdpater.OneViewHolder(view,onItemClickListener);

            case TWO_TYPE:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.subscribed_read_interest_group_cell,parent,false);
                return new InterestGroupsAdpater.SecondViewHolder(view);
            case THIRD_TYPE:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.unsubscribed_interest_group_container,parent,false);
                return new InterestGroupsAdpater.ThirdViewHolder(view,onItemClickListener);

            case FOURTH_TYPE:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.unsubscribed_interest_group_does_not_match_cell,parent,false);
                return new InterestGroupsAdpater.FourthViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InterestGroupsModel interestGroupsModel=modelArrayList.get(position);
        switch (interestGroupsModel.getViewType()) {
            case ONE_TYPE:
                ((OneViewHolder)holder).interestCategoryTitle.setText(interestGroupsModel.getAddInterestCategoryTitle());
                ((OneViewHolder)holder).interestGroupTitle.setText(interestGroupsModel.getAddInterestGroupTitle());
                ((OneViewHolder)holder).interestGroupDescription.setText(interestGroupsModel.getAddInterestGroupDescription());
                break;
            case TWO_TYPE:
                ((SecondViewHolder)holder).interestCategoryTitle.setText(interestGroupsModel.getAddInterestCategoryTitle());
                ((SecondViewHolder)holder).interestGroupTitle.setText(interestGroupsModel.getAddInterestGroupTitle());
                ((SecondViewHolder)holder).interestGroupDescription.setText(interestGroupsModel.getAddInterestGroupDescription());
                break;
            case THIRD_TYPE:
                ((ThirdViewHolder)holder).interestCategoryTitle.setText(interestGroupsModel.getAddInterestCategoryTitle());
                ((ThirdViewHolder)holder).interestGroupTitle.setText(interestGroupsModel.getAddInterestGroupTitle());
                ((ThirdViewHolder)holder).interestGroupDescription.setText(interestGroupsModel.getAddInterestGroupDescription());
                break;
            case FOURTH_TYPE:
                ((FourthViewHolder)holder).interestCategoryTitle.setText(interestGroupsModel.getAddInterestCategoryTitle());
                ((FourthViewHolder)holder).interestGroupTitle.setText(interestGroupsModel.getAddInterestGroupTitle());
                ((FourthViewHolder)holder).interestGroupDescription.setText(interestGroupsModel.getAddInterestGroupDescription());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView interestCategoryTitle,interestGroupTitle,interestGroupDescription;
        OnItemClickListener onItemClickListener;
         public OneViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
             super(itemView);
             interestCategoryTitle=itemView.findViewById(R.id.subscribedInterestCategoryTitle);
             interestGroupTitle=itemView.findViewById(R.id.subscribedInterestGroupTitle);
             interestGroupDescription=itemView.findViewById(R.id.interestLargeDescription);
             this.onItemClickListener=onItemClickListener;
             itemView.setOnClickListener(this);
         }
        @Override
        public void onClick(View v) {
            onItemClickListener.onclickSubscribeGroup(getAdapterPosition());
        }
     }
    class SecondViewHolder extends RecyclerView.ViewHolder{
        TextView interestCategoryTitle,interestGroupTitle,interestGroupDescription;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            interestCategoryTitle=itemView.findViewById(R.id.techTitle);
            interestGroupTitle=itemView.findViewById(R.id.textView4);
            interestGroupDescription=itemView.findViewById(R.id.roboticsLargeDescription);
        }
    }
    class ThirdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView interestCategoryTitle,interestGroupTitle,interestGroupDescription,indicatorDescription;
        ImageView indicators,informationIcons;
        OnItemClickListener onItemClickListener;
        public ThirdViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            interestCategoryTitle=itemView.findViewById(R.id.interestCategoryTitle);
            interestGroupTitle=itemView.findViewById(R.id.interestGroupTitle);
            interestGroupDescription=itemView.findViewById(R.id.interestGroupDescription);
            indicatorDescription=itemView.findViewById(R.id.indicatorSmallDescription);
            indicators=itemView.findViewById(R.id.interestGroupIndicator);
            informationIcons=itemView.findViewById(R.id.informationIcon);
            indicators.setImageResource(R.drawable.indicator_icon);
            informationIcons.setImageResource(R.drawable.information_icon);
            this.onItemClickListener=onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onclickUnsubscribeGroup(getAdapterPosition());
        }
    }
    class FourthViewHolder extends RecyclerView.ViewHolder {
        TextView interestCategoryTitle,interestGroupTitle,interestGroupDescription;
        ImageView indicators,informationIcons;
        public FourthViewHolder(@NonNull View itemView) {
            super(itemView);
            interestCategoryTitle=itemView.findViewById(R.id.outdoorTitle);
            interestGroupTitle=itemView.findViewById(R.id.skyDivingTitle);
            interestGroupDescription=itemView.findViewById(R.id.skyDivingLargeDescription);
            informationIcons=itemView.findViewById(R.id.circleIcon);

        }

    }
    public interface OnItemClickListener{
        void onclickUnsubscribeGroup(int position);
        void onclickSubscribeGroup(int position);

    }
}
