package com.novo.controllers;

import com.novo.model.employee.Employee;
import com.novo.model.entry.Entry;
import com.novo.model.entry.service.EntryService;
import com.novo.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller allows you to work with entry objects.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Controller
@RequestMapping("entry")
@Slf4j
public class EntryController {

    @Autowired
    private EntryService entryService;
    @Autowired
    private UserService userService;


    @GetMapping("add")
    public String getAddEntry(Model model, Authentication authentication) throws Exception {
        model.addAttribute("emps", userService.findByUsername(authentication.getName()).getEmployeeList());
        model.addAttribute("entry", new Entry());
        return "page";
    }


    @PostMapping("add")
    public String postAddEntry (@ModelAttribute Entry entry, Authentication authentication){
        log.debug(entry.toString());
        return "redirect:/entry/add";
    }
}
