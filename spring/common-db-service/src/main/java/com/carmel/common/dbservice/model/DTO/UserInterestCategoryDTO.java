package com.carmel.common.dbservice.model.DTO;

import java.util.List;

public class UserInterestCategoryDTO {
    private InterestCategoryDTO interestCategory;
    private List<UserInterestsDTO> interestList;

    public InterestCategoryDTO getInterestCategory() {
        return interestCategory;
    }

    public void setInterestCategory(InterestCategoryDTO interestCategory) {
        this.interestCategory = interestCategory;
    }

    public List<UserInterestsDTO> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<UserInterestsDTO> interestList) {
        this.interestList = interestList;
    }
}
