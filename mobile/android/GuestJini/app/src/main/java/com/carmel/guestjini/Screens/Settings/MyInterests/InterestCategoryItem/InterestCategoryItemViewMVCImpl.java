package com.carmel.guestjini.Screens.Settings.MyInterests.InterestCategoryItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestRecycleAdapter;

import java.util.List;

public class InterestCategoryItemViewMVCImpl
        extends BaseObservableViewMvc<InterestCategoryItemViewMVC.Listener>
        implements InterestCategoryItemViewMVC, InterestRecycleAdapter.Listener {

    private final TextView txtCategoryName;
    private InterestCategory interestCategory;
    private final InterestRecycleAdapter interestRecycleAdapter;

    public InterestCategoryItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_my_interest_category_item, parent, false));
        txtCategoryName = findViewById(R.id.txtCategoryName);
        RecyclerView interestRecyclerView = findViewById(R.id.lstInterests);
        interestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        interestRecycleAdapter = new InterestRecycleAdapter(this, viewMVCFactory);
        interestRecyclerView.setAdapter(interestRecycleAdapter);
        ImageView btnShowCategories = findViewById(R.id.btnShowCategories);
        btnShowCategories.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void bindInterestCategory(InterestCategory interestCategory) {
        this.interestCategory = interestCategory;
        txtCategoryName.setText(interestCategory.getName());
    }

    @Override
    public void bindInterests(List<Interest> interestList) {
        interestRecycleAdapter.bindInterests(interestList);
    }

    @Override
    public void onInterestClicked(Interest interest, int isInterested) {

    }
}
