package com.carmel.guestjini.booking.model.Principal;


public class Client {
    private String clientId;
    private String webServerRedirectURL;
    private String scope;
    private long accessTokenValidity;
    private long refreshTokenValidity;
    private String resourceIds;
    private String authorizedGrantTypes;
    private String authorities;
    private String additionalInformation;
    private String autoapprove;
    private ClientDetails clientDetails;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getWebServerRedirectURL() {
        return webServerRedirectURL;
    }

    public void setWebServerRedirectURL(String webServerRedirectURL) {
        this.webServerRedirectURL = webServerRedirectURL;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(long accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public long getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(long refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }
}
