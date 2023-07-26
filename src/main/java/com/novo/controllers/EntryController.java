package com.novo.controllers;

import com.novo.model.entry.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller allows you to work with entry objects.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Controller
@RequestMapping("entry")
public class EntryController {

    /**
     * Adds new entry to the database.
     * @return the add entry fragment.
     */
    @GetMapping("add")
    public String getAddEntry (Model model){
        model.addAttribute("entry", new Entry());
        return "page";
    }
}
