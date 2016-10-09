package com.aidar.util.google_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by paradise on 03.04.16.
 */
@JsonIgnoreProperties
public class LocationBody {

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
