package com.aidar.service.impl;

import com.aidar.service.GoogleMapsService;
import com.aidar.util.google_api.GeocodeResponse;
import com.aidar.util.google_api.LocationBody;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by paradise on 01.05.16.
 */
@Service
public class GoogleMapsServiceImpl implements GoogleMapsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${googleApiKey}")
    private String googleApiKey;

    @Value("${googleMapsGeocodeApiRequest}")
    private String geocodeUrl;

    @Override
    public LocationBody getLocation(String address) {
        URI uri = null;
        try {
            uri = new URIBuilder(geocodeUrl)
                    .addParameter("address", address)
                    .addParameter("key", googleApiKey)
                    .build();
        } catch (URISyntaxException ignored) {
        }
        GeocodeResponse response = restTemplate.getForObject(uri, GeocodeResponse.class);
        if (!response.getStatus().equalsIgnoreCase("ok")) {
            return null;
        }
        return response.getResults().get(0).getLocation().getLocationBody();
    }

}
