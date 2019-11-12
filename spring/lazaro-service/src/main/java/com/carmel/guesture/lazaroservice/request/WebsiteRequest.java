package com.carmel.guesture.lazaroservice.request;

public class WebsiteRequest {
    private String _v;
    private String event;
    private WebsiteData data;

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

    public WebsiteData getData() {
        return data;
    }

    public void setData(WebsiteData data) {
        this.data = data;
    }
}
