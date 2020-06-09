package com.carmel.common.dbservice.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TwitterConfig {
    private Map<String, Object> twitterConfig;
    private List<String> name;
    private List<String> screenName;
    private List<String> twitterId;
    private List<String> location;
    private List<String> biography;
    private List<String> twitterCreated;
    private List<String> followers;
    private List<String> following;
    private List<String> tweets;
    private List<String> favorites;
    private List<String> website;
    private List<String> timezone;
    private List<String> geoEnabled;
    private List<String> verified;
    private List<String> language;
    private List<String> isProtected;

    public TwitterConfig(YAMLConfig yamlConfig) {
        try {
            Yaml yaml = new Yaml();
            FileInputStream inputStream =
                    new FileInputStream(new File(
                            yamlConfig.getTwitterConfigPath()
                    ));
            this.twitterConfig = yaml.load(inputStream);
            this.processConfig();
        } catch (Exception ex) {
            String message = ex.getMessage();

        }
    }

    private void processConfig() {
        Map<String, String> twitter = (Map<String, String>) this.twitterConfig.get("twitter");
        this.name = Arrays.asList(twitter.get("name").split(","));
        this.screenName = Arrays.asList(twitter.get("screenName").split(","));
        this.twitterId = Arrays.asList(twitter.get("twitterId").split(","));
        this.location = Arrays.asList(twitter.get("location").split(","));
        this.biography = Arrays.asList(twitter.get("biography").split(","));
        this.twitterCreated = Arrays.asList(twitter.get("twitterCreated").split(","));
        this.followers = Arrays.asList(twitter.get("followers").split(","));
        this.following = Arrays.asList(twitter.get("following").split(","));
        this.tweets = Arrays.asList(twitter.get("tweets").split(","));
        this.favorites = Arrays.asList(twitter.get("favorites").split(","));
        this.website = Arrays.asList(twitter.get("website").split(","));
        this.timezone = Arrays.asList(twitter.get("timezone").split(","));
        this.geoEnabled = Arrays.asList(twitter.get("geoEnabled").split(","));
        this.verified = Arrays.asList(twitter.get("verified").split(","));
        this.language = Arrays.asList(twitter.get("language").split(","));
        this.isProtected = Arrays.asList(twitter.get("isProtected").split(","));
    }


    public List<String> getName() {
        return name;
    }

    public List<String> getScreenName() {
        return screenName;
    }

    public List<String> getTwitterId() {
        return twitterId;
    }

    public List<String> getLocation() {
        return location;
    }

    public List<String> getBiography() {
        return biography;
    }

    public List<String> getTwitterCreated() {
        return twitterCreated;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public List<String> getTweets() {
        return tweets;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public List<String> getWebsite() {
        return website;
    }

    public List<String> getTimezone() {
        return timezone;
    }

    public List<String> getGeoEnabled() {
        return geoEnabled;
    }

    public List<String> getVerified() {
        return verified;
    }

    public List<String> getLanguage() {
        return language;
    }

    public List<String> getIsProtected() {
        return isProtected;
    }
}
