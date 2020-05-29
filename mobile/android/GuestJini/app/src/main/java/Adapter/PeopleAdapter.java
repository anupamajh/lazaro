package Adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Models.User.PeopleResponse;
import com.carmel.guestjini.Models.User.Person;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private ArrayList<Person> personArrayList;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private PeopleResponse peopleResponse;

    public PeopleAdapter(ArrayList<Person> personArrayList, OnItemClickListener onItemClickListener, Context context) {
        this.personArrayList = personArrayList;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_recycler_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.ViewHolder holder, int position) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        Person person = this.personArrayList.get(position);
        holder.addPeopleName.setText(String.valueOf(person.getAddressBook().getDisplayName()));
        holder.addPeopleGender.setVisibility(View.GONE);
        int totalInterestCount = 0;
        if (peopleResponse != null) {
            totalInterestCount = peopleResponse.getTotalInterestCount();
        }
        int currentCount = person.getUserInterestsList().size();
        String compatibilityCount = String.valueOf(currentCount) + "/" + String.valueOf(totalInterestCount);
        if (totalInterestCount > 0) {
            width = width - (84 - 47);
            Double widthPercent = (Double.parseDouble(String.valueOf(currentCount)) / Double.parseDouble(String.valueOf(totalInterestCount)));
            Double calcWidth = Double.parseDouble(String.valueOf(width)) * (widthPercent);
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) holder.compatibilityMeter.getLayoutParams();
            params.width = calcWidth.intValue();
            holder.compatibilityMeter.setLayoutParams(params);
        }
        holder.compatibilityMeter.post(new Runnable() {
            @Override
            public void run() {
                int width = holder.compatibilityMeter.getWidth();
            }
        });
        //holder.addPeopleGender.setText(genderText);
        //holder.peopleProfileImage.setImageResource(person.getProfilePicture());//TODO: Load Persons Profile pic
        if (person.getIsFavourite() == 0) {
            holder.favouritesIcon.setImageResource(R.drawable.unlike_icon_xhdpi);
        } else {
            holder.favouritesIcon.setImageResource(R.drawable.like_icon_xhdpi);
        }
        holder.compatibilityCount.setText(compatibilityCount);
        //holder.notificationIndicator.setBackgroundResource(peopleModel.getNotificationIndicator());


    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public void filterList(ArrayList<Person> filterList, PeopleResponse peopleResponse) {
        this.peopleResponse = peopleResponse;
        personArrayList = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView addPeopleName, addPeopleGender, compatibilityCount, notificationIndicator, compatibilityMeter;
        ImageView favouritesIcon;
        CircleImageView peopleProfileImage;
        private OnItemClickListener onClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            addPeopleName = itemView.findViewById(R.id.peopleName);
            addPeopleGender = itemView.findViewById(R.id.peopleGender);
            compatibilityCount = itemView.findViewById(R.id.compatibilityCount);
            peopleProfileImage = itemView.findViewById(R.id.peopleProfileImage);
            favouritesIcon = itemView.findViewById(R.id.unlike_blue_image);
            notificationIndicator = itemView.findViewById(R.id.notificationIndicator);
            compatibilityMeter = itemView.findViewById(R.id.compatibilityMeter);
            this.onClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            favouritesIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favouritesIcon.setActivated(!favouritesIcon.isActivated());
                }
            });

        }

        @Override
        public void onClick(View v) {
            onClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
