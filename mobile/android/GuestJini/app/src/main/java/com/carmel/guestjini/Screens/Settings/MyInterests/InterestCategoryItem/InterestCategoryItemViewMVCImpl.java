package com.carmel.guestjini.Screens.Settings.MyInterests.InterestCategoryItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestListAdapter;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestRecycleAdapter;

import java.util.List;

public class InterestCategoryItemViewMVCImpl
        extends BaseObservableViewMvc<InterestCategoryItemViewMVC.Listener>
        implements InterestCategoryItemViewMVC, InterestRecycleAdapter.Listener,
        InterestListAdapter.Listener {

    private final TextView txtCategoryName;
    private final RelativeLayout layoutInterestList;
    private final RelativeLayout layoutBtnShowCategories;
    private InterestCategory interestCategory;
    private final TableLayout lstInterests;
    //  private final InterestRecycleAdapter interestRecycleAdapter;
    //private final InterestListAdapter interestListAdapter;
    //private List<Inter>

    private boolean isBinding = true;

    private final LayoutInflater layoutInflater;

    public InterestCategoryItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_settings_my_interest_category_item, parent, false));
        this.layoutInflater = inflater;
        txtCategoryName = findViewById(R.id.txtCategoryName);
        layoutInterestList = findViewById(R.id.layoutInterestList);
        layoutBtnShowCategories = findViewById(R.id.layoutBtnShowCategories);
        lstInterests = findViewById(R.id.lstInterests);
//        ListView interestListView = findViewById(R.id.lstInterests);
//        interestListAdapter = new InterestListAdapter(this, viewMVCFactory);
//        interestListView.setAdapter(interestListAdapter);
//        RecyclerView interestRecyclerView = findViewById(R.id.lstInterests);
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
//        interestRecyclerView.setLayoutManager(layoutManager);
//        interestRecycleAdapter = new InterestRecycleAdapter(this, viewMVCFactory);
//        interestRecyclerView.setAdapter(interestRecycleAdapter);
        ImageView btnShowCategories = findViewById(R.id.btnShowCategories);
        layoutBtnShowCategories.setOnClickListener(new View.OnClickListener() {
            private boolean flag = true;

            @Override
            public void onClick(View view) {
                if (layoutInterestList.getVisibility() == View.VISIBLE) {
                    layoutInterestList.setVisibility(View.GONE);
                    btnShowCategories.setImageResource(R.drawable.dropdown_grey_icon);
                } else {
                    layoutInterestList.setVisibility(View.VISIBLE);
                    btnShowCategories.setImageResource(R.drawable.collapse_grey_icon);
                }
            }
        });
    }

    @Override
    public void bindInterestCategory(InterestCategory interestCategory, List<UserInterests> userInterests) {
        this.interestCategory = interestCategory;
        txtCategoryName.setText(interestCategory.getName() + "(" + interestCategory.getInterestList().size() + ")");
        this.bindInterestRows(interestCategory.getInterestList(), userInterests);
        layoutBtnShowCategories.callOnClick();
        //interestListAdapter.setInterests(interestCategory.getInterestList());
        //interestRecycleAdapter.bindInterests(interestCategory.getInterestList());
    }

    private void bindInterestRows(List<Interest> interests, List<UserInterests> userInterests) {
        lstInterests.removeAllViews();
        for (Interest interest : interests) {
            final View interestRow = layoutInflater.inflate(R.layout.layout_settings_my_interests_item_row, null);
            TextView txtInterestText = interestRow.findViewById(R.id.txtInterestTitle);
            txtInterestText.setText(interest.getName());
            TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            ImageView btnInterested = interestRow.findViewById(R.id.btnInterested);
            btnInterested.setOnClickListener(new View.OnClickListener() {
                private boolean flag = true;
                private int isInterested = 0;

                @Override
                public void onClick(View view) {
                    if (flag) {
                        flag = false;
                        btnInterested.setImageResource(R.drawable.check_icon);
                        isInterested = 1;
                    } else {
                        flag = true;
                        btnInterested.setImageResource(R.drawable.square_box_icon);
                        isInterested = 0;
                    }
                    if(!isBinding) {
                        for (Listener listener : getListeners()) {
                            listener.onInterestClicked(interest, isInterested);
                        }
                    }
                }
            });
            int leftMargin = 3;
            int topMargin = 10;
            int rightMargin = 3;
            int bottomMargin = 2;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            interestRow.setLayoutParams(tableRowParams);
            TableRow tr = (TableRow) interestRow;
            lstInterests.addView(tr);
            for(UserInterests userInterests1:userInterests){
                if(interest.getId().equals(userInterests1.getInterestId())){
                    btnInterested.callOnClick();
                    break;
                }
            }
        }
        isBinding = false;
    }

    @Override
    public void bindInterests(List<Interest> interestList) {
        //interestRecycleAdapter.bindInterests(interestList);
    }

    @Override
    public void onInterestClicked(Interest interest, int isInterested) {

    }
}
