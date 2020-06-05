package com.carmel.guestjini.Screens.Support.KBDetail;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.KBDetail.KBReviewItem.KBReviewItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class KBReviewRecyclerAdapter
        extends RecyclerView.Adapter<KBReviewRecyclerAdapter.KBReviewViewHolder>
        implements KBReviewItemViewMVC.Listener {
    public interface Listener {
    }

    static class KBReviewViewHolder extends RecyclerView.ViewHolder {
        private final KBReviewItemViewMVC viewMVC;

        public KBReviewViewHolder(@NonNull KBReviewItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<KBReview> kbReviewList = new ArrayList<>();

    public KBReviewRecyclerAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindKBReviews(List<KBReview> kbReviewList) {
        this.kbReviewList = new ArrayList<>(kbReviewList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KBReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KBReviewItemViewMVC viewMvc = viewMVCFactory.getKBReviewItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new KBReviewViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull KBReviewViewHolder holder, int position) {
        holder.viewMVC.bindReviewItem(this.kbReviewList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.kbReviewList.size();
    }
}
