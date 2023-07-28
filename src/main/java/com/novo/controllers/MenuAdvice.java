package com.novo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Slf4j
@ControllerAdvice
public class MenuAdvice {


    @ModelAttribute("showUserMenu")
    public boolean showUserMenu (Authentication authentication){
        if(authentication.getName().equals("admin")){
            log.debug("The show user menu: {}", true);
            return  true;
        }
        else{
            log.debug("The show user menu: {}", false);
            return  false;
        }

    }
}
