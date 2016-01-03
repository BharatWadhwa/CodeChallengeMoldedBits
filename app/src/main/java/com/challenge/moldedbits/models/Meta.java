package com.challenge.moldedbits.models;

import android.support.annotation.Keep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bharat on 03/01/16.
 */
@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {

    @JsonProperty("min_id")
    private String minId;
    @JsonProperty("code")
    private int code;
    @JsonProperty("more")
    private boolean more;
    @JsonProperty("maxId")
    private String maxId;
    @JsonProperty("error_message")
    private String errorMessage;

    public String getMinId() {
        return minId;
    }

    public void setMinId(String minId) {
        this.minId = minId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public String getMaxId() {
        return maxId;
    }

    public void setMaxId(String maxId) {
        this.maxId = maxId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
