package com.novo.controllers;

import com.novo.model.employee.Employee;
import com.novo.model.entry.Entry;
import com.novo.model.entry.service.EntryService;
import com.novo.model.user.User;
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


    @GetMapping
    public String getEntries (Model model){
        List<Entry> entries = entryService.findAllinInvertedOrder();

        model.addAttribute("fpath", "contents/entry");
        model.addAttribute("fname", "entries");
        model.addAttribute("entries", entries);
        return "page";
    }

    @GetMapping("add")
    public String getAddEntry(Model model, Authentication authentication) throws Exception {
        User user = userService.findByUsername(authentication.getName());

        model.addAttribute("fpath", "contents/entry");
        model.addAttribute("fname", "add");
        model.addAttribute("emps", userService.findByUsername(authentication.getName()).getEmployeeList());
        model.addAttribute("entry", new Entry());
        return "page";
    }


    @PostMapping("add")
    public String postAddEntry (@ModelAttribute Entry entry, Authentication authentication) throws Exception {
        User user = userService.findByUsername(authentication.getName());
        log.debug(entry.toString());
        entry.setCreatedBy(String.format("%s %s %s", user.getFirstName(), user.getMiddleName(), user.getLastName()));
        entryService.save(entry);
        return "redirect:/entry";
    }
}
