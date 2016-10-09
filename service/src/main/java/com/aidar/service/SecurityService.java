package com.aidar.service;

import com.aidar.model.User;

/**
 * Created by paradise on 22.04.16.
 */
public interface SecurityService {

    User getPrincipal();

    User getPersistedPrincipal();

}
