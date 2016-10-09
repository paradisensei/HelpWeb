package com.aidar.util;

import com.aidar.enums.Role;
import com.aidar.enums.UserStatus;
import com.aidar.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by paradise on 11.04.16.
 */
public class UserRegistrationFormToUserTransformer {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static User transform(UserRegistrationForm userRegistrationForm) {
        User user = new User();
        user.setName(userRegistrationForm.getName());
        user.setSurname(userRegistrationForm.getSurname());
        user.setEmail(userRegistrationForm.getEmail());
        user.setPassword(encoder.encode(userRegistrationForm.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setStatus(UserStatus.ACTIVE);
        return user;
    }

}
