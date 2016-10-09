package com.aidar.util.google_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by paradise on 03.04.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @JsonProperty("location")
    private LocationBody locationBody;

    public LocationBody getLocationBody() {
        return locationBody;
    }

    public void setLocationBody(LocationBody locationBody) {
        this.locationBody = locationBody;
    }
}
