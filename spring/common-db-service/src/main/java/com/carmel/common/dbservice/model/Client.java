package com.carmel.common.dbservice.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "oauth_client_details")
@Audited(targetAuditMode = NOT_AUDITED)
public class Client {
    @Id
    @Length(min = 1, max = 255, message = "Client name length should be between 1 and 255")
    @Column(name = "client_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String clientId;

    @Length(min = 0, max = 255)
    @NotNull
    @Column(name = "client_secret", nullable = false)
    private String clientSecrete;

    @Column(name = "web_server_redirect_uri")
    @Length(min = 0, max = 2048, message = "Web server redirect url length cannot exceed 2048 characters")
    private String webServerRedirectURL;

    @Column(name = "scope")
    @Length(min = 0, max = 8000, message = "Scope length cannot exceed 8000 characters")
    private String scope;

    @Column(name = "access_token_validity")
    private long accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private long refreshTokenValidity;

    @Column(name = "resource_ids")
    @Length(min = 0, max = 1024, message = "Resource ids length cannot exceed 1024 characters")
    private String resourceIds;

    @Column(name = "authorized_grant_types")
    @Length(min = 0, max = 1024, message = "Authorized Grant Types length cannot exceed 1024 characters")
    private String authorizedGrantTypes;

    @Column(name = "authorities")
    @Length(min = 0, max = 1024, message = "Authorities length cannot exceed 8000 characters")
    private String authorities;

    @Column(name = "additional_information")
    @Length(min = 0, max = 1024, message = "Additional information length cannot exceed 1024 characters")
    private String additionalInformation;

    @Column(name = "autoapprove")
    @Length(min = 0, max = 1024, message = "Auto approve length cannot exceed 1024 characters")
    private String autoapprove;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "client")
    private ClientDetails clientDetails;

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

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }
}
