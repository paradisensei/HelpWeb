package com.aidar.util.google_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by paradise on 03.04.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty("geometry")
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
