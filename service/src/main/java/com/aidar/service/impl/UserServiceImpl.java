package com.aidar.service.impl;

import com.aidar.enums.Role;
import com.aidar.enums.UserStatus;
import com.aidar.model.User;
import com.aidar.repository.UserRepository;
import com.aidar.service.SecurityService;
import com.aidar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by paradise on 08.04.16.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    public List<User> getAll() {
        return userRepository.findByRole(Role.ROLE_USER);
    }

    @Override
    public User getCurrent() {
        return securityService.getPersistedPrincipal();
    }

    @Override
    public User getOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        User newUser = userRepository.findOne(user.getId());
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
    }

    @Override
    public void ban(String email) {
        User user = userRepository.findOneByEmail(email);
        user.setStatus(UserStatus.BANNED);
    }

    @Override
    public void pardon(String email) {
        User user = userRepository.findOneByEmail(email);
        user.setStatus(UserStatus.ACTIVE);
    }

}
