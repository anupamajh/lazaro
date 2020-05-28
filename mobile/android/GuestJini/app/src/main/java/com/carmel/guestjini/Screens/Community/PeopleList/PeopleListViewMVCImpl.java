package com.carmel.guestjini.Screens.Community.PeopleList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Networking.Users.Person;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class PeopleListViewMVCImpl
        extends BaseObservableViewMvc<PeopleListViewMVC.Listener>
        implements PeopleListViewMVC,
        PeopleListRecycleAdapter.Listener {

    private final PeopleListRecycleAdapter peopleListRecycleAdapter;
    private final ProgressBar progressBar;

    public PeopleListViewMVCImpl(LayoutInflater inflater,
                             @Nullable ViewGroup parent,
                             ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_people_list, parent, false));
        RecyclerView peopleRecyclerView = findViewById(R.id.lstPeople);
        ImageView btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progress);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        peopleListRecycleAdapter = new PeopleListRecycleAdapter(this, viewMVCFactory);
        peopleRecyclerView.setAdapter(peopleListRecycleAdapter);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });
    }

    @Override
    public void onPersonClicked(Person person) {
        for (Listener listener : getListeners()) {
            listener.onPersonClicked(person);
        }
    }

    @Override
    public void onFavouriteClicked(Person person, int isFavourite) {
        for (Listener listener : getListeners()) {
            listener.onFavouriteClicked(person, isFavourite);
        }
    }

    @Override
    public void bindPeopleResponse(PeopleResponse peopleResponse) {
        peopleListRecycleAdapter.bindPeopleResponse(peopleResponse);
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }
}
