package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.ProfileInterestsModel;

public class ProfileInterestsRecyclerAdapter extends RecyclerView.Adapter<ProfileInterestsRecyclerAdapter.ViewHolder>{
    private Context context;
    ArrayList<ProfileInterestsModel> profileInterestsList;
    private OnItemClickListener onItemClickListener;

    public ProfileInterestsRecyclerAdapter(@NonNull Context context, ArrayList<ProfileInterestsModel> myInterestsList, OnItemClickListener onItemClickListener) {
        this.context=context;
        this.profileInterestsList =myInterestsList;
        this.onItemClickListener=onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_interests_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v,onItemClickListener);
        return viewHolder;
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull final ProfileInterestsRecyclerAdapter.ViewHolder holder, int position) {
    final ProfileInterestsModel myInterests1Model=this.profileInterestsList.get(position);
    holder.addInterestsName.setText(myInterests1Model.getAddMainCategory());
    holder.dropDownImage.setImageResource(myInterests1Model.getUpwardDropDownButton());
    holder.outDoorAdventureLayout.setVisibility(View.GONE);
        holder.myInterestRelativeLayout.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    holder.dropDownImage.setImageResource(R.drawable.dropup_icon_mdpi);
                    holder.outDoorAdventureLayout.setVisibility(View.VISIBLE);
                }else {
                    flag=true;
                    holder.dropDownImage.setImageResource(R.drawable.dropdown_icon_mdpi);
                    holder.outDoorAdventureLayout.setVisibility(View.GONE);

                }
            }
        });

        holder.hikingUncheckedCheckBox.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag = false;
                    holder.hikingUncheckedCheckBox.setImageResource(R.drawable.checked_icon_xhdpi);
                } else {
                    flag=true;
                    holder.hikingUncheckedCheckBox.setImageResource(R.drawable.unchecked_icon_xxhdpi);
                }

            }
        });
        holder.bikingUncheckedCheckBox.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag = false;
                    holder.bikingUncheckedCheckBox.setImageResource(R.drawable.checked_icon_xhdpi);
                } else {
                    flag=true;
                    holder.bikingUncheckedCheckBox.setImageResource(R.drawable.unchecked_icon_xxhdpi);
                }

            }
        });
        holder.cyclingUncheckedCheckBox.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag = false;
                    holder.cyclingUncheckedCheckBox.setImageResource(R.drawable.checked_icon_xhdpi);
                } else {
                    flag=true;
                    holder.cyclingUncheckedCheckBox.setImageResource(R.drawable.unchecked_icon_xxhdpi);
                }

            }
        });
        holder.campingUncheckedCheckBox.setOnClickListener(new View.OnClickListener() {
            private boolean flag=true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag = false;
                    holder.campingUncheckedCheckBox.setImageResource(R.drawable.checked_icon_xhdpi);
                } else {
                    flag=true;
                    holder.campingUncheckedCheckBox.setImageResource(R.drawable.unchecked_icon_xxhdpi);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return profileInterestsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView addInterestsName;
        ImageView dropDownImage, hikingUncheckedCheckBox,bikingUncheckedCheckBox,cyclingUncheckedCheckBox,campingUncheckedCheckBox;
        OnItemClickListener onItemClickListener;
        RelativeLayout myInterestRelativeLayout;
        ConstraintLayout outDoorAdventureLayout;
        public ViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            addInterestsName=itemView.findViewById(R.id.mainCategory);
            dropDownImage=itemView.findViewById(R.id.dropDownImage);
            dropDownImage.setImageResource(R.drawable.dropdown_icon_mdpi);
            outDoorAdventureLayout=itemView.findViewById(R.id.adventureLayout);
            hikingUncheckedCheckBox =itemView.findViewById(R.id.hikingCheckbox);
            bikingUncheckedCheckBox =itemView.findViewById(R.id.bikingCheckBox);
            cyclingUncheckedCheckBox =itemView.findViewById(R.id.cyclingCheckbox);
            campingUncheckedCheckBox =itemView.findViewById(R.id.campingCheckbox);
            myInterestRelativeLayout=itemView.findViewById(R.id.myInterestsRelativeLayout);
            this.onItemClickListener=onItemClickListener;
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
