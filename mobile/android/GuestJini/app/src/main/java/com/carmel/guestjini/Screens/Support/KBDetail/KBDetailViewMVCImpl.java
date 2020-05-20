package com.carmel.guestjini.Screens.Support.KBDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

public class KBDetailViewMVCImpl
        extends BaseObservableViewMvc<KBDetailViewMVC.Listener>
        implements KBDetailViewMVC {

    private final TextView txtKBAuthorName;
    private final TextView txtKBDate;
    private final TextView txtKBTitle;
    private final TextView txtKBNarration;
    private final ProgressBar progressBar;

    public KBDetailViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_kb_detail, parent, false));
        txtKBAuthorName = findViewById(R.id.txtKBAuthorName);
        txtKBDate = findViewById(R.id.txtKBDate);
        txtKBTitle = findViewById(R.id.txtKBTitle);
        txtKBNarration = findViewById(R.id.txtKBNarration);
        progressBar = findViewById(R.id.progress);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onBackClicked();
            }
        });
    }

    @Override
    public void bindKB(KB kb) {
        txtKBAuthorName.setText(kb.getAuthorName());
        txtKBDate.setText(kb.getCreationTime()); //TODO: Format the date
        txtKBTitle.setText(kb.getTopicTitle());
        txtKBNarration.setText(kb.getTopicNarration());
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);

    }
}
