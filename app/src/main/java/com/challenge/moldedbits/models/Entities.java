package com.challenge.moldedbits.models;

import android.support.annotation.Keep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
 * Created by bharat on 03/01/16.
 */
@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {

    @JsonProperty("links")
    private List<Link> links;
}
