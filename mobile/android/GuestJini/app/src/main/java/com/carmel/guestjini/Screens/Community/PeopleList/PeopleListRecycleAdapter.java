package com.carmel.guestjini.Screens.Community.PeopleList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Networking.Users.Person;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Community.PeopleList.PeopleListItem.PeopleListItemViewMVC;

import java.util.ArrayList;

public class PeopleListRecycleAdapter
        extends RecyclerView.Adapter<PeopleListRecycleAdapter.PeopleHolder>
        implements PeopleListItemViewMVC.Listener {

    public interface Listener {
        void onPersonClicked(Person person);

        void onFavouriteClicked(Person person, int isFavourite);
    }


    static class PeopleHolder extends RecyclerView.ViewHolder {
        private final PeopleListItemViewMVC viewMVC;

        public PeopleHolder(@NonNull PeopleListItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<Person> personArrayList = new ArrayList<>();
    private PeopleResponse peopleResponse;

    public PeopleListRecycleAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindPeopleResponse(PeopleResponse peopleResponse) {
        this.peopleResponse = peopleResponse;
        this.personArrayList = new ArrayList<>(peopleResponse.getPersonList());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeopleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PeopleListItemViewMVC viewMvc = viewMVCFactory.getPeopleListItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new PeopleHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleHolder holder, int position) {
        holder.viewMVC.bindPerson(this.personArrayList.get(position), peopleResponse.getTotalInterestCount());
    }

    @Override
    public int getItemCount() {
        return this.personArrayList.size();
    }

    @Override
    public void onPersonClicked(Person person) {
        listener.onPersonClicked(person);
    }

    @Override
    public void onFavouriteClicked(Person person, int isFavourite) {
        listener.onFavouriteClicked(person, isFavourite);
    }
}
