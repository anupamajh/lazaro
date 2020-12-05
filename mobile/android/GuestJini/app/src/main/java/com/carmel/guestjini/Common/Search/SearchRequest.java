package com.carmel.guestjini.Common.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest {
    private List<SearchCriteria> searchCriteria;
    private List<OrderUnit> orderUnits;
    private int pageSize;
    private int currentPage;
    private String userId;
    private String groupId;

    public SearchRequest() {
        searchCriteria = new ArrayList<>();
        orderUnits = new ArrayList<>();
        pageSize = 5000;
        currentPage = 1;
    }

    public List<SearchCriteria> getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(List<SearchCriteria> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public List<OrderUnit> getOrderUnits() {
        return orderUnits;
    }

    public void setOrderUnits(List<OrderUnit> orderUnits) {
        this.orderUnits = orderUnits;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
