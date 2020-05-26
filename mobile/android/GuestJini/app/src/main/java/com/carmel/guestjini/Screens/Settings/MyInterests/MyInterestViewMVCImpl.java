package com.carmel.guestjini.Screens.Settings.MyInterests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

public class MyInterestViewMVCImpl
        extends BaseObservableViewMvc<MyInterestViewMVC.Listener>
        implements MyInterestViewMVC,
        InterestCategoryRecycleAdapter.Listener {

    private final ProgressBar progressBar;
    private final InterestCategoryRecycleAdapter interestCategoryRecycleAdapter;

    public MyInterestViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_my_interests, parent, false));
        RecyclerView interestCategoryRecyclerView = findViewById(R.id.lstInterestCategories);
        progressBar = findViewById(R.id.progress);
        interestCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        interestCategoryRecycleAdapter = new InterestCategoryRecycleAdapter(this, viewMVCFactory);
        interestCategoryRecyclerView.setAdapter(interestCategoryRecycleAdapter);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });
    }

    @Override
    public void bindInterestCategories(List<InterestCategory> interestCategoryList) {
        interestCategoryRecycleAdapter.bindInterestCategories(interestCategoryList);
    }

    @Override
    public void bindInterests(List<Interest> interestList) {
        interestCategoryRecycleAdapter.bindInterests(interestList);
    }

    @Override
    public void bindUserInterests(List<UserInterests> userInterests) {
        //TODO: See what you have to do here
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onInterestCategoryClicked(InterestCategory interestCategory) {
        //TODO: Do i need this?
    }
}
