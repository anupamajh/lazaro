package com.carmel.common.dbservice.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InstagramConfig {
    private Map<String, Object> instagramConfig;

    private List<String> instagramId;
    private List<String> userName;
    private List<String> fullName;
    private List<String> userProfile;
    private List<String> isFollowedByUser;
    private List<String> isRequestedByUser;
    private List<String> isUserFollowedBy;
    private List<String> profilePicUrl;
    private List<String> isVerified;
    private List<String> profilePicUrlHD;
    private List<String> isPrivate;
    private List<String> followsCount;
    private List<String> followedByCount;
    private List<String> followingTagCount;
    private List<String> mediaCount;
    private List<String> biography;
    private List<String> isBusiness;
    private List<String> userTags;
    private List<String> mutualFollowersCount;

    public InstagramConfig(YAMLConfig yamlConfig) {
        try {
            Yaml yaml = new Yaml();
            FileInputStream inputStream =
                    new FileInputStream(new File(
                            yamlConfig.getInstagramConfigPath()
                    ));
            this.instagramConfig = yaml.load(inputStream);
            this.processConfig();
        } catch (Exception ex) {
            String message = ex.getMessage();

        }
    }

    private void processConfig() {
        Map<String, String> instagram = (Map<String, String>) this.instagramConfig.get("instagram");
        this.instagramId = Arrays.asList(instagram.get("instagramId").split(","));
        this.userName = Arrays.asList(instagram.get("userName").split(","));
        this.fullName = Arrays.asList(instagram.get("fullName").split(","));
        this.userProfile = Arrays.asList(instagram.get("userProfile").split(","));
        this.isFollowedByUser = Arrays.asList(instagram.get("isFollowedByUser").split(","));
        this.isRequestedByUser = Arrays.asList(instagram.get("isRequestedByUser").split(","));
        this.isUserFollowedBy = Arrays.asList(instagram.get("isUserFollowedBy").split(","));
        this.profilePicUrl = Arrays.asList(instagram.get("profilePicUrl").split(","));
        this.isVerified = Arrays.asList(instagram.get("isVerified").split(","));
        this.profilePicUrlHD = Arrays.asList(instagram.get("profilePicUrlHD").split(","));
        this.isPrivate = Arrays.asList(instagram.get("isPrivate").split(","));
        this.followsCount = Arrays.asList(instagram.get("followsCount").split(","));
        this.followedByCount = Arrays.asList(instagram.get("followedByCount").split(","));
        this.followingTagCount = Arrays.asList(instagram.get("followingTagCount").split(","));
        this.mediaCount = Arrays.asList(instagram.get("mediaCount").split(","));
        this.biography = Arrays.asList(instagram.get("biography").split(","));
        this.isBusiness = Arrays.asList(instagram.get("isBusiness").split(","));
        this.userTags = Arrays.asList(instagram.get("userTags").split(","));
        this.mutualFollowersCount = Arrays.asList(instagram.get("mutualFollowersCount").split(","));
    }

    public List<String> getInstagramId() {
        return instagramId;
    }

    public List<String> getUserName() {
        return userName;
    }

    public List<String> getFullName() {
        return fullName;
    }

    public List<String> getUserProfile() {
        return userProfile;
    }

    public List<String> getIsFollowedByUser() {
        return isFollowedByUser;
    }

    public List<String> getIsRequestedByUser() {
        return isRequestedByUser;
    }

    public List<String> getIsUserFollowedBy() {
        return isUserFollowedBy;
    }

    public List<String> getProfilePicUrl() {
        return profilePicUrl;
    }

    public List<String> getIsVerified() {
        return isVerified;
    }

    public List<String> getProfilePicUrlHD() {
        return profilePicUrlHD;
    }

    public List<String> getIsPrivate() {
        return isPrivate;
    }

    public List<String> getFollowsCount() {
        return followsCount;
    }

    public List<String> getFollowedByCount() {
        return followedByCount;
    }

    public List<String> getFollowingTagCount() {
        return followingTagCount;
    }

    public List<String> getMediaCount() {
        return mediaCount;
    }

    public List<String> getBiography() {
        return biography;
    }

    public List<String> getIsBusiness() {
        return isBusiness;
    }

    public List<String> getUserTags() {
        return userTags;
    }

    public List<String> getMutualFollowersCount() {
        return mutualFollowersCount;
    }
}
