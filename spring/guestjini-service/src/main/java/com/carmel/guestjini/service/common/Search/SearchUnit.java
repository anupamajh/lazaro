package com.carmel.guestjini.service.common.Search;

import java.util.List;

public class SearchUnit {
    /**
     * Operators :  lessthan,lessthanorequal,greaterthan,greaterthanorequal
     *              notequal,between
     */
    private String operator;
    private String field;
    private String value;
    private String value1;
    private List<Object> inValues;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Object> getInValues() {
        return inValues;
    }

    public void setInValues(List<Object> inValues) {
        this.inValues = inValues;
    }
}
