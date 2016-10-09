package com.aidar.service;

import com.aidar.util.google_api.LocationBody;

/**
 * Created by paradise on 01.05.16.
 */
public interface GoogleMapsService {

    LocationBody getLocation(String address);

}
