package com.aidar.service.impl;

import com.aidar.model.User;
import com.aidar.repository.UserRepository;
import com.aidar.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by paradise on 22.04.16.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User getPersistedPrincipal() {
        return userRepository.findOneByEmail(getPrincipal().getEmail());
    }

}
