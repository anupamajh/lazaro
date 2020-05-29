package com.carmel.guestjini.Screens.Support.KBList.KBListItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Common.StringUtils;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class KBListItemViewMVCImpl
        extends BaseObservableViewMvc<KBListItemViewMVC.Listener>
        implements KBListItemViewMVC {

    CircleImageView profileImage;
    TextView txtKBTitle;
    TextView txtKBAuthorName;
    TextView txtKBDate;
    TextView txtKBShortText;
    FloatingActionButton btnShowKBDetail;

    private KB kb;

    public KBListItemViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_kb_list_item, parent, false));
        profileImage = findViewById(R.id.profilePicture);
        txtKBTitle = findViewById(R.id.txtKBTitle);
        txtKBAuthorName = findViewById(R.id.txtKBAuthorName);
        txtKBDate = findViewById(R.id.txtKBDate);
        txtKBShortText = findViewById(R.id.txtKBShortText);
        btnShowKBDetail = findViewById(R.id.btnShowKBDetail);
        btnShowKBDetail.setOnClickListener(view -> {
            for (Listener listener : getListeners()) {
                listener.onKBItemClicked(kb);
            }
        });


    }

    @Override
    public void bindKB(KB kb) {
        this.kb = kb;
        txtKBTitle.setText(kb.getTopicTitle());
        txtKBAuthorName.setText(kb.getAuthorName());
        txtKBDate.setText(kb.getCreationTime()); //TODO: format date here
        txtKBShortText.setText(StringUtils.toEllipsis(
                kb.getTopicNarration(), 150, 0
                )
        );
        //TODO: find a nice way to bind image
    }
}
