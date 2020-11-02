package com.carmel.guestjini.Screens.Settings.MyInterests;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestCategoryItem.InterestCategoryItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class InterestCategoryRecycleAdapter
        extends RecyclerView.Adapter<InterestCategoryRecycleAdapter.InterestCategoryViewHolder>
        implements InterestCategoryItemViewMVC.Listener {

    public interface Listener {
        void onInterestCategoryClicked(InterestCategory interestCategory);

        void onInterestClicked(Interest interest, int isInterested);
    }

    static class InterestCategoryViewHolder extends RecyclerView.ViewHolder {
        private final InterestCategoryItemViewMVC viewMVC;

        public InterestCategoryViewHolder(@NonNull InterestCategoryItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;
    private InterestCategoryItemViewMVC viewMvc;
    private List<UserInterests> userInterests;

    private ArrayList<InterestCategory> interestCategories = new ArrayList<>();

    public InterestCategoryRecycleAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindInterestCategories(List<InterestCategory> interestCategoryList, List<UserInterests> userInterests) {
        this.userInterests = userInterests;
        this.interestCategories = new ArrayList<>(interestCategoryList);
        notifyDataSetChanged();
    }

    /*public void bindInterests(List<Interest> interestList) {
        viewMvc.bindInterests(interestList);
    }*/

    @NonNull
    @Override
    public InterestCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewMvc = viewMVCFactory.getInterestCategoryItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new InterestCategoryViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestCategoryViewHolder holder, int position) {
        holder.viewMVC.bindInterestCategory(this.interestCategories.get(position),userInterests);
    }

    @Override
    public int getItemCount() {
        return this.interestCategories.size();
    }

    @Override
    public void onInterestCategoryClicked(InterestCategory interestCategory) {
        listener.onInterestCategoryClicked(interestCategory);
    }

    @Override
    public void onInterestClicked(Interest interest, int isInterested) {
        listener.onInterestClicked(interest, isInterested);
    }
}
