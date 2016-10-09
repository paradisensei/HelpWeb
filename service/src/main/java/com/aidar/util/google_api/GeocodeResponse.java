package com.aidar.util.google_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by paradise on 03.04.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponse {

    @JsonProperty("results")
    private List<Result> results;

    @JsonProperty("status")
    private String status;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
