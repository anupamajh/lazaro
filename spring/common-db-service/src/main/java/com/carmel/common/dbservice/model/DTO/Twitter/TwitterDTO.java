package com.carmel.common.dbservice.model.DTO.Twitter;

import com.carmel.common.dbservice.model.Twitter.Twitter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class TwitterDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String name;
    private String screenName;
    private String twitterId;
    private String location;
    private String biography;
    private String twitterCreated;
    private int followers;
    private int following;
    private int tweets;
    private int favorites;
    private String website;
    private String timezone;
    private String geoEnabled;
    private String verified;
    private String language;
    private String isProtected;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TwitterDTO() {
    }

    public TwitterDTO(Twitter twitter) {
        this.id = twitter.getId();
        this.clientId = twitter.getClientId();
        this.orgId = twitter.getOrgId();
        this.name = twitter.getName();
        this.screenName = twitter.getScreenName();
        this.twitterId = twitter.getTwitterId();
        this.location = twitter.getLocation();
        this.biography = twitter.getBiography();
        this.twitterCreated = twitter.getTwitterCreated();
        this.followers = twitter.getFollowers();
        this.following = twitter.getFollowing();
        this.tweets = twitter.getTweets();
        this.favorites = twitter.getFavorites();
        this.website = twitter.getWebsite();
        this.timezone = twitter.getTimezone();
        this.geoEnabled = twitter.getGeoEnabled();
        this.verified = twitter.getVerified();
        this.language = twitter.getLanguage();
        this.isProtected = twitter.getIsProtected();
        this.createdBy = twitter.getCreatedBy();
        this.creationTime = twitter.getCreationTime();
        this.lastModifiedBy = twitter.getLastModifiedBy();
        this.lastModifiedTime = twitter.getLastModifiedTime();
        this.isDeleted = twitter.getIsDeleted();
        this.deletedBy = twitter.getDeletedBy();
        this.deletedTime = twitter.getDeletedTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getTwitterCreated() {
        return twitterCreated;
    }

    public void setTwitterCreated(String twitterCreated) {
        this.twitterCreated = twitterCreated;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getTweets() {
        return tweets;
    }

    public void setTweets(int tweets) {
        this.tweets = tweets;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(String geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(String isProtected) {
        this.isProtected = isProtected;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }
}
