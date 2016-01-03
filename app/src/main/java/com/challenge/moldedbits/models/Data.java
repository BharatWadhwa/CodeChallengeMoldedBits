package com.challenge.moldedbits.models;

import android.support.annotation.Keep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bharat on 03/01/16.
 */
@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @JsonProperty("user")
    private User user;
    @JsonProperty("id")
    private String id;
    @JsonProperty("html")
    private String html;
    @JsonProperty("text")
    private String text;
    @JsonProperty("thread_id")
    private String threadId;
    @JsonProperty("num_stars")
    private int numStars;
    @JsonProperty("created_at")
    private String creationDate;
    @JsonProperty("num_replies")
    private int numReplies;
    @JsonProperty("num_reposts")
    private int numReposts;
    @JsonProperty("machine_only")
    private boolean machineOnly;
    @JsonProperty("canonical_url")
    private String canonicalUrl;
    @JsonProperty("pagination_id")
    private String paginationId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public int getNumStars() {
        return numStars;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public String getCreationDate() {return creationDate;}

    public void setCreationDate(String creationDate) {this.creationDate = creationDate;}

    public int getNumReplies() {
        return numReplies;
    }

    public void setNumReplies(int numReplies) {
        this.numReplies = numReplies;
    }

    public int getNumReposts() {
        return numReposts;
    }

    public void setNumReposts(int numReposts) {
        this.numReposts = numReposts;
    }

    public boolean isMachineOnly() {
        return machineOnly;
    }

    public void setMachineOnly(boolean machineOnly) {
        this.machineOnly = machineOnly;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public String getPaginationId() {
        return paginationId;
    }

    public void setPaginationId(String paginationId) {
        this.paginationId = paginationId;
    }
}
