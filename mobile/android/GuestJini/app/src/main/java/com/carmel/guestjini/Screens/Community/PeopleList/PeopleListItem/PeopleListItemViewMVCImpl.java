package com.carmel.guestjini.Screens.Community.PeopleList.PeopleListItem;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Users.Person;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleListItemViewMVCImpl
        extends BaseObservableViewMvc<PeopleListItemViewMVC.Listener>
        implements PeopleListItemViewMVC {

    private final CircleImageView peopleProfileImage;
    private final TextView peopleName;
    private final TextView peopleGender;
    private final TextView compatibilityMeter;
    private final TextView compatibilityCount;
    private final ImageView btnFavourite;

    private Person person;

    public PeopleListItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_community_people_list_item, parent, false));
        peopleProfileImage = findViewById(R.id.peopleProfileImage);
        peopleName = findViewById(R.id.peopleName);
        peopleGender = findViewById(R.id.peopleGender);
        compatibilityMeter = findViewById(R.id.compatibilityMeter);
        compatibilityCount = findViewById(R.id.compatibilityCount);
        btnFavourite = findViewById(R.id.btnFavourite);
        getRootView().setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onPersonClicked(person);
            }
        });

        btnFavourite.setOnClickListener(v -> {

            int isFavourite = 0;
            if (person.getIsFavourite() != 0) {
                isFavourite = 1;
            }
            for (Listener listener : getListeners()) {
                listener.onFavouriteClicked(person, isFavourite);
            }
        });
    }

    @Override
    public void bindPerson(Person person, int totalInterestCount) {
        this.person = person;
        peopleName.setText(person.getAddressBook().getDisplayName());
        peopleGender.setVisibility(View.GONE);
        if (person.getIsFavourite() == 0) {
            btnFavourite.setImageResource(R.drawable.favourite_select_icon);
        } else {
            btnFavourite.setImageResource(R.drawable.favourite_selected_icon);
        }
        setCompatibilityMeter(totalInterestCount, person.getUserInterestsList().size());
    }

    private void setCompatibilityMeter(int totalCount, int currentCount) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        String stCompatibilityCount = String.valueOf(totalCount) + "/" + String.valueOf(currentCount);
        compatibilityCount.setText(stCompatibilityCount);
        if (totalCount > 0) {
            width = width - (84 - 47);
            Double widthPercent = (Double.parseDouble(String.valueOf(currentCount)) / Double.parseDouble(String.valueOf(totalCount)));
            Double calcWidth = Double.parseDouble(String.valueOf(width)) * (widthPercent);
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) compatibilityMeter.getLayoutParams();
            params.width = calcWidth.intValue();
            compatibilityMeter.setLayoutParams(params);
        }
        compatibilityMeter.post(new Runnable() {
            @Override
            public void run() {
                int width = compatibilityMeter.getWidth();
            }
        });
    }
}
