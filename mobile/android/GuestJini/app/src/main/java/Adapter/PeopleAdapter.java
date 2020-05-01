package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Models.User.Person;
import com.carmel.guestjini.R;

import java.util.ArrayList;

import Model.PeopleModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private ArrayList<Person> personArrayList;
    private OnItemClickListener onItemClickListener;
    public PeopleAdapter(ArrayList<Person> personArrayList,OnItemClickListener onItemClickListener) {
        this.personArrayList=personArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.people_recycler_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view,onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.ViewHolder holder, int position) {
        Person person=this.personArrayList.get(position);
        holder.addPeopleName.setText(String.valueOf(person.getAddressBook().getDisplayName()));
        holder.addPeopleGender.setVisibility(View.GONE);
        //holder.addPeopleGender.setText(String.valueOf(person..getAddPeopleGender()));
        //holder.peopleProfileImage.setImageResource(person.getProfilePicture());//TODO: Load Persons Profile pic
        //holder.favouritesIcon.setImageResource(peopleModel.getFavouritesIcon()); //TODO: Set favourite based on
//        holder.compatibilityCount.setText(person.getUserInterestsList().size());
        //holder.notificationIndicator.setBackgroundResource(peopleModel.getNotificationIndicator());


    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public void filterList(ArrayList<Person> filterList) {
        personArrayList = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView addPeopleName,addPeopleGender,compatibilityCount,notificationIndicator;
        ImageView favouritesIcon;
        CircleImageView peopleProfileImage;
        private OnItemClickListener onClickListener;
        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            addPeopleName=itemView.findViewById(R.id.peopleName);
            addPeopleGender=itemView.findViewById(R.id.peopleGender);
            compatibilityCount=itemView.findViewById(R.id.compatibilityCount);
            peopleProfileImage=itemView.findViewById(R.id.peopleProfileImage);
            favouritesIcon=itemView.findViewById(R.id.unlike_blue_image);
            notificationIndicator=itemView.findViewById(R.id.notificationIndicator);
            this.onClickListener=onItemClickListener;
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
