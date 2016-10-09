package com.aidar.util;

import com.aidar.model.Request;
import com.aidar.service.GoogleMapsService;
import com.aidar.util.google_api.LocationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by paradise on 01.05.16.
 */
@Component
public class RequestValidator implements Validator {

    @Autowired
    private GoogleMapsService googleMapsService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Request.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Request request = (Request) o;
        String address = request.getAddress();
        LocationBody location = null;
        if (address != null && !address.isEmpty()) {
            location = googleMapsService.getLocation(address);
        }
        if (location == null) {
            errors.rejectValue("address", "", "Enter valid address");
        }
    }

}
