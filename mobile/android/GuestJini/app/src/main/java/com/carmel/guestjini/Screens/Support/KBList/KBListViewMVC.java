package com.carmel.guestjini.Screens.Support.KBList;

import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Screens.Common.Views.ObservableViewMvc;

import java.util.List;

public interface KBListViewMVC extends ObservableViewMvc<KBListViewMVC.Listener> {
    public interface Listener {
        void onKBItemClicked(KB kb);

        void onSearchClicked(String searchText);

        void onBackClicked();
    }

    void bindKB(List<KB> kbList, int totalItems);

    void bindSearch(String searchText);

    void showProgressIndication();

    void hideProgressIndication();
}
