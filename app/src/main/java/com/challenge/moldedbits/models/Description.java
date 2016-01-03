package com.challenge.moldedbits.models;

import android.support.annotation.Keep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bharat on 03/01/16.
 */
@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
public class Description {

    @JsonProperty("html")
    private String html;
    @JsonProperty("text")
    private String text;
    @JsonProperty("entities")
    private Entities entities;

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

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }
}
