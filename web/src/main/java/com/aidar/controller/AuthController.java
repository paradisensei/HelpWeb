package com.aidar.controller;

import com.aidar.service.UserService;
import com.aidar.util.UserRegistrationForm;
import com.aidar.util.UserRegistrationFormToUserTransformer;
import com.aidar.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by paradise on 12.04.16.
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String getSignIn(@RequestParam(value = "error", required = false) Boolean error,
                            Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "user/sign_in";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String getSignUp(Model model) {
        model.addAttribute("user", new UserRegistrationForm());
        return "user/sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") @Valid UserRegistrationForm userRegistrationForm,
                         BindingResult result) {
        userValidator.validate(userRegistrationForm, result);
        if (result.hasErrors()) {
            return "user/sign_up";
        }
        userService.add(UserRegistrationFormToUserTransformer
                .transform(userRegistrationForm));
        return "redirect:/sign_in";
    }

}
