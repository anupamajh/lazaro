package com.carmel.guesture.lazaroservice.request;

import com.carmel.guesture.lazaroservice.model.Person;

public class PersonRequest {
    private String _v;
    private String event;
    private PersonData data;

    public String get_v() {
        return _v;
    }

    public void set_v(String _v) {
        this._v = _v;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public PersonData getData() {
        return data;
    }

    public void setData(PersonData data) {
        this.data = data;
    }
}
