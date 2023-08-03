package com.novo.controllers;

import com.novo.model.jobtypes.service.JobTypeService;
import com.novo.model.responsible.service.ResponsibleService;
import com.novo.model.search.Search;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("search")
public class SearchController {
    @Autowired
    private JobTypeService jobTypeService;


    @Autowired
    private ResponsibleService responsibleService;

    @GetMapping
    public String getSearch (Model model){
        model.addAttribute("fpath", "fragments/search");
        model.addAttribute("fname", "search");
        model.addAttribute("search", new Search());
        model.addAttribute("jobTypes", jobTypeService.findAll());
        model.addAttribute("responsibles", responsibleService.findAll());
        return "index";
    }
}
