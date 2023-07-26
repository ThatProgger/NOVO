package com.novo.controllers;

import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Allows you to find and pass the user across all controllers
 * @author Mikhail Dedyukhin
 * @since
 */
@Slf4j
@ControllerAdvice
public class UserAdvice {
@Autowired
private UserService userService;


    @ModelAttribute("AuthorizedUser")
    public User getAuthorizedUser (Authentication authentication){
        User user = null;
        try {
            user =   userService.findByUsername(authentication.getName());
            log.debug("The user has been found by the username: {}", authentication.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return user;
    }
}
