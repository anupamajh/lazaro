package com.carmel.guesture.lazaroservice.request;

public class PhonedRequest {
    private String _v;
    private String event;
    private PhonedData data;

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

    public PhonedData getData() {
        return data;
    }

    public void setData(PhonedData data) {
        this.data = data;
    }
}
