package com.masteringapi.meetings.model;

import java.util.List;

public class ValueWrapper<T> {

    private List<T> value;

    public ValueWrapper(List<T> items) {
        this.value = items;
    }

    public List<T> getValue() {
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }
}
