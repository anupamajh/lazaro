package com.carmel.guestjini.Screens.Common.Views;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BaseObservableViewMvc<ListenerType> extends BaseViewMvc
        implements ObservableViewMvc<ListenerType> {

    private Set<ListenerType> mListeners = new HashSet<>();

    @Override
    public final void registerListener(ListenerType listener) {
        mListeners.add(listener);
    }

    @Override
    public final void unregisterListener(ListenerType listener) {
        mListeners.remove(listener);
    }

    protected final Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(mListeners);
    }
}