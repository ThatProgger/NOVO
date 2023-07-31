package com.novo.controllers;

import com.novo.model.entry.Entry;
import com.novo.model.entry.service.EntryService;
import com.novo.model.jobtypes.service.JobTypeService;
import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import com.novo.pagination.Pagination;
import com.novo.pagination.impl.PaginationImpl;
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
@RequestMapping
@Slf4j
public class EntryController {

    @Autowired
    private EntryService entryService;
    @Autowired
    private UserService userService;

    @Autowired
    private JobTypeService jobTypeService;


    @GetMapping
    public String redirect (){
        return "redirect:/entries?page=0";
    }

    @GetMapping("entries")
    public String getEntries(Model model, @RequestParam("page") int pageNum) {
        Page<Entry> page = entryService.findAllSeparatedByPages(pageNum);
        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();

        Pagination pagination = new PaginationImpl();
        pagination.setCurrentPage(currentPage);
        pagination.setTotalPages(totalPages);



        model.addAttribute("fpath", "fragments/entries");
        model.addAttribute("fname", "entries");
        model.addAttribute("entries", page.getContent());
        model.addAttribute("pagination", pagination);
//        model.addAttribute("currentPage", currentPage + 1);
//        model.addAttribute("totalPages", totalPages);




        return "index";
    }

    @GetMapping("add")
    public String getAddEntry(Model model, Authentication authentication) throws Exception {
        User user = userService.findByUsername(authentication.getName());

        model.addAttribute("fpath", "fragments/entry-add");
        model.addAttribute("fname", "add");
        model.addAttribute("emps", userService.findByUsername(authentication.getName()).getEmployeeList());
        model.addAttribute("entry", new Entry());
        model.addAttribute("jobTypes", jobTypeService.findAll());
        return "index";
    }


    @PostMapping("add")
    public String postAddEntry(@ModelAttribute Entry entry, Authentication authentication) throws Exception {
        log.debug(entry.toString());
        User user = userService.findByUsername(authentication.getName());
        entry.setCreatedBy(String.format("%s %s %s", user.getFirstName(), user.getMiddleName(), user.getLastName()));
        entryService.save(entry);
        return "redirect:?page=0";
    }
}
