package com.novo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller is responsible for the main page
 * @author Mikhail Dedyukhin
 * @since 1.o
 */
@Slf4j
@Controller
@RequestMapping
public class HomeController {

    @GetMapping
    public String getHome (Model model, Authentication authentication){
        model.addAttribute("fpath", "contents/main");
        model.addAttribute("fname", "main");
        return "page";
    }
}
