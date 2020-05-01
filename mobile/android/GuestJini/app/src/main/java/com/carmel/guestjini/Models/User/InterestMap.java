package com.carmel.guestjini.Models.User;

import java.util.List;

public class InterestMap {
    private InterestCategory interestCategory;
    private List<UserInterests> interestList;

    public InterestCategory getInterestCategory() {
        return interestCategory;
    }

    public void setInterestCategory(InterestCategory interestCategory) {
        this.interestCategory = interestCategory;
    }

    public List<UserInterests> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<UserInterests> interestList) {
        this.interestList = interestList;
    }
}
