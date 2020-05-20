package com.carmel.guestjini.Screens.Support.KBList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

public class KBListViewMVCImpl
        extends BaseObservableViewMvc<KBListViewMVC.Listener>
        implements KBListViewMVC,
        KBRecyclerAdapter.Listener {

    private final EditText txtSearch;
    private final KBRecyclerAdapter kbRecyclerAdapter;
    private final ProgressBar progressBar;
    private final TextView txtKBInformation;

    public KBListViewMVCImpl(LayoutInflater inflater,
                             @Nullable ViewGroup parent,
                             ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_kb_list, parent, false));

        RecyclerView kbRecyclerView = findViewById(R.id.lstKBItems);
        txtSearch = findViewById(R.id.txtSearch);
        txtKBInformation = findViewById(R.id.txtKBInformation);
        ImageView btnSearch = findViewById(R.id.btnSearch);
        ImageView btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progress);
        kbRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kbRecyclerAdapter = new KBRecyclerAdapter(this, viewMVCFactory);
        kbRecyclerView.setAdapter(kbRecyclerAdapter);
        btnSearch.setOnClickListener(view -> {
            String searchText = txtSearch.getText().toString().trim();
            for (Listener listener : getListeners()) {
                listener.onSearchClicked(searchText);
            }
        });

        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });

    }

    @Override
    public void bindKB(List<KB> kbList, int totalItems) {
        txtKBInformation.setText(
                "Showing " +
                        (kbList.size()) +
                        " of " +
                        (totalItems));

        kbRecyclerAdapter.bindKB(kbList);
    }

    @Override
    public void bindSearch(String searchText) {
        txtSearch.setText(searchText);
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
    public void onKBItemClicked(KB kb) {
        for (Listener listener : getListeners()) {
            listener.onKBItemClicked(kb);
        }
    }
}
