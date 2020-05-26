package com.carmel.guestjini.Screens.Settings.MyInterests;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestItem.InterestItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class InterestRecycleAdapter
        extends RecyclerView.Adapter<InterestRecycleAdapter.InterestViewHolder>
        implements InterestItemViewMVC.Listener {

    public interface Listener {
        void onInterestClicked(Interest interest, int isInterested);
    }

    static class InterestViewHolder extends RecyclerView.ViewHolder {
        private final InterestItemViewMVC viewMVC;

        public InterestViewHolder(@NonNull InterestItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<Interest> interests = new ArrayList<>();

    public InterestRecycleAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindInterests(List<Interest> interestList) {
        this.interests = new ArrayList<>(interestList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public InterestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InterestItemViewMVC viewMvc = viewMVCFactory.getInterestItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new InterestViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestViewHolder holder, int position) {
        holder.viewMVC.bindInterest(this.interests.get(position));
    }

    @Override
    public int getItemCount() {
        return this.interests.size();
    }

    @Override
    public void onInterestClicked(Interest interest, int isInterested) {
        listener.onInterestClicked(interest, isInterested);
    }
}
