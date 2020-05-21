package com.carmel.guestjini.Screens.Support.KBList.KBListItem;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

public interface KBListItemViewMVC extends ObservableViewMvc<KBListItemViewMVC.Listener> {


    public interface Listener {
        void onKBItemClicked(KB kb);
    }

    void bindQuestion(KB kb);


}
