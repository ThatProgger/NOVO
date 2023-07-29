package com.novo.controllers;

import com.novo.model.entry.Entry;
import com.novo.model.entry.service.EntryService;
import com.novo.model.jobtypes.service.JobTypeService;
import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private JobTypeService jobTypeService;

    @GetMapping
    public String getEntries(Model model, @RequestParam("page") int pageNum) {
        Page<Entry> page = entryService.findAllSeparatedByPages(pageNum);
        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();


        model.addAttribute("fpath", "contents/entry");
        model.addAttribute("fname", "entries");
        model.addAttribute("entries", page.getContent());
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("totalPages", totalPages);

        if(currentPage == 0){
            model.addAttribute("previousPageLinkClass", "incorrectLink");
            model.addAttribute("previous", "#");
        } else {
            model.addAttribute("previousPageLinkClass", "correctLink");
            model.addAttribute("previous", String.format("/entry?page=%d", currentPage -1));
        }

        if(currentPage == totalPages-1){
            model.addAttribute("nextPageLinkClass", "incorrectLink");
            model.addAttribute("next", "#");
        }

        else {
            model.addAttribute("nextPageLinkClass", "correctLink");
            model.addAttribute("next", String.format("/entry?page=%d", currentPage + 1));
        }


        return "page";
    }

    @GetMapping("add")
    public String getAddEntry(Model model, Authentication authentication) throws Exception {
        User user = userService.findByUsername(authentication.getName());

        model.addAttribute("fpath", "contents/entry");
        model.addAttribute("fname", "add");
        model.addAttribute("emps", userService.findByUsername(authentication.getName()).getEmployeeList());
        model.addAttribute("entry", new Entry());
        model.addAttribute("jobTypes", jobTypeService.findAll());
        return "page";
    }


    @PostMapping("add")
    public String postAddEntry(@ModelAttribute Entry entry, Authentication authentication) throws Exception {
        log.debug(entry.toString());
        User user = userService.findByUsername(authentication.getName());
        log.debug(entry.toString());
        entry.setCreatedBy(String.format("%s %s %s", user.getFirstName(), user.getMiddleName(), user.getLastName()));
        entryService.save(entry);
        return "redirect:/entry?page=0";
    }
}
