package com.carmel.guestjini.Screens.Settings.MyInterests;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.carmel.guestjini.Networking.Users.Interest;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestItem.InterestItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class InterestListAdapter extends BaseAdapter {

    public interface Listener {
        void onInterestClicked(Interest interest, int isInterested);
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;
    private final InterestItemViewMVC viewMVC;

    private ArrayList<Interest> interests = new ArrayList<>();

    public InterestListAdapter(
            Listener listener,
            ViewMVCFactory viewMVCFactory
    ) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
        this.viewMVC = viewMVCFactory.getInterestItemViewMVC(null);
    }

    public void setInterests(List<Interest> interests){
        this.interests = new ArrayList<>(interests);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return interests.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewMVC.bindInterest(interests.get(position));
        return viewMVC.getRootView();
    }
}
