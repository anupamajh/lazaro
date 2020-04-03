package com.carmel.guestjini.service.request.Search;

import java.util.List;

public class SearchCriteria {
    private String condition;
    private String searchUnitCondition;
    private List<SearchUnit> searchUnits;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSearchUnitCondition() {
        return searchUnitCondition;
    }

    public void setSearchUnitCondition(String searchUnitCondition) {
        this.searchUnitCondition = searchUnitCondition;
    }

    public List<SearchUnit> getSearchUnits() {
        return searchUnits;
    }

    public void setSearchUnits(List<SearchUnit> searchUnits) {
        this.searchUnits = searchUnits;
    }
}
