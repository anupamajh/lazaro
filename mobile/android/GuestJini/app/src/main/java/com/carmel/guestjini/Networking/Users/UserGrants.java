package com.carmel.guestjini.Networking.Users;

public class UserGrants {
    private String[] aud;
    private String user_name;
    private String[] scope;
    private boolean active;
    private long exp;
    private String[] authorities;
    private String client_id;

    public String[] getAud() {
        return aud;
    }

    public void setAud(String[] aud) {
        this.aud = aud;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String[] getScope() {
        return scope;
    }

    public void setScope(String[] scope) {
        this.scope = scope;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
