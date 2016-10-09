package com.aidar.util;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by paradise on 11.04.16.
 */
public class UserRegistrationForm {

    @NotEmpty(message = "This field can`t be empty")
    private String name;

    @NotEmpty(message = "This field can`t be empty")
    private String surname;

    @Email(message = "Not a valid email")
    private String email;

    @Size(min = 7, max = 20, message = "Wrong size of password")
    private String password;

    @Size(min = 7, max = 20, message = "Wrong size of password")
    private String passwordConfirmation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
