package com.challenge.moldedbits.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by bharat on 03/01/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class RecentPosts {

    private Meta meta;
    private List<Data> data;

    public RecentPosts(){}

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }
}
