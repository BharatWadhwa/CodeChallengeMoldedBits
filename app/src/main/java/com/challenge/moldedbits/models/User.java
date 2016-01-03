package com.challenge.moldedbits.models;


import android.support.annotation.Keep;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bharat on 03/01/16.
 */
@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("counts")
    private Counts counts;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("username")
    private String username;
    @JsonProperty("created_at")
    private String creationDate;
    @JsonProperty("canonical_url")
    private String canonicalUrl;
    @JsonProperty("description")
    private Description description;
    @JsonProperty("cover_image")
    private UserImage coverImage;
    @JsonProperty("avatar_image")
    private UserImage avatarImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public UserImage getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(UserImage avatarImage) {
        this.avatarImage = avatarImage;
    }

    public UserImage getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(UserImage coverImage) {
        this.coverImage = coverImage;
    }
}
