package com.carmel.guestjini.Screens.Support.KBList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Support.KBList.KBListItem.KBListItemViewMVC;

import java.util.ArrayList;
import java.util.List;

public class KBRecyclerAdapter
        extends RecyclerView.Adapter<KBRecyclerAdapter.KBViewHolder>
        implements KBListItemViewMVC.Listener {


    public interface Listener {
        void onKBItemClicked(KB kb);
    }

    static class KBViewHolder extends RecyclerView.ViewHolder {
        private final KBListItemViewMVC viewMVC;

        public KBViewHolder(@NonNull KBListItemViewMVC viewMvc) {
            super(viewMvc.getRootView());
            this.viewMVC = viewMvc;
        }
    }

    private final Listener listener;
    private final ViewMVCFactory viewMVCFactory;

    private ArrayList<KB> kbList = new ArrayList<>();

    public KBRecyclerAdapter(Listener listener, ViewMVCFactory viewMVCFactory) {
        this.listener = listener;
        this.viewMVCFactory = viewMVCFactory;
    }

    public void bindKB(List<KB> kbList) {
        this.kbList = new ArrayList<>(kbList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KBListItemViewMVC viewMvc = viewMVCFactory.getKBListItemViewMVC(parent);
        viewMvc.registerListener(this);
        return new KBViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull KBViewHolder holder, int position) {
        holder.viewMVC.bindKB(this.kbList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.kbList.size();
    }

    @Override
    public void onKBItemClicked(KB kb) {
        listener.onKBItemClicked(kb);
    }
}
