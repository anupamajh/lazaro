package com.carmel.guestjini.Screens.Common.FragmentHelper;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public interface HierarchicalFragment {
    @Nullable
    Fragment getHierarchicalParentFragment();
}
