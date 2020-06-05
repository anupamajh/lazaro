package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.Client;

public class ClientDTO {
    private String clientId;
    private String clientSecrete;
    private String webServerRedirectURL;
    private String scope;
    private long accessTokenValidity;
    private long refreshTokenValidity;
    private String resourceIds;
    private String authorizedGrantTypes;
    private String authorities;
    private String additionalInformation;
    private String autoapprove;
    private ClientDetailsDTO clientDetails;

    public ClientDTO() {

    }

    public ClientDTO(Client client) {
        this.clientId = client.getClientId();
        this.clientSecrete = client.getClientSecrete();
        this.webServerRedirectURL = client.getWebServerRedirectURL();
        this.scope = client.getScope();
        this.accessTokenValidity = client.getAccessTokenValidity();
        this.refreshTokenValidity = client.getRefreshTokenValidity();
        this.resourceIds = client.getResourceIds();
        this.authorizedGrantTypes = client.getAuthorizedGrantTypes();
        this.authorities = client.getAuthorities();
        this.autoapprove = client.getAutoapprove();
        this.clientDetails = client.getClientDetails() != null ? ClientDetailsDTO.getSimple(client.getClientDetails()) : null;

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecrete() {
        return clientSecrete;
    }

    public void setClientSecrete(String clientSecrete) {
        this.clientSecrete = clientSecrete;
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

    public ClientDetailsDTO getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetailsDTO clientDetails) {
        this.clientDetails = clientDetails;
    }
}
