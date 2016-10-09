package com.aidar.util;

import com.aidar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by paradise on 11.04.16.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegistrationForm user = (UserRegistrationForm) o;
        if (userRepository.findOneByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already in use");
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            errors.rejectValue("PasswordConfirmation", "", "Password doesn`t match");
        }
    }

}
