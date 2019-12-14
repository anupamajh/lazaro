package com.carmel.guestjini.booking.common;

public class FilterSpecification {

    public static final int OPERATOR_EQUAL = 1;
    public static final int OPERATOR_NOT_EQUAL = 2;
    public static final int OPERATOR_GREATER_THAN = 3;
    public static final int OPERATOR_LESS_THAN = 4;
    public static final int OPERATOR_GREATER_OR_EQUAL = 5;
    public static final int OPERATOR_LESS_THAN_OR_EQUAL = 6;
    public static final int OPERATOR_BETWEEN = 7;
    public static final int OPERATOR_LIKE = 8;

    public static final int CONDITION_AND = 1;
    public static final int CONDITION_OR = 2;

    private int filterOperator;
    private int filterCondition;
    private FilterField filterField;

    public int getFilterOperator() {
        return filterOperator;
    }

    public void setFilterOperator(int filterOperator) {
        this.filterOperator = filterOperator;
    }

    public int getFilterCondition() {
        return filterCondition;
    }

    public void setFilterCondition(int filterCondition) {
        this.filterCondition = filterCondition;
    }

    public FilterField getFilterField() {
        return filterField;
    }

    public void setFilterField(FilterField filterField) {
        this.filterField = filterField;
    }
}
